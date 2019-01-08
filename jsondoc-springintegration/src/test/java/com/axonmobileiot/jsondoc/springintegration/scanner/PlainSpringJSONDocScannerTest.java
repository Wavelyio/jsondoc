package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.pojo.*;
import com.axonmobileiot.jsondoc.core.pojo.display.MethodDisplay;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class PlainSpringJSONDocScannerTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
	private class SpringController {

		@RequestMapping(value = "/string/{name}", headers = "header=test", params = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseStatus(value = HttpStatus.CREATED)
		public @ResponseBody String string(@PathVariable(value = "test") String name, @RequestParam("id") Integer id, @RequestParam Long query, @RequestParam(name = "user", required = false, defaultValue = "admin") String user,				@RequestHeader(value = "header-two", defaultValue = "header-test") String header, @RequestBody String requestBody) {
			return "ok";
		}

	}

	@Test
	public void testMergeApiDoc() {
		Set<Class<?>> controllers = new LinkedHashSet<>();
		controllers.add(SpringController.class);
		Set<ApiDoc> apiDocs = jsondocScanner.getApiDocs(controllers, MethodDisplay.URI);

		ApiDoc apiDoc = apiDocs.iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getDescription());
		Assertions.assertEquals("SpringController", apiDoc.getName());
		Assertions.assertNotNull(apiDoc.getGroup());

		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			Assertions.assertEquals(MethodDisplay.URI, apiMethodDoc.getDisplayMethodAs());
			Assertions.assertNull(apiMethodDoc.getAuth());
			Assertions.assertNull(apiMethodDoc.getSupportedversions());
			Assertions.assertTrue(apiMethodDoc.getApierrors().isEmpty());
			Assertions.assertNull(apiMethodDoc.getId());
			Assertions.assertEquals("", apiMethodDoc.getSummary());
			Assertions.assertEquals("", apiMethodDoc.getDescription());
			
			if (apiMethodDoc.getPath().contains("/api/string/{name}")) {
				Assertions.assertEquals(2, apiMethodDoc.getHeaders().size());
				Set<ApiHeaderDoc> headers = apiMethodDoc.getHeaders();
				Iterator<ApiHeaderDoc> headersIterator = headers.iterator();
				ApiHeaderDoc headerTest = headersIterator.next();
				Assertions.assertEquals("header", headerTest.getName());
				Assertions.assertEquals("test", headerTest.getAllowedvalues()[0]);
				ApiHeaderDoc headerTwo = headersIterator.next();
				Assertions.assertEquals("header-two", headerTwo.getName());
				Assertions.assertEquals("header-test", headerTwo.getAllowedvalues()[0]);

				Assertions.assertEquals("String", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				Assertions.assertEquals("String", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("POST", apiMethodDoc.getVerb().iterator().next().name());
				Assertions.assertEquals("application/json", apiMethodDoc.getProduces().iterator().next());
				Assertions.assertEquals("application/json", apiMethodDoc.getConsumes().iterator().next());
				Assertions.assertEquals("201 - Created", apiMethodDoc.getResponsestatuscode());

				Set<ApiParamDoc> queryparameters = apiMethodDoc.getQueryparameters();
				Assertions.assertEquals(4, queryparameters.size());
				Iterator<ApiParamDoc> qpIterator = queryparameters.iterator();
				ApiParamDoc apiParamDoc = qpIterator.next();
				Assertions.assertEquals("delete", apiParamDoc.getName());
				Assertions.assertEquals("true", apiParamDoc.getRequired());
				Assertions.assertEquals(null, apiParamDoc.getDefaultvalue());
				Assertions.assertEquals(0, apiParamDoc.getAllowedvalues().length);
				apiParamDoc = qpIterator.next();
				Assertions.assertEquals("id", apiParamDoc.getName());
				Assertions.assertEquals("true", apiParamDoc.getRequired());
				Assertions.assertTrue(apiParamDoc.getDefaultvalue().isEmpty());
				apiParamDoc = qpIterator.next();
				Assertions.assertEquals("", apiParamDoc.getName());
				Assertions.assertEquals("true", apiParamDoc.getRequired());
				Assertions.assertEquals("", apiParamDoc.getDefaultvalue());

				apiParamDoc = qpIterator.next();
				Assertions.assertEquals("user", apiParamDoc.getName());
				Assertions.assertEquals("false", apiParamDoc.getRequired());
				Assertions.assertEquals("admin", apiParamDoc.getDefaultvalue());

				Set<ApiParamDoc> pathparameters = apiMethodDoc.getPathparameters();
				Iterator<ApiParamDoc> ppIterator = pathparameters.iterator();
				apiParamDoc = ppIterator.next();
				apiParamDoc = apiMethodDoc.getPathparameters().iterator().next();
				Assertions.assertEquals("test", apiParamDoc.getName());
			}
		}

	}

}
