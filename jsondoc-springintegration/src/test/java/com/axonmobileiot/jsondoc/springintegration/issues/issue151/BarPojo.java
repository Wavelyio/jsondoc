package com.axonmobileiot.jsondoc.springintegration.issues.issue151;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;

@ApiObject(group = "bargroup", description = "Bar description")
public class BarPojo {

	@ApiObjectField(description = "Bar description")
	private String barField;

}
