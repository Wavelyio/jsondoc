package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;
import com.axonmobileiot.jsondoc.core.annotation.ApiVersion;

import java.util.List;

@ApiObject(name = "parent")
public class Parent extends Grandparent {

	@ApiObjectField(description = "the test name")
	private String name;

	@ApiObjectField(description = "the test name")
	@ApiVersion(since = "1.0", until = "2.12")
	private List<Child> children;
}
