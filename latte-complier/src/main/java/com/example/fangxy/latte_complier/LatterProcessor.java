package com.example.fangxy.latte_complier;

import com.example.fangxy.latte_annotations.AppregisiterGenerator;
import com.example.fangxy.latte_annotations.EntryGenerator;
import com.example.fangxy.latte_annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@AutoService(Process.class)
@SuppressWarnings("unused")
public class LatterProcessor extends AbstractProcessor {   //anno包配合


    @Override
    public Set<String> getSupportedAnnotationTypes() {

        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();
        for (Class<? extends Annotation> supportAnnotation : supportAnnotations) {
            types.add(supportAnnotation.getCanonicalName());
        }

        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateAppRegisiterCode(roundEnvironment);
        generateEntryCode(roundEnvironment);
        generateAppRegisiterCode(roundEnvironment);

        return true;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations(){
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppregisiterGenerator.class);
        return annotations;
    }



    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor){
        for(Element typeElement : env.getElementsAnnotatedWith(annotation)){
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement,? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()){
                    entry.getValue().accept(visitor,null);
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment env){
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env,EntryGenerator.class,entryVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment env){
        final PayEntryVisitor entryVisitor = new PayEntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env,PayEntryGenerator.class,entryVisitor);
    }

    private void generateAppRegisiterCode(RoundEnvironment env){
        final AppRegisterVisitor entryVisitor = new AppRegisterVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env,AppregisiterGenerator.class,entryVisitor);
    }



}
