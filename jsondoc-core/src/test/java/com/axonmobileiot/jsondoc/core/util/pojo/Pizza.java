package com.axonmobileiot.jsondoc.core.util.pojo;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "customPizzaObject", group = "Restaurant")
public class Pizza extends Parent {

	@ApiObjectField(description = "the cost of this pizza")
	private Float price;

	@ApiObjectField(description = "the topping of this pizza")
	private String[] topping;

}
