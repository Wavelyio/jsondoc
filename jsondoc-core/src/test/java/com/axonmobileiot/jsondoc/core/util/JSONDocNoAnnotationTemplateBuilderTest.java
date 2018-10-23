package com.axonmobileiot.jsondoc.core.util;

import com.axonmobileiot.jsondoc.core.util.pojo.NoAnnotationPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class JSONDocNoAnnotationTemplateBuilderTest {

	@Test
	public void testTemplate() throws IOException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		Set<Class<?>> classes = Set.of(NoAnnotationPojo.class);
		
		Map<String, Object> template = JSONDocTemplateBuilder.build(NoAnnotationPojo.class, classes);
		System.out.println(mapper.writeValueAsString(template));
	}

}
