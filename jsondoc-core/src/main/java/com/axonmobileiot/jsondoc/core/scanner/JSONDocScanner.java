package com.axonmobileiot.jsondoc.core.scanner;

import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiObjectDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.pojo.flow.ApiFlowDoc;
import com.axonmobileiot.jsondoc.core.pojo.global.ApiGlobalDoc;

import java.util.List;
import java.util.Set;


public interface JSONDocScanner {
	
	JSONDoc getJSONDoc(String version, String basePath, List<String> packages, boolean playgroundEnabled, JSONDoc.MethodDisplay methodDisplay);

	Set<ApiDoc> getApiDocs(Set<Class<?>> classes, JSONDoc.MethodDisplay displayMethodAs);
	
	Set<ApiObjectDoc> getApiObjectDocs(Set<Class<?>> classes);

	Set<ApiFlowDoc> getApiFlowDocs(Set<Class<?>> classes, List<ApiMethodDoc> apiMethodDocs);

	ApiGlobalDoc getApiGlobalDoc(Set<Class<?>> global, Set<Class<?>> changelogs, Set<Class<?>> migrations);
	
}
