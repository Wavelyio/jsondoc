package com.axonmobileiot.jsondoc.core.util.controller;

import com.axonmobileiot.jsondoc.core.util.pojo.Pizza;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

public class PizzaControllerImpl implements PizzaController {

	@Override
	public @ResponseBody
    Pizza get(@PathVariable Long id) {
		return null;
	}

}
