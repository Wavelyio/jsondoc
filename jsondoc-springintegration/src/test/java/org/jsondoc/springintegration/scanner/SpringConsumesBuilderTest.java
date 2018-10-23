package org.jsondoc.springintegration.scanner;

import java.util.Iterator;
import java.util.Set;

import org.jsondoc.core.pojo.ApiDoc;
import org.jsondoc.core.pojo.ApiMethodDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class SpringConsumesBuilderTest {

    private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

    @Controller
    @RequestMapping
    public class SpringController {

	@RequestMapping(value = "/consumes-one", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void consumesOne() {

	}

	@RequestMapping(value = "/consumes-two", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void consumesTwo() {

	}

	@RequestMapping(value = "/consumes-three")
	public void consumesThree() {

	}

    }

    @Controller
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public class SpringController2 {

	@RequestMapping(value = "/consumes-one")
	public void consumesOne() {

	}

	@RequestMapping(value = "/consumes-two", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void consumesTwo() {

	}

	@RequestMapping(value = "/consumes-three", consumes = MediaType.APPLICATION_XML_VALUE)
	public void consumesThree() {

	}

    }

    @Test
    public void testApiVerb() {
	ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), MethodDisplay.URI).iterator().next();
	Assertions.assertEquals("SpringController", apiDoc.getName());
	Assertions.assertEquals(3, apiDoc.getMethods().size());
	for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
	    if (apiMethodDoc.getPath().contains("/consumes-one")) {
		Assertions.assertEquals(1, apiMethodDoc.getConsumes().size());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getConsumes().iterator().next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-two")) {
		Assertions.assertEquals(2, apiMethodDoc.getConsumes().size());
		Iterator<String> iterator = apiMethodDoc.getConsumes().iterator();
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
		Assertions.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-three")) {
		Assertions.assertEquals(1, apiMethodDoc.getConsumes().size());
		String consumes = apiMethodDoc.getConsumes().iterator().next();
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, consumes);
	    }
	}

	apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController2.class), MethodDisplay.URI).iterator().next();
	Assertions.assertEquals("SpringController2", apiDoc.getName());
	Assertions.assertEquals(3, apiDoc.getMethods().size());
	for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
	    if (apiMethodDoc.getPath().contains("/consumes-one")) {
		Assertions.assertEquals(1, apiMethodDoc.getConsumes().size());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getConsumes().iterator().next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-two")) {
		Assertions.assertEquals(2, apiMethodDoc.getConsumes().size());
		Iterator<String> iterator = apiMethodDoc.getConsumes().iterator();
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
		Assertions.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-three")) {
		Assertions.assertEquals(1, apiMethodDoc.getConsumes().size());
		Assertions.assertEquals(MediaType.APPLICATION_XML_VALUE, apiMethodDoc.getConsumes().iterator().next());
	    }
	}
    }

}
