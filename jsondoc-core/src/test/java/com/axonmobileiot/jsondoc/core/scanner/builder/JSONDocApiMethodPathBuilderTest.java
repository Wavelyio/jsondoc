package com.axonmobileiot.jsondoc.core.scanner.builder;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.annotation.ApiMethod;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.pojo.display.MethodDisplay;
import com.axonmobileiot.jsondoc.core.scanner.DefaultJSONDocScanner;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class JSONDocApiMethodPathBuilderTest {

	JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();

	@Api(name = "test-path", description = "test-path")
	private class Controller {

		@ApiMethod(path = { "/path1", "/path2" })
		public void path() {

		}

	}

	@Test
	public void testPathWithMethodDisplayURI() {

		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(Controller.class), MethodDisplay.URI).iterator().next();
		boolean allRight = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/path1") &&
							doc.getPath().contains("/path2") &&
							doc.getDisplayedMethodString().contains("/path1") &&
							doc.getDisplayedMethodString().contains("/path2"));

		Assertions.assertTrue(allRight);
	}

	@Test
	public void testPathWithMethodDisplayMethod() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(Controller.class), MethodDisplay.METHOD).iterator().next();

		boolean allRight = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/path1") &&
                doc.getPath().contains("/path2") &&
                doc.getDisplayedMethodString().contains("path") &&
                !doc.getDisplayedMethodString().contains("/path1"));

		Assertions.assertTrue(allRight);
	}

}
