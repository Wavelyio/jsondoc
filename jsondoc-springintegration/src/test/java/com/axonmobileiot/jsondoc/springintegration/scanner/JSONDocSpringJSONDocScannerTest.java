package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.annotation.*;
import com.axonmobileiot.jsondoc.core.pojo.*;
import com.axonmobileiot.jsondoc.core.pojo.display.MethodDisplay;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class JSONDocSpringJSONDocScannerTest {
	
	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
	
	@Api(description = "A spring controller", name = "Spring controller")
	@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiAuthNone
	@ApiVersion(since = "1.0")
	@ApiErrors(
		apierrors = {
			@ApiError(code = "100", description = "error-100")
		}
	)
	private class SpringController {
		
		@ApiMethod(description = "Gets a string", path = "/wrongOnPurpose", verb = ApiVerb.GET)
		@RequestMapping(value = "/string/{name}", headers = "header=test", params = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseStatus(value = HttpStatus.CREATED)
		public @ApiResponseObject @ResponseBody String string(
				@ApiPathParam(name = "name") @PathVariable(value = "test") String name, 
				@ApiQueryParam @RequestParam("id") Integer id,
				@ApiQueryParam(name = "query", defaultvalue = "test") @RequestParam(value = "myquery") Long query,
				@RequestBody @ApiBodyObject String requestBody) {
			return "ok";
		}
	}
	
	@Test
	public void testMergeApiDoc() {
		Set<Class<?>> controllers = new LinkedHashSet<>();
		controllers.add(SpringController.class);
		Set<ApiDoc> apiDocs = jsondocScanner.getApiDocs(controllers, MethodDisplay.URI);
		
		ApiDoc apiDoc = apiDocs.iterator().next();
		Assertions.assertEquals("A spring controller", apiDoc.getDescription());
		Assertions.assertEquals("Spring controller", apiDoc.getName());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if(apiMethodDoc.getPath().contains("/api/string/{name}")) {
				Assertions.assertNotNull(apiMethodDoc.getAuth());
				Assertions.assertNotNull(apiMethodDoc.getSupportedversions());
				Assertions.assertFalse(apiMethodDoc.getApierrors().isEmpty());
				
				Assertions.assertEquals("String", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				Assertions.assertEquals("String", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("/api/string/{name}", apiMethodDoc.getPath().iterator().next());
				Assertions.assertEquals("POST", apiMethodDoc.getVerb().iterator().next().name());
				Assertions.assertEquals("application/json", apiMethodDoc.getProduces().iterator().next());
				Assertions.assertEquals("application/json", apiMethodDoc.getConsumes().iterator().next());
				Assertions.assertEquals("201 - Created", apiMethodDoc.getResponsestatuscode());
				
				Set<ApiHeaderDoc> headers = apiMethodDoc.getHeaders();
				ApiHeaderDoc header = headers.iterator().next();
				Assertions.assertEquals("header", header.getName());
				Assertions.assertEquals("test", header.getAllowedvalues()[0]);
				
				Set<ApiParamDoc> queryparameters = apiMethodDoc.getQueryparameters();
				Assertions.assertEquals(3, queryparameters.size());
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
				Assertions.assertEquals("myquery", apiParamDoc.getName());
				Assertions.assertEquals("true", apiParamDoc.getRequired());
				Assertions.assertEquals("", apiParamDoc.getDefaultvalue());
				
				Set<ApiParamDoc> pathparameters = apiMethodDoc.getPathparameters();
				Iterator<ApiParamDoc> ppIterator = pathparameters.iterator();
				apiParamDoc = ppIterator.next();
				apiParamDoc = apiMethodDoc.getPathparameters().iterator().next();
				Assertions.assertEquals("test", apiParamDoc.getName());
			}
		}
		
	}

}
