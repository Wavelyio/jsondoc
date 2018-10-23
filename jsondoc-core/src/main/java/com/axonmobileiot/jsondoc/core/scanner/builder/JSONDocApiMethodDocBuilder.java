package com.axonmobileiot.jsondoc.core.scanner.builder;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.annotation.ApiMethod;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiStage;
import com.axonmobileiot.jsondoc.core.pojo.ApiVisibility;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class JSONDocApiMethodDocBuilder {

	public static ApiMethodDoc build(Method method) {
		ApiMethod methodAnnotation = method.getAnnotation(ApiMethod.class);
		Api controllerAnnotation = method.getDeclaringClass().getAnnotation(Api.class);
		
		ApiMethodDoc apiMethodDoc = new ApiMethodDoc();
		apiMethodDoc.setId(methodAnnotation.id());
		apiMethodDoc.setPath(new LinkedHashSet<>(Arrays.asList(methodAnnotation.path())));
		apiMethodDoc.setMethod(method.getName());
		apiMethodDoc.setSummary(methodAnnotation.summary());
		apiMethodDoc.setDescription(methodAnnotation.description());
		apiMethodDoc.setVerb(new LinkedHashSet<>(Arrays.asList(methodAnnotation.verb())));
		apiMethodDoc.setConsumes(new LinkedHashSet<>(Arrays.asList(methodAnnotation.consumes())));
		apiMethodDoc.setProduces(new LinkedHashSet<>(Arrays.asList(methodAnnotation.produces())));
		apiMethodDoc.setResponsestatuscode(methodAnnotation.responsestatuscode());
		
		if(methodAnnotation.visibility().equals(ApiVisibility.UNDEFINED)) {
			apiMethodDoc.setVisibility(controllerAnnotation.visibility());
		} else {
			apiMethodDoc.setVisibility(methodAnnotation.visibility());
		}
		
		if(methodAnnotation.stage().equals(ApiStage.UNDEFINED)) {
			apiMethodDoc.setStage(controllerAnnotation.stage());
		} else {
			apiMethodDoc.setStage(methodAnnotation.stage());
		}
		
		apiMethodDoc.setHeaders(JSONDocApiHeaderDocBuilder.build(method));
		apiMethodDoc.setPathparameters(JSONDocApiPathParameterDocBuilder.build(method));
		apiMethodDoc.setQueryparameters(JSONDocApiQueryParameterDocBuilder.build(method));
		apiMethodDoc.setBodyobject(JSONDocApiBodyObjectDocBuilder.build(method));
		apiMethodDoc.setResponse(JSONDocApiResponseDocBuilder.build(method));

		return apiMethodDoc;
	}
	
}
