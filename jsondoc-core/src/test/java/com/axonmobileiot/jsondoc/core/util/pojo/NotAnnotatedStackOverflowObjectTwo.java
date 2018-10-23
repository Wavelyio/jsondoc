package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;


public class NotAnnotatedStackOverflowObjectTwo {

	private Long id;

	private String name;
	
	@ApiObjectField(processtemplate = false)
	private NotAnnotatedStackOverflowObjectOne typeOne;

}
