package org.jsondoc.springintegration.scanner;

import org.jsondoc.core.pojo.ApiObjectDoc;
import org.jsondoc.core.pojo.JSONDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.jsondoc.springintegration.controller.object.Spring3JSONDocObjectScannerTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Spring3JSONDocGenericsObjectScannerTest {
	private String version = "1.0";
	private String basePath = "http://localhost:8080/api";

	private static Logger log = LoggerFactory.getLogger(Spring3JSONDocObjectScannerTest.class);

	@Test
	public void getJSONDoc() throws IOException {
		JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
		JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, List.of("org.jsondoc.springmvc.issues.issue174"), true, MethodDisplay.URI);

		Map<String, Set<ApiObjectDoc>> objects = jsondoc.getObjects();
		for (Set<ApiObjectDoc> values : objects.values()) {
			for (ApiObjectDoc apiObjectDoc : values) {
				System.out.println(apiObjectDoc.getName());
			}
		}
	}
}
