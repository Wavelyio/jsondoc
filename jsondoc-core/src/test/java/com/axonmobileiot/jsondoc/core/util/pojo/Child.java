package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;
import com.axonmobileiot.jsondoc.core.annotation.ApiVersion;

import java.util.Map;

@ApiObject(name = "child")
public class Child extends Parent {

	@ApiObjectField(description = "the test age")
	@ApiVersion(since = "1.0")
	private Integer age;

	@ApiObjectField(description = "the test games")
	private Long[] games;

	@ApiObjectField(description = "the scores for each game")
	private Map<String, Integer> scores;
	
	@ApiObjectField(name = "gender", description = "the gender of this person")
	@ApiVersion(since = "1.2")
	private Gender gender;

}
