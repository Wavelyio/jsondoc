package com.axonmobileiot.jsondoc.springintegration.issues.issue151;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FooController {

	@RequestMapping("/")
	public FooWrapper<BarPojo> getBar() {
		return null;
	}
	
	@RequestMapping("/wildcard")
	public FooWrapper<?> wildcard() {
		return null;
	}

}