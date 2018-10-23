package com.axonmobileiot.jsondoc.core.scanner.builder;

import java.lang.reflect.Method;

import com.axonmobileiot.jsondoc.core.annotation.ApiResponseObject;
import com.axonmobileiot.jsondoc.core.pojo.ApiResponseObjectDoc;
import com.axonmobileiot.jsondoc.core.util.JSONDocDefaultType;
import com.axonmobileiot.jsondoc.core.util.JSONDocType;
import com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder;

public class JSONDocApiResponseDocBuilder {

	public static ApiResponseObjectDoc build(Method method) {
		if(method.isAnnotationPresent(ApiResponseObject.class)) {
			ApiResponseObject annotation = method.getAnnotation(ApiResponseObject.class);
			
			if(annotation.clazz().isAssignableFrom(JSONDocDefaultType.class)) {
				return new ApiResponseObjectDoc(JSONDocTypeBuilder.build(new JSONDocType(), method.getReturnType(), method.getGenericReturnType()));
			} else { 
				return new ApiResponseObjectDoc(JSONDocTypeBuilder.build(new JSONDocType(), annotation.clazz(), annotation.clazz()));
			}
		}
		
		return null;
	}

}
