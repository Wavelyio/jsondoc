package com.axonmobileiot.jsondoc.core.doc;

import com.axonmobileiot.jsondoc.core.pojo.ApiMethodDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiVerb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ApiMethodDocTest {

	private ApiMethodDoc first;
	private ApiMethodDoc second;

	@Test
	public void testNotEqual() {
		first = new ApiMethodDoc();
		first.setPath(Set.of("/first"));
		first.setVerb(Set.of(ApiVerb.GET));
		second = new ApiMethodDoc();
		second.setPath(Set.of("/second"));
		second.setVerb(Set.of(ApiVerb.GET));
        Assertions.assertNotEquals(0, first.compareTo(second));
	}

	@Test
	public void testEqual() {
		first = new ApiMethodDoc();
		first.setPath(Set.of("/test"));
		first.setVerb(Set.of(ApiVerb.GET));
		second = new ApiMethodDoc();
		second.setPath(Set.of("/test"));
		second.setVerb(Set.of(ApiVerb.GET));
        Assertions.assertEquals(0, first.compareTo(second));
	}
	
	@Test
	public void testNotEqualMultipleVerbs() {
		first = new ApiMethodDoc();
		first.setPath(Set.of("/first"));
		first.setVerb(Set.of(ApiVerb.GET, ApiVerb.POST));
		second = new ApiMethodDoc();
		second.setPath(Set.of("/second"));
		second.setVerb(Set.of(ApiVerb.GET, ApiVerb.POST));
        Assertions.assertNotEquals(0, first.compareTo(second));
		
		second.setPath(Set.of("/first"));
		second.setVerb(Set.of(ApiVerb.PUT, ApiVerb.POST));
        Assertions.assertNotEquals(0, first.compareTo(second));
	}
	
	@Test
	public void testEqualMultipleVerbs() {
		first = new ApiMethodDoc();
		first.setPath(Set.of("/test"));
		first.setVerb(Set.of(ApiVerb.GET, ApiVerb.POST));
		second = new ApiMethodDoc();
		second.setPath(Set.of("/test"));
		second.setVerb(Set.of(ApiVerb.GET, ApiVerb.POST));
        Assertions.assertEquals(0, first.compareTo(second));
		
		second.setVerb(Set.of(ApiVerb.POST, ApiVerb.GET));
        Assertions.assertEquals(0, first.compareTo(second));
	}

}
