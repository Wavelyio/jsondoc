package com.axonmobileiot.jsondoc.springintegration.issues.invisible;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "another resource implementation")
public class AnotherResourceImplementation implements ResourceInterface {
	
	@ApiObjectField(name = "resource id")
	private String id;

	@Override
	public String getId() {
		return this.id;
	}

}
