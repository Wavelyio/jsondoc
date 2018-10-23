package com.axonmobileiot.jsondoc.springintegration.issues.issue151;

import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.pojo.display.MethodDisplay;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import com.axonmobileiot.jsondoc.springintegration.scanner.Spring5JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// @ApiObject ignored for ParameterizedType return objects
// https://github.com/fabiomaffioletti/jsondoc/issues/151
public class Issue151Test {
	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
	
	@Test
	public void testIssue151() {
		JSONDoc jsonDoc = jsondocScanner.getJSONDoc("version", "basePath", List.of(Issue151Test.class.getPackage().getName()), true, MethodDisplay.URI);
		Assertions.assertEquals(2, jsonDoc.getObjects().keySet().size());
		Assertions.assertEquals(1, jsonDoc.getObjects().get("bargroup").size());
		Assertions.assertEquals(1, jsonDoc.getObjects().get("foogroup").size());
	}

}
