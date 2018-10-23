package com.axonmobileiot.jsondoc.core.doc;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.annotation.ApiHeader;
import com.axonmobileiot.jsondoc.core.annotation.ApiHeaders;
import com.axonmobileiot.jsondoc.core.annotation.ApiMethod;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.pojo.display.MethodDisplay;
import com.axonmobileiot.jsondoc.core.scanner.DefaultJSONDocScanner;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ApiHeadersDocTest {
	
	private JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
	
	@Api(description = "ApiHeadersController", name = "ApiHeadersController")
	@ApiHeaders(
		headers = {
			@ApiHeader(name = "H1", description = "h1-description"),
			@ApiHeader(name = "H2", description = "h2-description")
		}
	)
	private class ApiHeadersController {
		
		@ApiMethod(path = "/api-headers-controller-method-one")
		public void apiHeadersMethodOne() {
			
		}
		
		@ApiMethod(path = "/api-headers-controller-method-two")
		@ApiHeaders(
			headers = {
				@ApiHeader(name = "H4", description = "h4-description"),
				@ApiHeader(name = "H1", description = "h1-description") // this is a duplicate of the one at the class level, it will not be taken into account when building the doc
			}
		)
		public void apiHeadersMethodTwo() {
			
		}
		
	}
	
	@Test
	public void testApiHeadersOnClass() {
		final ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(ApiHeadersController.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("ApiHeadersController", apiDoc.getName());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if(apiMethodDoc.getPath().contains("/api-headers-controller-method-one")) {
				Assertions.assertEquals(2, apiMethodDoc.getHeaders().size());
			}
			if(apiMethodDoc.getPath().contains("/api-headers-controller-method-two")) {
				Assertions.assertEquals(3, apiMethodDoc.getHeaders().size());
			}
		}
	}

}
