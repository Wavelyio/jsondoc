package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.annotation.ApiQueryParam;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiParamDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.Set;

public class SpringQueryParamBuilderTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping
	public class SpringController {

		@RequestMapping(value = "/param-one", params = "param")
		public void paramOne() {

		}
		
		@RequestMapping(value = "/param-two", params = { "param", "param2" })
		public void paramTwo() {

		}
		
		@RequestMapping(value = "/param-three", params = { "param=value", "param2=value2" })
		public void paramThree() {

		}
		
	}
	
	@Controller
	@RequestMapping(params = { "param", "param2=value2" })
	public class SpringController2 {

		@RequestMapping(value = "/param-one")
		public void paramOne() {

		}
		
		@RequestMapping(value = "/param-two", params = "param3")
		public void paramTwo() {

		}
		
	}
	
	@Controller
	@RequestMapping(params = "param")
	public class SpringController3 {

		@RequestMapping(value = "/param-one")
		public void paramOne(@RequestParam(value = "name") String name) {

		}
		
		@RequestMapping(value = "/param-two")
		public void paramTwo(@RequestParam(value = "name", defaultValue = "test", required = false) String name) {

		}
		
		@RequestMapping(value = "/param-three")
		public void paramThree(@RequestParam String name) {

		}

		@RequestMapping(value = "/param-four")
		public void paramFour(@RequestParam(name = "value", required = false) String value) {

		}
		
	}
	
	@Controller
	@RequestMapping
	public class SpringController4 {
		
		@RequestMapping(value = "/")
		public void paramOne(@RequestParam @ApiQueryParam(name = "name") String name) {
			
		}
		
		@RequestMapping(value = "/two")
		public void paramOne(@RequestParam @ApiQueryParam(name = "name") String name, @RequestParam @ApiQueryParam(name = "test") String test) {
			
		}
		
	}
	
	@Controller
	@RequestMapping
	public class SpringController5 {
		
		@RequestMapping(value = "/")
		public void paramOne(@ModelAttribute(value = "modelAttributePojo") ModelAttributePojo modelAttributePojo) {
			
		}
		
		public class ModelAttributePojo {
			
		}
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testQueryParam() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getName());
		Assertions.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one")) {
				Assertions.assertEquals(1, apiMethodDoc.getQueryparameters().size());
			}
			if (apiMethodDoc.getPath().contains("/param-two")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
			}
			if (apiMethodDoc.getPath().contains("/param-three")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				Assertions.assertEquals("param", param.getName());
				Assertions.assertEquals("value", param.getAllowedvalues()[0]);
				ApiParamDoc param2 = iterator.next();
				Assertions.assertEquals("param2", param2.getName());
				Assertions.assertEquals("value2", param2.getAllowedvalues()[0]);
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController2.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController2", apiDoc.getName());
		Assertions.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
			}
			if (apiMethodDoc.getPath().contains("/param-two")) {
				Assertions.assertEquals(3, apiMethodDoc.getQueryparameters().size());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController3.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController3", apiDoc.getName());
		Assertions.assertEquals(4, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assertions.assertEquals("name", queryParam.getName());
				Assertions.assertEquals("true", queryParam.getRequired());
				Assertions.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assertions.assertEquals("", queryParam.getDefaultvalue());
			}
			if (apiMethodDoc.getPath().contains("/param-two")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assertions.assertEquals("name", queryParam.getName());
				Assertions.assertEquals("false", queryParam.getRequired());
				Assertions.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assertions.assertEquals("test", queryParam.getDefaultvalue());
			}
			if (apiMethodDoc.getPath().contains("/param-three")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assertions.assertEquals("", queryParam.getName());
				Assertions.assertEquals("true", queryParam.getRequired());
				Assertions.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assertions.assertEquals("", queryParam.getDefaultvalue());
			}
			if (apiMethodDoc.getPath().contains("/param-four")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assertions.assertEquals("value", queryParam.getName());
				Assertions.assertEquals("false", queryParam.getRequired());
				Assertions.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assertions.assertEquals("", queryParam.getDefaultvalue());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController4.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController4", apiDoc.getName());
		Assertions.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/")) {
				Assertions.assertEquals(1, apiMethodDoc.getQueryparameters().size());
				ApiParamDoc param = apiMethodDoc.getQueryparameters().iterator().next();
				Assertions.assertEquals("name", param.getName());
			}
			if (apiMethodDoc.getPath().contains("/two")) {
				Assertions.assertEquals(2, apiMethodDoc.getQueryparameters().size());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController5.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController5", apiDoc.getName());
		Assertions.assertEquals(1, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/")) {
				Assertions.assertEquals(1, apiMethodDoc.getQueryparameters().size());
				ApiParamDoc param = apiMethodDoc.getQueryparameters().iterator().next();
				Assertions.assertEquals("modelAttributePojo", param.getName());
				Assertions.assertEquals("modelattributepojo", param.getJsondocType().getOneLineText());
			}
		}
		
	}

}
