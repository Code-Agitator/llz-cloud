package org.llz.annotation.base;

import com.sun.tools.javac.code.Symbol;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class BaseAbstractProcessor extends AbstractProcessor {

    /**
     * 获取所有被@clazz注解的类
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
