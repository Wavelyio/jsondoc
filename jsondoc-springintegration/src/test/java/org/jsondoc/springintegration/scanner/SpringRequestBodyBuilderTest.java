package org.jsondoc.springintegration.scanner;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiDoc;
import org.jsondoc.core.pojo.ApiMethodDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

public class SpringRequestBodyBuilderTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping
	public class SpringController {

		@RequestMapping(value = "/body-one")
		public void bodyOne(@RequestBody String string) {

		}
		
		@RequestMapping(value = "/body-two")
		public void bodyTwo(@RequestBody Body body) {

		}
		
	}
	
	@ApiObject
	private class Body {
		@ApiObjectField
		private String name;
		@ApiObjectField
		private Integer age;
	}
	
	@Test
	public void testBodyOne() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getName());
		Assertions.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/body-one")) {
				Assertions.assertNotNull(apiMethodDoc.getBodyobject());
				Assertions.assertEquals("string", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
			}
			if (apiMethodDoc.getPath().contains("/body-two")) {
				Assertions.assertNotNull(apiMethodDoc.getBodyobject());
				Assertions.assertEquals("body", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
			}
		}
	}

}
