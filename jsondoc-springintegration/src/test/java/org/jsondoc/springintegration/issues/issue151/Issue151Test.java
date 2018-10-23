package org.jsondoc.springintegration.issues.issue151;

import org.jsondoc.core.pojo.JSONDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;

import org.jsondoc.springintegration.scanner.Spring5JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// @ApiObject ignored for ParameterizedType return objects
// https://github.com/fabiomaffioletti/jsondoc/issues/151
public class Issue151Test {
	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
	
	@Test
	public void testIssue151() {
		JSONDoc jsonDoc = jsondocScanner.getJSONDoc("version", "basePath", List.of("org.jsondoc.springmvc.issues.issue151"), true, MethodDisplay.URI);
		Assertions.assertEquals(2, jsonDoc.getObjects().keySet().size());
		Assertions.assertEquals(1, jsonDoc.getObjects().get("bargroup").size());
		Assertions.assertEquals(1, jsonDoc.getObjects().get("foogroup").size());
	}

}
