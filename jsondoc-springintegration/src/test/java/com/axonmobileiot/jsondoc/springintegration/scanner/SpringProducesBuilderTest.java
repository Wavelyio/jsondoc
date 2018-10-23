package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.Set;

public class SpringProducesBuilderTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping
	public class SpringController {

		@RequestMapping(value = "/produces-one", produces = MediaType.APPLICATION_JSON_VALUE)
		public void producesOne() {

		}
		
		@RequestMapping(value = "/produces-two", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public void producesTwo() {

		}
		
		@RequestMapping(value = "/produces-three")
		public void producesThree() {

		}

	}
	
	@Controller
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public class SpringController2 {

		@RequestMapping(value = "/produces-one")
		public void producesOne() {

		}
		
		@RequestMapping(value = "/produces-two", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public void producesTwo() {

		}
		
		@RequestMapping(value = "/produces-three", produces = MediaType.APPLICATION_XML_VALUE)
		public void producesThree() {

		}

	}

	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getName());
		Assertions.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/produces-one")) {
				Assertions.assertEquals(1, apiMethodDoc.getProduces().size());
				Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getProduces().iterator().next());
			}
			if (apiMethodDoc.getPath().contains("/produces-two")) {
				Assertions.assertEquals(2, apiMethodDoc.getProduces().size());
				Iterator<String> iterator = apiMethodDoc.getProduces().iterator();
				Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
				Assertions.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
			}
			if (apiMethodDoc.getPath().contains("/produces-three")) {
				Assertions.assertEquals(1, apiMethodDoc.getProduces().size());
				String produces = apiMethodDoc.getProduces().iterator().next();
				Assertions.assertEquals("application/json", produces);
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController2.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController2", apiDoc.getName());
		Assertions.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/produces-one")) {
				Assertions.assertEquals(1, apiMethodDoc.getProduces().size());
				Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getProduces().iterator().next());
			}
			if (apiMethodDoc.getPath().contains("/produces-two")) {
				Assertions.assertEquals(2, apiMethodDoc.getProduces().size());
				Iterator<String> iterator = apiMethodDoc.getProduces().iterator();
				Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
				Assertions.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
			}
			if (apiMethodDoc.getPath().contains("/produces-three")) {
				Assertions.assertEquals(1, apiMethodDoc.getProduces().size());
				Assertions.assertEquals(MediaType.APPLICATION_XML_VALUE, apiMethodDoc.getProduces().iterator().next());
			}
		}
	}

}
