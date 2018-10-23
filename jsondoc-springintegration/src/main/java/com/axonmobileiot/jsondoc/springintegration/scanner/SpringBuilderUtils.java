package com.axonmobileiot.jsondoc.springintegration.scanner;

import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class SpringBuilderUtils {

    public static boolean isAnnotated(Class<?> clazz, Class<? extends Annotation> annotation) {
        return AnnotatedElementUtils.isAnnotated(clazz, annotation);
    }

    public static boolean isAnnotated(Method method, Class<? extends Annotation> annotation) {
        return AnnotatedElementUtils.isAnnotated(method, annotation);
    }

    public static boolean isAnnotatedWithAny(Method method, Set<Class<? extends Annotation>> annotations) {
        return annotations.stream().anyMatch(annotation -> SpringBuilderUtils.isAnnotated(method, annotation));
    }

    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotation) {
        return AnnotatedElementUtils.getMergedAnnotation(clazz, annotation);
    }

    public static <T extends Annotation> T getAnnotation(Method method, Class<T> annotation) {
        return AnnotatedElementUtils.getMergedAnnotation(method, annotation);
    }

}
