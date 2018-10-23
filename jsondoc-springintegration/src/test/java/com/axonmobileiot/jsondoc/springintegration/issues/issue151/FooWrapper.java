package com.axonmobileiot.jsondoc.springintegration.issues.issue151;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;

@ApiObject(group = "foogroup", description = "Foo description")
public class FooWrapper<T> {

	@ApiObjectField(description = "The wrapper's content")
	private T content;

}
