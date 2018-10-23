package com.axonmobileiot.jsondoc.core.pojo;

import com.axonmobileiot.jsondoc.core.util.JSONDocType;

import java.util.UUID;

public class ApiBodyObjectDoc {
	public final String jsondocId = UUID.randomUUID().toString();
	private JSONDocType jsondocType;
	private JSONDocTemplate jsondocTemplate;

	public ApiBodyObjectDoc(JSONDocType jsondocType) {
		this.jsondocType = jsondocType;
	}

	public JSONDocType getJsondocType() {
		return jsondocType;
	}

	public JSONDocTemplate getJsondocTemplate() {
		return jsondocTemplate;
	}

	public void setJsondocTemplate(JSONDocTemplate jsondocTemplate) {
		this.jsondocTemplate = jsondocTemplate;
	}

	public void setJsondocType(JSONDocType jsondocType) {
		this.jsondocType = jsondocType;
	}

}
