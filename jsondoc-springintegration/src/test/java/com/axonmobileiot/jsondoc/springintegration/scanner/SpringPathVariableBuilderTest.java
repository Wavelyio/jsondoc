package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.annotation.ApiPathParam;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiParamDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.Set;

public class SpringPathVariableBuilderTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping
	public class SpringController {

		@RequestMapping(value = "/param-one/{id}/{string}")
		public void paramOne(@PathVariable Long id, @PathVariable("name") String name) {

		}
		
		@RequestMapping(value = "/param-one/{id}/{string}/{test}")
		public void paramTwo(@ApiPathParam(name = "id", description = "my description") @PathVariable Long id, @PathVariable("name") String name, @PathVariable @ApiPathParam Long test) {

		}

		
	}
	
	@Controller
	@RequestMapping
	public class SpringController2 {

		@RequestMapping(value = "/param-one/{id}/{string}")
		public void paramOne(@ApiPathParam(description = "description for id") @PathVariable Long id, @PathVariable("name") String name) {

		}
		
	}
	
	@Test
	public void testPathVariable() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getName());
		Assertions.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one/{id}/{string}")) {
				Assertions.assertEquals(2, apiMethodDoc.getPathparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getPathparameters().iterator();
				ApiParamDoc id = iterator.next();
				Assertions.assertEquals("", id.getName());
				Assertions.assertEquals("long", id.getJsondocType().getOneLineText());
				ApiParamDoc name = iterator.next();
				Assertions.assertEquals("name", name.getName());
				Assertions.assertEquals("string", name.getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/param-one/{id}/{string}/{test}")) {
				Assertions.assertEquals(3, apiMethodDoc.getPathparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getPathparameters().iterator();
				ApiParamDoc id = iterator.next();
				Assertions.assertEquals("id", id.getName());
				Assertions.assertEquals("long", id.getJsondocType().getOneLineText());
				ApiParamDoc name = iterator.next();
				Assertions.assertEquals("name", name.getName());
				Assertions.assertEquals("string", name.getJsondocType().getOneLineText());
				ApiParamDoc test = iterator.next();
				Assertions.assertEquals("", test.getName());
				Assertions.assertEquals("long", test.getJsondocType().getOneLineText());
			}
		}
		
	}
	
	@Test
	public void testPathVariableWithJSONDoc() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController2.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController2", apiDoc.getName());
		Assertions.assertEquals(1, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one/{id}/{string}")) {
				Assertions.assertEquals(2, apiMethodDoc.getPathparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getPathparameters().iterator();
				ApiParamDoc id = iterator.next();
				Assertions.assertEquals("", id.getName());
				Assertions.assertEquals("long", id.getJsondocType().getOneLineText());
				Assertions.assertEquals("description for id", id.getDescription());
				ApiParamDoc name = iterator.next();
				Assertions.assertEquals("name", name.getName());
				Assertions.assertEquals("string", name.getJsondocType().getOneLineText());
			}
		}
		
	}

}
