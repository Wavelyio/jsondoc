package com.axonmobileiot.jsondoc.core.util.controller;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.annotation.ApiMethod;
import com.axonmobileiot.jsondoc.core.annotation.ApiPathParam;
import com.axonmobileiot.jsondoc.core.annotation.ApiResponseObject;
import com.axonmobileiot.jsondoc.core.pojo.ApiVerb;
import com.axonmobileiot.jsondoc.core.util.pojo.Pizza;

@Api(description = "Annotations put on an interface instead of on a concrete class", name = "interface services")
public interface PizzaController {
	
	@ApiMethod(path = "/pizzas/pizza/{id}", verb = ApiVerb.GET, produces = "application/json")
	@ApiResponseObject
    Pizza get(@ApiPathParam(name = "id") Long id);

}
