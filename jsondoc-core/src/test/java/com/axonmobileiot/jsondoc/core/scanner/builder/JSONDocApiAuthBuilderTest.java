package com.axonmobileiot.jsondoc.core.scanner.builder;

import com.axonmobileiot.jsondoc.core.annotation.Api;
import com.axonmobileiot.jsondoc.core.annotation.ApiAuthToken;
import com.axonmobileiot.jsondoc.core.annotation.ApiMethod;
import com.axonmobileiot.jsondoc.core.pojo.ApiDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.JSONDoc;
import com.axonmobileiot.jsondoc.core.scanner.DefaultJSONDocScanner;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class JSONDocApiAuthBuilderTest {
	
	JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
	
	@Api(name = "test-token-auth", description = "Test token auth")
	@ApiAuthToken(roles = { "" }, testtokens = { "abc", "cde" })
	private class Controller {
		
		@ApiMethod(path = "/inherit")
		public void inherit() {
			
		}
		
		@ApiMethod(path = "/override")
		@ApiAuthToken(roles = { "" }, scheme = "Bearer", testtokens = { "xyz" })
		public void override() {
			
		}
		
	}
	
	@Test
	public void testApiAuthToken() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(Controller.class), JSONDoc.MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("TOKEN", apiDoc.getAuth().getType());
		Assertions.assertEquals("", apiDoc.getAuth().getScheme());
		Assertions.assertEquals("abc", apiDoc.getAuth().getTesttokens().iterator().next());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if(apiMethodDoc.getPath().contains("/inherit")) {
				Assertions.assertEquals("TOKEN", apiMethodDoc.getAuth().getType());
				Assertions.assertEquals("", apiMethodDoc.getAuth().getScheme());
				Assertions.assertEquals("abc", apiMethodDoc.getAuth().getTesttokens().iterator().next());
			}
			if(apiMethodDoc.getPath().contains("/override")) {
				Assertions.assertEquals("TOKEN", apiMethodDoc.getAuth().getType());
				Assertions.assertEquals("Bearer", apiMethodDoc.getAuth().getScheme());
				Assertions.assertEquals("xyz", apiMethodDoc.getAuth().getTesttokens().iterator().next());
			}
		}
		
	}

}
