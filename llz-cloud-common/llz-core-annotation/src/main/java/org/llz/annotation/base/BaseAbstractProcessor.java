package org.llz.annotation.base;


import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseAbstractProcessor extends AbstractProcessor {

    /**
     * 获取所有被@clazz注解的类
     */
    protected List<Element> getClassList(RoundEnvironment roundEnv, Class<? extends Annotation> clazz) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(clazz);
        return new ArrayList<>(elements);
    }





}
