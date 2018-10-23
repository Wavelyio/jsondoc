package com.axonmobileiot.jsondoc.springintegration.scanner.builder;

import com.axonmobileiot.jsondoc.core.pojo.ApiBodyObjectDoc;
import com.axonmobileiot.jsondoc.core.util.JSONDocType;
import com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder;
import com.axonmobileiot.jsondoc.core.util.JSONDocUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Method;

public class SpringRequestBodyBuilder {

	public static ApiBodyObjectDoc buildRequestBody(Method method) {
		Integer index = JSONDocUtils.getIndexOfParameterWithAnnotation(method, RequestBody.class);
		if (index != -1) {
			return new ApiBodyObjectDoc(
					JSONDocTypeBuilder.build(new JSONDocType(), method.getParameterTypes()[index], method.getGenericParameterTypes()[index]));

		}
		
		return null;
	}
	
}
