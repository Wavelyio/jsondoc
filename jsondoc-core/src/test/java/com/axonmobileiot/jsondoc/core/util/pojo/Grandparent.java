package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;
import com.axonmobileiot.jsondoc.core.annotation.ApiVersion;

import java.util.Date;

@ApiObject(name = "grandparent", show = false)
public class Grandparent {

	@ApiObjectField(description = "the test surname")
	@ApiVersion(since = "1.0")
	private String surname;

	@ApiObjectField(description = "the date of birth", format = "yyyy-MM-dd HH:mm:ss")
	private Date dob;

}
