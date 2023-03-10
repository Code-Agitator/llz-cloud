package org.llz.annotation.spf;

import org.llz.annotation.base.BaseAbstractProcessor;
import org.llz.annotation.spi.SPIAutoProcessor;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@SupportedAnnotationTypes("org.llz.annotation.spf.SpringFactoriesAuto")
public class SpringFactoriesAutoProcessor extends BaseAbstractProcessor {
    Logger logger = Logger.getLogger(SpringFactoriesAutoProcessor.class.getName());

    private static final String FILE_NAME = "META-INF/spring.factories";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<Element> classList = getClassList(roundEnv, SpringFactoriesAuto.class);
        if (!classList.isEmpty()) {
            generateNewFiles(classList.stream().map(Element::toString).collect(Collectors.toSet()));
        }

        return true;
    }

    private void generateNewFiles(Set<String> clzzs) {
        Filer filer = processingEnv.getFiler();

        try {
            FileObject existingFile = filer.getResource(StandardLocation.CLASS_OUTPUT, "", FILE_NAME);

            Set<String> oldLines = readAllLines(existingFile.openInputStream());
            clzzs.addAll(oldLines);
            write(new HashSet<>(clzzs), existingFile.openOutputStream(), false);
            return;
        } catch (IOException | IllegalStateException e) {
            logger.info("文件不存在，重新创建,message:" + e.getMessage());
        }

        try {
            FileObject newFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "",
                    FILE_NAME);
            OutputStream outputStream = newFile.openOutputStream();
            write(new HashSet<>(clzzs), outputStream, true);
        } catch (IOException e) {
            logger.info("重新创建失败，请检查,message:" + e.getMessage());
        }


    }

    private void write(Set<String> lines, OutputStream openOutputStream, boolean isNew) throws IOException {
        if (isNew) {
            openOutputStream.write("org.springframework.boot.autoconfigure.EnableAutoConfiguration=".getBytes());
        }
        openOutputStream.write(String.join(",", lines).getBytes());
    }


}
