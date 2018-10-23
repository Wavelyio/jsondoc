package com.axonmobileiot.jsondoc.core.scanner.builder;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

import com.axonmobileiot.jsondoc.core.annotation.ApiHeader;
import com.axonmobileiot.jsondoc.core.annotation.ApiHeaders;
import com.axonmobileiot.jsondoc.core.pojo.ApiHeaderDoc;

public class JSONDocApiHeaderDocBuilder {
	
	public static Set<ApiHeaderDoc> build(Method method) {
		Set<ApiHeaderDoc> docs = new LinkedHashSet<>();
		
		ApiHeaders methodAnnotation = method.getAnnotation(ApiHeaders.class);
		ApiHeaders typeAnnotation = method.getDeclaringClass().getAnnotation(ApiHeaders.class);
		
		if(typeAnnotation != null) {
			for (ApiHeader apiHeader : typeAnnotation.headers()) {
				docs.add(new ApiHeaderDoc(apiHeader.name(), apiHeader.description(), apiHeader.allowedvalues()));
			}
		}

		if (methodAnnotation != null) {
			for (ApiHeader apiHeader : methodAnnotation.headers()) {
				docs.add(new ApiHeaderDoc(apiHeader.name(), apiHeader.description(), apiHeader.allowedvalues()));
			}
		}
		
		return docs;
	}
	
}
