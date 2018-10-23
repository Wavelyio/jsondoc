package com.axonmobileiot.jsondoc.springintegration.scanner.builder;

import com.axonmobileiot.jsondoc.core.annotation.ApiPathParam;
import com.axonmobileiot.jsondoc.core.pojo.ApiParamDoc;
import com.axonmobileiot.jsondoc.core.util.JSONDocType;
import com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public class SpringPathVariableBuilder {

	public static Set<ApiParamDoc> buildPathVariable(Method method) {
		Set<ApiParamDoc> apiParamDocs = new LinkedHashSet<>();

		Annotation[][] parametersAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parametersAnnotations.length; i++) {
			PathVariable pathVariable = null;
			ApiPathParam apiPathParam = null;
			ApiParamDoc apiParamDoc = null;

			for (int j = 0; j < parametersAnnotations[i].length; j++) {
				if (parametersAnnotations[i][j] instanceof PathVariable) {
					pathVariable = (PathVariable) parametersAnnotations[i][j];
				}
				if (parametersAnnotations[i][j] instanceof ApiPathParam) {
					apiPathParam = (ApiPathParam) parametersAnnotations[i][j];
				}

				if (pathVariable != null) {
					apiParamDoc = new ApiParamDoc(pathVariable.value(), "", JSONDocTypeBuilder.build(new JSONDocType(), method.getParameterTypes()[i], method.getGenericParameterTypes()[i]), "true", new String[] {}, null, "");
					mergeApiPathParamDoc(apiPathParam, apiParamDoc);
				}
			}

			if (apiParamDoc != null) {
				apiParamDocs.add(apiParamDoc);
			}
		}

		return apiParamDocs;
	}

	/**
	 * Available properties that can be overridden: name, description,
	 * allowedvalues, format. Name is overridden only if it's empty in the
	 * apiParamDoc argument. Description, format and allowedvalues are copied in
	 * any case.
	 * 
	 * @param apiPathParam
	 * @param apiParamDoc
	 */
	private static void mergeApiPathParamDoc(ApiPathParam apiPathParam, ApiParamDoc apiParamDoc) {
		if (apiPathParam != null) {
			if (apiParamDoc.getName().trim().isEmpty()) {
				apiParamDoc.setName(apiPathParam.name());
			}
			apiParamDoc.setDescription(apiPathParam.description());
			apiParamDoc.setAllowedvalues(apiPathParam.allowedvalues());
			apiParamDoc.setFormat(apiPathParam.format());
		}
	}

}
