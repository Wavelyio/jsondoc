package org.jsondoc.springintegration.issues.invisible;

import org.jsondoc.core.pojo.ApiDoc;
import org.jsondoc.core.pojo.ApiMethodDoc;
import org.jsondoc.core.pojo.JSONDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.jsondoc.springintegration.scanner.Spring5JSONDocScanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InterfaceApiObjectTest {
	
	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
	
	@Test
	public void testInvisible() {
		JSONDoc jsonDoc = jsondocScanner.getJSONDoc("version", "basePath", List.of("org.jsondoc.springmvc.issues.invisible"), true, MethodDisplay.URI);
		Assertions.assertEquals(1, jsonDoc.getObjects().keySet().size());
		for (String string : jsonDoc.getObjects().keySet()) {
			Assertions.assertEquals(2, jsonDoc.getObjects().get(string).size());
		}
		for (ApiDoc apiDoc : jsonDoc.getApis().get("")) {
			for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
				Assertions.assertEquals("Resource Interface", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
		}
		
	}

}
