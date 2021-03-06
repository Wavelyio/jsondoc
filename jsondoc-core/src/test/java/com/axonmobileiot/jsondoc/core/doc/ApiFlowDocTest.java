package com.axonmobileiot.jsondoc.core.doc;

import com.axonmobileiot.jsondoc.core.annotation.flow.ApiFlow;
import com.axonmobileiot.jsondoc.core.annotation.flow.ApiFlowSet;
import com.axonmobileiot.jsondoc.core.annotation.flow.ApiFlowStep;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.flow.ApiFlowDoc;
import com.axonmobileiot.jsondoc.core.scanner.DefaultJSONDocScanner;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApiFlowDocTest {
	
	private JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();

	@ApiFlowSet
	private class TestFlow {

		@ApiFlow(
				name = "flow", 
				description = "A test flow",
				steps = {
						@ApiFlowStep(apimethodid = "F1"),
						@ApiFlowStep(apimethodid = "F2"),
						@ApiFlowStep(apimethodid = "F3")
				},
				group = "Flows A"
			)
		public void flow() {
			
		}
		
		@ApiFlow(
				name = "flow2", 
				description = "A test flow 2",
				steps = {
						@ApiFlowStep(apimethodid = "F4"),
						@ApiFlowStep(apimethodid = "F5"),
						@ApiFlowStep(apimethodid = "F6")
				},
				group = "Flows B"
			)
		public void flow2() {
			
		}

	}
	
	@Test
	public void testApiDoc() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(TestFlow.class);
		
		List<ApiMethodDoc> apiMethodDocs = new ArrayList<>();
		ApiMethodDoc apiMethodDoc = new ApiMethodDoc();
		apiMethodDoc.setId("F1");
		apiMethodDocs.add(apiMethodDoc);
		
		Set<ApiFlowDoc> apiFlowDocs = jsondocScanner.getApiFlowDocs(classes, apiMethodDocs);
		for (ApiFlowDoc apiFlowDoc : apiFlowDocs) {
			if(apiFlowDoc.getName().equals("flow")) {
				Assertions.assertEquals("A test flow", apiFlowDoc.getDescription());
				Assertions.assertEquals(3, apiFlowDoc.getSteps().size());
				Assertions.assertEquals("F1", apiFlowDoc.getSteps().get(0).getApimethodid());
				Assertions.assertEquals("F2", apiFlowDoc.getSteps().get(1).getApimethodid());
				Assertions.assertEquals("Flows A", apiFlowDoc.getGroup());
				Assertions.assertNotNull(apiFlowDoc.getSteps().get(0).getApimethoddoc());
				Assertions.assertEquals("F1", apiFlowDoc.getSteps().get(0).getApimethoddoc().getId());
			}
			
			if(apiFlowDoc.getName().equals("flow2")) {
				Assertions.assertEquals("A test flow 2", apiFlowDoc.getDescription());
				Assertions.assertEquals(3, apiFlowDoc.getSteps().size());
				Assertions.assertEquals("F4", apiFlowDoc.getSteps().get(0).getApimethodid());
				Assertions.assertEquals("F5", apiFlowDoc.getSteps().get(1).getApimethodid());
				Assertions.assertEquals("Flows B", apiFlowDoc.getGroup());
			}
		}
	}

}
