package com.axonmobileiot.jsondoc.core.issues.issue151;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.annotation.ApiMethod;
import com.axonmobileiot.jsondoc.core.annotation.ApiResponseObject;

@Api(name = "Foo Services", description = "bla, bla, bla ...", group = "foogroup")
public class FooController {

	@ApiMethod(path = { "/api/foo" }, description = "Main foo service")
	@ApiResponseObject
	public FooWrapper<BarPojo> getBar() {
		return null;
	}
	
	@ApiMethod(path = { "/api/foo-wildcard" }, description = "Main foo service with wildcard")
	@ApiResponseObject
	public FooWrapper<?> wildcard() {
		return null;
	}

}