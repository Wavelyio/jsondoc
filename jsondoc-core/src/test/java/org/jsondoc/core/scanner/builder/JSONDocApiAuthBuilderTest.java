package org.jsondoc.core.scanner.builder;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthToken;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiDoc;
import org.jsondoc.core.pojo.ApiMethodDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.DefaultJSONDocScanner;
import org.jsondoc.core.scanner.JSONDocScanner;

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
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(Controller.class), MethodDisplay.URI).iterator().next();
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
