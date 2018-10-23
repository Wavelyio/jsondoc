package org.jsondoc.core.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.jsondoc.core.util.pojo.MyEnum;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JSONDocEnumTemplateBuilderTest {

	@Test
	public void testTemplate() throws IOException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		Set<Class<?>> classes = Set.of(MyEnum.class);
		
		Map<String, Object> template = JSONDocTemplateBuilder.build(MyEnum.class, classes);
		System.out.println(mapper.writeValueAsString(template));
	}

}
