package com.axonmobileiot.jsondoc.springintegration.scanner.builder;

import com.axonmobileiot.jsondoc.core.pojo.ApiResponseObjectDoc;
import com.axonmobileiot.jsondoc.core.util.JSONDocType;
import com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder;
import com.axonmobileiot.jsondoc.core.util.JSONDocUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.*;
import java.util.Collection;

import static com.axonmobileiot.jsondoc.core.util.JSONDocTypeBuilder.WILDCARD;

public class SpringResponseBuilder {

	/**
	 * Builds the ApiResponseObjectDoc from the method's return type and checks if the first type corresponds to a ResponseEntity class. In that case removes the "responseentity"
	 * string from the final list because it's not important to the documentation user.
	 * @param method
	 * @return
	 */
	public static ApiResponseObjectDoc buildResponse(Method method) {
		return new ApiResponseObjectDoc(build(new JSONDocType(), method.getReturnType(), method.getGenericReturnType()));
	}

	@SuppressWarnings("Duplicates")
	public static JSONDocType build(JSONDocType jsondocType, Class<?> clazz, Type type) {
		if (type instanceof ParameterizedType && ResponseEntity.class.isAssignableFrom(clazz) && clazz.getTypeParameters().length > 0) {
			Type parametrizedType = ((ParameterizedType) type).getActualTypeArguments()[0];
			if (parametrizedType instanceof Class) {
				jsondocType.addItemToType(JSONDocUtils.getCustomClassName((Class<?>) parametrizedType));
			} else {
				build(jsondocType, (Class<?>) ((ParameterizedType) parametrizedType).getRawType(), parametrizedType);
			}
		} else if (Page.class.isAssignableFrom(clazz)) {
			if (type instanceof ParameterizedType) {
				Type parametrizedType = ((ParameterizedType) type).getActualTypeArguments()[0];
				jsondocType.addItemToType(JSONDocUtils.getCustomClassName(clazz));
				if (parametrizedType instanceof Class) {
					jsondocType.addItemToType(JSONDocUtils.getCustomClassName((Class<?>) parametrizedType));
				} else if (parametrizedType instanceof WildcardType) {
					jsondocType.addItemToType(WILDCARD);
				} else if(parametrizedType instanceof TypeVariable<?>){
					jsondocType.addItemToType(((TypeVariable<?>) parametrizedType).getName());
				} else {
					return build(jsondocType, (Class<?>) ((ParameterizedType) parametrizedType).getRawType(), parametrizedType);
				}
			} else if (type instanceof GenericArrayType) {
				return build(jsondocType, clazz, ((GenericArrayType) type).getGenericComponentType());
			} else {
				jsondocType.addItemToType(JSONDocUtils.getCustomClassName(clazz));
			}
		} else {
			JSONDocTypeBuilder.build(jsondocType, clazz, type);
		}
		return jsondocType;
	}
}
