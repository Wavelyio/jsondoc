package com.axonmobileiot.jsondoc.core.issues.issue151;

import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.DefaultJSONDocScanner;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//@ApiObject ignored for ParameterizedType return objects
//https://github.com/fabiomaffioletti/jsondoc/issues/151
public class Issue151Test {
	
	JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
	
	@Test
	public void testIssue151() {
		JSONDoc jsonDoc = jsondocScanner.getJSONDoc("", "", List.of("com.axonmobileiot.jsondoc.core.issues.issue151"), true, JSONDoc.MethodDisplay.URI);
		Assertions.assertEquals(2, jsonDoc.getObjects().keySet().size());
		Assertions.assertEquals(1, jsonDoc.getObjects().get("bargroup").size());
		Assertions.assertEquals(1, jsonDoc.getObjects().get("foogroup").size());
	}

}
