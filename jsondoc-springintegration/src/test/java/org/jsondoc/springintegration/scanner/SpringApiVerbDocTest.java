package org.jsondoc.springintegration.scanner;

import org.jsondoc.core.pojo.ApiDoc;
import org.jsondoc.core.pojo.ApiMethodDoc;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

public class SpringApiVerbDocTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping(value = "/api-verb")
	public class SpringApiVerbController {

		@RequestMapping(value = "/spring-api-verb-controller-method-one")
		public void apiVerbOne() {

		}
		
		@RequestMapping(value = "/spring-api-verb-controller-method-two", method = { RequestMethod.POST, RequestMethod.GET })
		public void apiVerbTwo() {

		}

	}
	
	@Controller
	@RequestMapping(value = "/api-verb-2", method = { RequestMethod.POST, RequestMethod.PUT })
	public class SpringApiVerbController2 {

		@RequestMapping(value = "/spring-api-verb-controller-method-one")
		public void apiVerbOne() {

		}

	}

	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringApiVerbController.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringApiVerbController", apiDoc.getName());
		Assertions.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/api-verb/spring-api-verb-controller-method-one")) {
				Assertions.assertEquals(1, apiMethodDoc.getVerb().size());
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
			}
			if (apiMethodDoc.getPath().contains("/api-verb/spring-api-verb-controller-method-two")) {
				Assertions.assertEquals(2, apiMethodDoc.getVerb().size());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Set.of(SpringApiVerbController2.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringApiVerbController2", apiDoc.getName());
		Assertions.assertEquals(1, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/api-verb-2/spring-api-verb-controller-method-one")) {
				Assertions.assertEquals(2, apiMethodDoc.getVerb().size());
			}
		}
		
	}

}
