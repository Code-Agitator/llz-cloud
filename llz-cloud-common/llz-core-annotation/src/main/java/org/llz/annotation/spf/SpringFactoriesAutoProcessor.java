package org.llz.annotation.spf;

import org.llz.annotation.base.BaseAbstractProcessor;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;



@SupportedAnnotationTypes("org.llz.annotation.spf.SpringFactoriesAuto")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SpringFactoriesAutoProcessor extends BaseAbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<Element> classList = getClassList(roundEnv, SpringFactoriesAuto.class);

        return false;
    }
}
