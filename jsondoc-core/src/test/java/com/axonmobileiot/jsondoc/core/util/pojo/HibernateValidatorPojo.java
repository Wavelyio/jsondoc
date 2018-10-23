package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;

@ApiObject
public class HibernateValidatorPojo {
	
	@ApiObjectField(format = "a not empty id")
	@Length(min = 2)
	@Max(9)
	private String id;

}
