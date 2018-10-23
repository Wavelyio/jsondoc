package com.axonmobileiot.jsondoc.core.scanner.validator;

import com.axonmobileiot.jsondoc.core.pojo.ApiObjectDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiObjectFieldDoc;

public class JSONDocApiObjectDocValidator {
	
	public static ApiObjectDoc validateApiObjectDoc(ApiObjectDoc apiObjectDoc) {
		final String HINT_MISSING_API_OBJECT_FIELD_DESCRIPTION = "Add description to field: %s";

		for (ApiObjectFieldDoc apiObjectFieldDoc : apiObjectDoc.getFields()) {
			if (apiObjectFieldDoc.getDescription() == null || apiObjectFieldDoc.getDescription().trim().isEmpty()) {
				apiObjectDoc.addJsondochint(String.format(HINT_MISSING_API_OBJECT_FIELD_DESCRIPTION, apiObjectFieldDoc.getName()));
			}
		}
		return apiObjectDoc;
	}

}
