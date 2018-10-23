package com.axonmobileiot.jsondoc.core.scanner.builder;

import java.lang.reflect.Method;

import com.axonmobileiot.jsondoc.core.annotation.ApiBodyObject;
import com.axonmobileiot.jsondoc.core.pojo.ApiBodyObjectDoc;
import com.axonmobileiot.jsondoc.core.util.JSONDocType;
import com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder;
import com.axonmobileiot.jsondoc.core.util.JSONDocUtils;

public class JSONDocApiBodyObjectDocBuilder {

	public static ApiBodyObjectDoc build(Method method) {
		if (method.isAnnotationPresent(ApiBodyObject.class)) {
			ApiBodyObjectDoc apiBodyObjectDoc = new ApiBodyObjectDoc(JSONDocTypeBuilder.build(new JSONDocType(), method.getAnnotation(ApiBodyObject.class).clazz(), method.getAnnotation(ApiBodyObject.class).clazz()));
			return apiBodyObjectDoc;
		}

		Integer index = JSONDocUtils.getIndexOfParameterWithAnnotation(method, ApiBodyObject.class);
		if (index != -1) {
			ApiBodyObjectDoc apiBodyObjectDoc = new ApiBodyObjectDoc(JSONDocTypeBuilder.build(new JSONDocType(), method.getParameterTypes()[index], method.getGenericParameterTypes()[index]));
			return apiBodyObjectDoc;
		}
		
		return null;
	}

}
