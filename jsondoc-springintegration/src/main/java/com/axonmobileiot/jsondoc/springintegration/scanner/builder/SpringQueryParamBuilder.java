package com.axonmobileiot.jsondoc.springintegration.scanner.builder;

import com.axonmobileiot.jsondoc.core.annotation.ApiQueryParam;
import com.axonmobileiot.jsondoc.core.pojo.ApiParamDoc;
import com.axonmobileiot.jsondoc.core.util.JSONDocType;
import com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder;
import com.axonmobileiot.jsondoc.springintegration.scanner.SpringBuilderUtils;
import com.axonmobileiot.jsondoc.springintegration.scanner.constants.SupportedMappingConstants;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ValueConstants;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public class SpringQueryParamBuilder {

	/**
	 * From Spring's documentation: Supported at the type level as well as at
	 * the method level! When used at the type level, all method-level mappings
	 * inherit this parameter restriction
	 * 
	 * @param method
	 * @return
	 */
	public static Set<ApiParamDoc> buildQueryParams(Method method) {
		Set<ApiParamDoc> apiParamDocs = new LinkedHashSet<>();
		Class<?> controller = method.getDeclaringClass();

		if (SpringBuilderUtils.isAnnotated(controller, RequestMapping.class)) {
			RequestMapping requestMapping = SpringBuilderUtils.getAnnotation(controller, RequestMapping.class);
			addRequestMappingParamDoc(apiParamDocs, requestMapping);
		}

		if (SpringBuilderUtils.isAnnotatedWithAny(method, SupportedMappingConstants.mappingAnnotations)) {
			RequestMapping requestMapping = SpringBuilderUtils.getAnnotation(method, RequestMapping.class);
			addRequestMappingParamDoc(apiParamDocs, requestMapping);
		}

		Annotation[][] parametersAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parametersAnnotations.length; i++) {
			RequestParam requestParam = null;
			ModelAttribute modelAttribute = null;
			ApiQueryParam apiQueryParam = null;
			PageableDefault pageableDefault = null;
			ApiParamDoc apiParamDoc = null;
			
			for (Annotation annotation : parametersAnnotations[i]) {
				if (annotation instanceof RequestParam) {
					requestParam = (RequestParam) annotation;
				}
				if (annotation instanceof PageableDefault) {
					pageableDefault = (PageableDefault) annotation;
				}
				if(annotation instanceof ModelAttribute) {
					modelAttribute = (ModelAttribute) annotation;
				}
				if (annotation instanceof ApiQueryParam) {
					apiQueryParam = (ApiQueryParam) annotation;
				}
				
				if (requestParam != null) {
					apiParamDoc = new ApiParamDoc(requestParam.value().isEmpty() ? requestParam.name() : requestParam.value(), "", JSONDocTypeBuilder.build(new JSONDocType(), method.getParameterTypes()[i], method.getGenericParameterTypes()[i]), String.valueOf(requestParam.required()), new String[] {}, null, requestParam.defaultValue().equals(ValueConstants.DEFAULT_NONE) ? "" : requestParam.defaultValue());
					mergeApiQueryParamDoc(apiQueryParam, apiParamDoc);
				}
				if(modelAttribute != null) {
					apiParamDoc = new ApiParamDoc(modelAttribute.value(), "", JSONDocTypeBuilder.build(new JSONDocType(), method.getParameterTypes()[i], method.getGenericParameterTypes()[i]), "false", new String[] {}, null, null);
					mergeApiQueryParamDoc(apiQueryParam, apiParamDoc);
				}
				if (pageableDefault != null) {
					apiParamDoc = new ApiParamDoc("Pageable", "This will allow", JSONDocTypeBuilder.build(new JSONDocType(), method.getParameterTypes()[i], method.getGenericParameterTypes()[i]), "false", new String[] {"size, page, sort"}, null, "Size: " +pageableDefault.size());
					mergeApiQueryParamDoc(apiQueryParam, apiParamDoc);
				}
				
			}
			
			if(apiParamDoc != null) {
				apiParamDocs.add(apiParamDoc);
			}
		}
		
		return apiParamDocs;
	}

	/**
	 * Checks the request mapping annotation value and adds the resulting @ApiParamDoc to the documentation
	 * @param apiParamDocs
	 * @param requestMapping
	 */
	private static void addRequestMappingParamDoc(Set<ApiParamDoc> apiParamDocs, RequestMapping requestMapping) {
		if (requestMapping.params().length > 0) {
            for (String param : requestMapping.params()) {
                String[] splitParam = param.split("=");
                if (splitParam.length > 1) {
                    apiParamDocs.add(new ApiParamDoc(splitParam[0], "", JSONDocTypeBuilder.build(new JSONDocType(), String.class, null), "true", new String[] { splitParam[1] }, null, null));
                } else {
                    apiParamDocs.add(new ApiParamDoc(param, "", JSONDocTypeBuilder.build(new JSONDocType(), String.class, null), "true", new String[] {}, null, null));
                }
            }
        }
	}

	/**
	 * Available properties that can be overridden: name, description, required,
	 * allowedvalues, format, defaultvalue. Name is overridden only if it's empty
	 * in the apiParamDoc argument. Description, format and allowedvalues are
	 * copied in any case Default value and required are not overridden: in any
	 * case they are coming from the default values of @RequestParam
	 * 
	 * @param apiQueryParam
	 * @param apiParamDoc
	 */
	private static void mergeApiQueryParamDoc(ApiQueryParam apiQueryParam, ApiParamDoc apiParamDoc) {
		if (apiQueryParam != null) {
			if (apiParamDoc.getName().trim().isEmpty()) {
				apiParamDoc.setName(apiQueryParam.name());
			}
			apiParamDoc.setDescription(apiQueryParam.description());
			apiParamDoc.setAllowedvalues(apiQueryParam.allowedvalues());
			apiParamDoc.setFormat(apiQueryParam.format());
		}
	}

}
