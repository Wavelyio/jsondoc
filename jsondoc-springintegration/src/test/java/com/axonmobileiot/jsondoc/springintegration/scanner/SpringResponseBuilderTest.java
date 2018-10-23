package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Set;

public class SpringResponseBuilderTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping
	public class SpringController {

		@RequestMapping(value = "/response-one")
		public String string() {
			return "";
		}
		
		@RequestMapping(value = "/response-two")
		public ResponseEntity<String> responseEntityString() {
			return ResponseEntity.ok("");
		}
		
		@RequestMapping(value = "/response-three")
		public ResponseEntity<Map<String,Integer>> responseEntityMap() {
			return ResponseEntity.ok(null);
		}
		
	}
	
	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getName());
		Assertions.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/response-one")) {
				Assertions.assertEquals("string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			if (apiMethodDoc.getPath().contains("/response-two")) {
				Assertions.assertEquals("string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			if (apiMethodDoc.getPath().contains("/response-three")) {
				Assertions.assertEquals("map[string, integer]", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
		}
	}

}
