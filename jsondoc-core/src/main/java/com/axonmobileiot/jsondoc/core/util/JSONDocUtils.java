package com.axonmobileiot.jsondoc.core.util;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class JSONDocUtils {
	
	public static Integer getIndexOfParameterWithAnnotation(Method method, Class<?> a) {
		Annotation[][] parametersAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parametersAnnotations.length; i++) {
			for (int j = 0; j < parametersAnnotations[i].length; j++) {
				if(a.equals(parametersAnnotations[i][j].annotationType())) {
					return i;
				}
			}
		}
		return -1;
	}

	public static String getCustomClassName(Class<?> clazz) {
		if(clazz.isAnnotationPresent(ApiObject.class)) {
			ApiObject annotation = clazz.getAnnotation(ApiObject.class);
			if(annotation.name().isEmpty()) {
				return clazz.getSimpleName();
			} else {
				return annotation.name();
			}
		} else {
			return clazz.getSimpleName();
		}
	}

}
