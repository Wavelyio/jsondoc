package com.axonmobileiot.jsondoc.springintegration.scanner;

import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.pojo.display.MethodDisplay;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

public class SpringPathBuilderTest {

	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();

	@Controller
	@RequestMapping
	public class SpringController {

		@RequestMapping(value = "/path")
		public void slashPath() {

		}

		@RequestMapping(value = "/")
		public void path() {

		}

		@RequestMapping()
		public void none() {

		}
	}

	@Controller
	@RequestMapping
	public class SpringController2 {

		@RequestMapping
		public void none() {

		}

		@RequestMapping(value = "/test")
		public void test() {

		}
	}

	@Controller
	@RequestMapping(value = { "/path1", "/path2" })
	public class SpringController3 {

		@RequestMapping(value = { "/path3", "/path4" })
		public void none() {

		}

	}
	
	@Controller
	@RequestMapping(value = "/path")
	public class SpringController4 {

		@RequestMapping
		public void none() {

		}

	}

	@Controller
	@RequestMapping(path = {"/path", "/path2"})
	public class SpringController5 {
		
		@RequestMapping
		public void none() {
			
		}
		
	}

	@Controller
	@RequestMapping(path = {"/api/widget"})
	public class SpringController6 {

		@RequestMapping(method = RequestMethod.GET, path = "frame")
		public void getItem() {

		}

	}

	@Test
	public void testPath() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController", apiDoc.getName());
		boolean slashPath = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/path"));
		Assertions.assertTrue(slashPath);
		boolean slash = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/"));
		Assertions.assertTrue(slash);
	}

	@Test
	public void testPath2() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController2.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController2", apiDoc.getName());
		boolean none = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/"));
		Assertions.assertTrue(none);
		boolean test = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/test"));
		Assertions.assertTrue(test);
	}
	
	@Test
	public void testPath3() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController3.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController3", apiDoc.getName());

		boolean allRight = apiDoc.getMethods().stream()
				.anyMatch(doc ->
						doc.getPath().contains("/path1/path3") &&
						doc.getPath().contains("/path1/path4") &&
						doc.getPath().contains("/path2/path3") &&
						doc.getPath().contains("/path2/path4"));
		Assertions.assertTrue(allRight);

	}
	
	@Test
	public void testPath4() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController4.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController4", apiDoc.getName());
		boolean allRight = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/path"));
		Assertions.assertTrue(allRight);

	}

	@Test
	public void testPath5() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController5.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController5", apiDoc.getName());
		boolean allRight = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/path") && doc.getPath().contains("/path2"));
		Assertions.assertTrue(allRight);
	}

	@Test
	public void testPath6() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController6.class), MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("SpringController6", apiDoc.getName());
		boolean allRight = apiDoc.getMethods().stream().anyMatch(doc -> doc.getPath().contains("/api/widget/frame"));
		Assertions.assertTrue(allRight);
	}
	
	@Test
	public void testPathWithMethodDisplayMethod() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(SpringController5.class), MethodDisplay.METHOD).iterator().next();
		boolean allRight = apiDoc.getMethods().stream()
                .anyMatch(doc -> doc.getPath().contains("/path")
                        && doc.getPath().contains("/path2")
                        && doc.getDisplayedMethodString().contains("none"));
		Assertions.assertTrue(allRight);
	}

}
