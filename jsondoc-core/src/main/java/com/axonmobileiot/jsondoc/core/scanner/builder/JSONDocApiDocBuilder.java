package com.axonmobileiot.jsondoc.core.scanner.builder;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;

public class JSONDocApiDocBuilder {
	
	public static ApiDoc build(Class<?> controller) {
		Api api = controller.getAnnotation(Api.class);
		ApiDoc apiDoc = new ApiDoc();
		apiDoc.setDescription(api.description());
		apiDoc.setName(api.name());
		apiDoc.setGroup(api.group());
		apiDoc.setVisibility(api.visibility());
		apiDoc.setStage(api.stage());
		return apiDoc;
	}

}
