package com.axonmobileiot.jsondoc.core.scanner.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

import com.axonmobileiot.jsondoc.core.annotation.ApiParams;
import com.axonmobileiot.jsondoc.core.annotation.ApiPathParam;
import com.axonmobileiot.jsondoc.core.pojo.ApiParamDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiParamType;
import com.axonmobileiot.jsondoc.core.util.JSONDocType;
import com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder;

public class JSONDocApiPathParameterDocBuilder {
	
	public static Set<ApiParamDoc> build(Method method) {
		Set<ApiParamDoc> docs = new LinkedHashSet<>();

		if (method.isAnnotationPresent(ApiParams.class)) {
			for (ApiPathParam apiParam : method.getAnnotation(ApiParams.class).pathparams()) {
				ApiParamDoc apiParamDoc = ApiParamDoc.buildFromAnnotation(apiParam, JSONDocTypeBuilder.build(new JSONDocType(), apiParam.clazz(), apiParam.clazz()), ApiParamType.PATH);
				docs.add(apiParamDoc);
			}
		}

		Annotation[][] parametersAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parametersAnnotations.length; i++) {
			for (int j = 0; j < parametersAnnotations[i].length; j++) {
				if (parametersAnnotations[i][j] instanceof ApiPathParam) {
					ApiPathParam annotation = (ApiPathParam) parametersAnnotations[i][j];
					ApiParamDoc apiParamDoc = ApiParamDoc.buildFromAnnotation(annotation, JSONDocTypeBuilder.build(new JSONDocType(), method.getParameterTypes()[i], method.getGenericParameterTypes()[i]), ApiParamType.PATH);
					docs.add(apiParamDoc);
				}
			}
		}

		return docs;
	}

}
