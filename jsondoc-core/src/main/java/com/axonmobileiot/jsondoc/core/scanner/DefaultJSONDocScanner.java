package com.axonmobileiot.jsondoc.core.scanner;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.annotation.ApiBodyObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiMethod;
import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.flow.ApiFlowSet;
import com.axonmobileiot.jsondoc.core.annotation.global.ApiChangelogSet;
import com.axonmobileiot.jsondoc.core.annotation.global.ApiGlobal;
import com.axonmobileiot.jsondoc.core.annotation.global.ApiMigrationSet;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiObjectDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDocTemplate;
import com.axonmobileiot.jsondoc.core.scanner.builder.JSONDocApiDocBuilder;
import com.axonmobileiot.jsondoc.core.scanner.builder.JSONDocApiMethodDocBuilder;
import com.axonmobileiot.jsondoc.core.scanner.builder.JSONDocApiObjectDocBuilder;
import com.axonmobileiot.jsondoc.core.util.JSONDocUtils;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultJSONDocScanner extends AbstractJSONDocScanner {
	public static final String UNDEFINED = "undefined";
	public static final String ANONYMOUS = "anonymous";
	
	@Override
	public Set<Class<?>> jsondocControllers() {
		return reflections.getTypesAnnotatedWith(Api.class, true);
	}
	
	@Override
	public Set<Method> jsondocMethods(Class<?> controller) {
		Set<Method> annotatedMethods = new LinkedHashSet<>();
		for (Method method : controller.getDeclaredMethods()) {
			if(method.isAnnotationPresent(ApiMethod.class)) {
				annotatedMethods.add(method);
			}
		}
		return annotatedMethods;
	}
	
	@Override
	public Set<Class<?>> jsondocObjects(List<String> packages) {
		return reflections.getTypesAnnotatedWith(ApiObject.class, true);
	}
	
	@Override
	public Set<Class<?>> jsondocFlows() {
		return reflections.getTypesAnnotatedWith(ApiFlowSet.class, true);
	}

	@Override
	public ApiDoc initApiDoc(Class<?> controller) {
		return JSONDocApiDocBuilder.build(controller);
	}
	
	@Override
	public ApiDoc mergeApiDoc(Class<?> controller, ApiDoc apiDoc) {
		return apiDoc;
	}
	
	@Override
	public ApiMethodDoc initApiMethodDoc(Method method, Map<Class<?>, JSONDocTemplate> jsondocTemplates) {
		ApiMethodDoc apiMethodDoc = JSONDocApiMethodDocBuilder.build(method);
		
		if (method.isAnnotationPresent(ApiBodyObject.class)) {
			apiMethodDoc.getBodyobject().setJsondocTemplate(jsondocTemplates.get(method.getAnnotation(ApiBodyObject.class).clazz()));
		}
		Integer index = JSONDocUtils.getIndexOfParameterWithAnnotation(method, ApiBodyObject.class);
		if (index != -1) {
			apiMethodDoc.getBodyobject().setJsondocTemplate(jsondocTemplates.get(method.getParameterTypes()[index]));
		}
		
		return apiMethodDoc;
	}

	@Override
	public ApiMethodDoc mergeApiMethodDoc(Method method, ApiMethodDoc apiMethodDoc) {
		return apiMethodDoc;
	}

	@Override
	public ApiObjectDoc initApiObjectDoc(Class<?> clazz) {
		return JSONDocApiObjectDocBuilder.build(clazz);
	}

	@Override
	public ApiObjectDoc mergeApiObjectDoc(Class<?> clazz, ApiObjectDoc apiObjectDoc) {
		return apiObjectDoc;
	}

	@Override
	public Set<Class<?>> jsondocGlobal() {
		return reflections.getTypesAnnotatedWith(ApiGlobal.class, true);
	}

	@Override
	public Set<Class<?>> jsondocChangelogs() {
		return reflections.getTypesAnnotatedWith(ApiChangelogSet.class, true);
	}

	@Override
	public Set<Class<?>> jsondocMigrations() {
		return reflections.getTypesAnnotatedWith(ApiMigrationSet.class, true);
	}

}
