package org.llz.annotation.base;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.*;

@SupportedAnnotationTypes("org.llz.annotation.base.SPIAuto")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SPIAutoProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<Element> classList = getClassList(roundEnv, SPIAuto.class);

        Map<String, Set<String>> spiClassMap = new HashMap<>();
        for (Element clazz : classList) {

            // 获取对应的SPI接口
            String spiInterfaceName = getSpiClassName(clazz);
            String spiClassName = ((Symbol.ClassSymbol) clazz).fullname.toString();
            SPIAuto spiAuto = ((Symbol.ClassSymbol) clazz).getAnnotation(SPIAuto.class);
            String filePath = spiAuto.dir() + spiInterfaceName;
            Set<String> lineSet = spiClassMap.get(filePath);
            if (lineSet == null) {
                lineSet = new HashSet<>();
            }
            lineSet.add(spiClassName);
            spiClassMap.put(filePath, lineSet);
        }
        generateNewFiles(spiClassMap);

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

    private void write(Set<String> newLines, OutputStream writer) throws IOException {

        for (String line : newLines) {
            writer.write((line + "\n").getBytes());
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

    private String getSpiClassName(Element clazz) {
        com.sun.tools.javac.util.List<Type> interfaces = ((Symbol.ClassSymbol) clazz).getInterfaces();
        if (interfaces.size() == 0) {
            return "";
        }


        for (Type interface_ : interfaces) {
            if (interface_.tsym.getAnnotation(SPI.class) != null) {
                return ((Symbol.ClassSymbol) interface_.tsym).fullname.toString();
            }
        }
        return "";

    }

    /**
     * 获取所有被@SPIAuto注解的类
     */
    protected List<Element> getClassList(RoundEnvironment roundEnv, Class<? extends Annotation> clazz) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(clazz);
        List<Element> classList = new ArrayList<>();

        for (Element element : elements) {
            if (element instanceof Symbol.ClassSymbol) {
                classList.add(element);
            }
        }
        return classList;
    }
}
