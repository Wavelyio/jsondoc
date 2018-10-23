package com.axonmobileiot.jsondoc.springintegration.scanner.object;

import com.axonmobileiot.jsondoc.core.pojo.ApiObjectDoc;
import com.axonmobileiot.jsondoc.springintegration.scanner.builder.SpringObjectBuilder;
import com.axonmobileiot.jsondoc.springintegration.scanner.object.pojo.MyObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpringObjectBuilderTest {

	@Test
	public void testApiVerb() {
		ApiObjectDoc buildObject = SpringObjectBuilder.buildObject(MyObject.class);
		Assertions.assertEquals("MyObject", buildObject.getName());
		Assertions.assertEquals(3, buildObject.getFields().size());
	}

}
