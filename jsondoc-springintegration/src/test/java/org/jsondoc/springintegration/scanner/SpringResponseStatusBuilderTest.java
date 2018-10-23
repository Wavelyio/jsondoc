package org.jsondoc.springintegration.scanner;

import org.jsondoc.core.pojo.ApiDoc;
import org.jsondoc.core.pojo.ApiMethodDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

public class SpringResponseStatusBuilderTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping
	public class SpringController {

		@RequestMapping(value = "/status-one")
		@ResponseStatus(value = HttpStatus.CREATED)
		public String statusOne() {
			return "";
		}
		
		@RequestMapping(value = "/status-two")
		public void statusTwo() {
			
		}
		
	}
	
	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getName());
		Assertions.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/status-one")) {
				Assertions.assertEquals("201 - Created", apiMethodDoc.getResponsestatuscode());
			}
			if (apiMethodDoc.getPath().contains("/status-two")) {
				Assertions.assertEquals("200 - OK", apiMethodDoc.getResponsestatuscode());
			}
		}
	}

}
