package com.axonmobileiot.jsondoc.springintegration.issues.invisible;

import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import com.axonmobileiot.jsondoc.springintegration.scanner.Spring5JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InterfaceApiObjectTest {
	
	private JSONDocScanner jsondocScanner = new Spring5JSONDocScanner();
	
	@Test
	public void testInvisible() {
		JSONDoc jsonDoc = jsondocScanner.getJSONDoc("version", "basePath", List.of("com.axonmobileiot.jsondoc.springintegration.issues.invisible"), true, JSONDoc.MethodDisplay.URI);
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
