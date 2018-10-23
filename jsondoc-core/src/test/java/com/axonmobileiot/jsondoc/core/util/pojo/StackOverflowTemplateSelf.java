package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;

public class StackOverflowTemplateSelf {
	
	@ApiObjectField
	private Integer id;
	
	@ApiObjectField
	private StackOverflowTemplateSelf ooo;
	
}
