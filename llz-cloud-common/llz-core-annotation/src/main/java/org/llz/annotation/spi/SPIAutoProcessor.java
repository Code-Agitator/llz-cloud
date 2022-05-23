package org.llz.annotation.spi;

import org.llz.annotation.base.BaseAbstractProcessor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.*;

@SupportedAnnotationTypes("org.llz.annotation.spi.SPIAuto")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SPIAutoProcessor extends BaseAbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<Element> classList = getClassList(roundEnv, SPIAuto.class);

        if (classList.size() > 0) {
            Map<String, Set<String>> spiClassMap = new HashMap<>();
            for (Element clazz : classList) {
                // 获取对应的SPI接口
                String spiInterfaceName = getSpiClassName(clazz);
                String spiClassName = clazz.toString();
                SPIAuto spiAuto = clazz.getAnnotation(SPIAuto.class);
                String filePath = spiAuto.dir() + spiInterfaceName;
                Set<String> lineSet = spiClassMap.get(filePath);
                if (lineSet == null) {
                    lineSet = new HashSet<>();
                }
                lineSet.add(spiClassName);
                spiClassMap.put(filePath, lineSet);
            }
            generateNewFiles(spiClassMap);
        }

        return true;
    }

    private void generateNewFiles(Map<String, Set<String>> spiClassMap) {
        Filer filer = processingEnv.getFiler();

        for (Map.Entry<String, Set<String>> entry : spiClassMap.entrySet()) {
            String fullFilePath = entry.getKey();
            Set<String> newLines = entry.getValue();
            try {
                FileObject existingFile = filer.getResource(StandardLocation.CLASS_OUTPUT, "", fullFilePath);

                Set<String> oldLines = readAllLines(existingFile.openInputStream());
                newLines.addAll(oldLines);
                write(newLines, existingFile.openOutputStream());
                return;
            } catch (IOException | IllegalStateException e) {
                System.out.println("文件不存在，重新创建");
                e.printStackTrace();
            }


            try {
                FileObject newFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "",
                        fullFilePath);
                OutputStream outputStream = newFile.openOutputStream();
                write(newLines, outputStream);
            } catch (IOException e) {
                System.out.println("重新创建失败，请检查:" + e.getMessage());
            }
        }
    }


    private Set<String> readAllLines(InputStream inputStream) throws IOException {
        Set<String> lines = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void write(Set<String> newLines, OutputStream writer) throws IOException {

        for (String line : newLines) {
            writer.write((line + "\n").getBytes());
        }

    }


    private String getSpiClassName(Element clazz) {
//        com.sun.tools.javac.util.List<Type> interfaces = ((Symbol.ClassSymbol) clazz).getInterfaces();
        final List<? extends TypeMirror> interfaces = ((TypeElement) clazz).getInterfaces();
        if (interfaces.size() == 0) {
            return "";
        }


        for (TypeMirror interface_ : interfaces) {
            if (((DeclaredType) interface_).asElement().getAnnotation(SPI.class) != null) {
                return interface_.toString();
            }
        }
        return "";

    }

}
