package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiHeaderDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.Set;

public class SpringApiHeadersDocTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping(headers = { "h1", "h2" })
	public class SpringApiHeadersController {

		@RequestMapping(value = "/spring-api-headers-controller-method-one")
		public void apiHeadersMethodOne() {

		}

		@RequestMapping(value = "/spring-api-headers-controller-method-two", headers = { "h3" })
		public void apiHeadersMethodTwo() {

		}

		@RequestMapping(value = "/spring-api-headers-controller-method-three", headers = { "h4" })
		public void apiHeadersMethodThree(@RequestHeader(value = "h5") String h5) {

		}

	}

	@SuppressWarnings("unused")
	@Test
	public void testApiHeadersOnClass() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringApiHeadersController.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringApiHeadersController", apiDoc.getName());
		Assertions.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/spring-api-headers-controller-method-one")) {
				Assertions.assertEquals(2, apiMethodDoc.getHeaders().size());
			}
			if (apiMethodDoc.getPath().contains("/spring-api-headers-controller-method-two")) {
				Assertions.assertEquals(3, apiMethodDoc.getHeaders().size());
			}
			if (apiMethodDoc.getPath().contains("/spring-api-headers-controller-method-three")) {
				Assertions.assertEquals(4, apiMethodDoc.getHeaders().size());
				Iterator<ApiHeaderDoc> headers = apiMethodDoc.getHeaders().iterator();
				ApiHeaderDoc h1 = headers.next();
				ApiHeaderDoc h2 = headers.next();
				ApiHeaderDoc h4 = headers.next();
				Assertions.assertEquals("h4", h4.getName());
				ApiHeaderDoc h5 = headers.next();
				Assertions.assertEquals("h5", h5.getName());
			}
		}
	}

}
