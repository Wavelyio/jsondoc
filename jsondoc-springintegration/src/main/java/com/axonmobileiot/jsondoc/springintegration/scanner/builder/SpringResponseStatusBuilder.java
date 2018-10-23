package com.axonmobileiot.jsondoc.springintegration.scanner.builder;

import com.axonmobileiot.jsondoc.springintegration.scanner.SpringBuilderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Method;

public class SpringResponseStatusBuilder {

	public static String buildResponseStatusCode(Method method) {
		if (SpringBuilderUtils.isAnnotated(method, ResponseStatus.class)) {
			ResponseStatus responseStatus = SpringBuilderUtils.getAnnotation(method, ResponseStatus.class);
			return responseStatus.value().toString() + " - " + responseStatus.value().getReasonPhrase();
		} else {
			return HttpStatus.OK.toString() + " - " + "OK";
		}
	}

}
