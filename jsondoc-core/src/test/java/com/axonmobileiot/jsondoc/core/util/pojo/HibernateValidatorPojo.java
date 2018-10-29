package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@ApiObject
public class HibernateValidatorPojo {
	
	@ApiObjectField(format = "a not empty id")
	@Size(min = 2)
	@Max(9)
	private String id;

}
