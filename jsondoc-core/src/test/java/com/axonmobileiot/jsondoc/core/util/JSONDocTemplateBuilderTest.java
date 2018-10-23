package com.axonmobileiot.jsondoc.core.util;

import com.axonmobileiot.jsondoc.core.pojo.JSONDocTemplate;
import com.axonmobileiot.jsondoc.core.util.pojo.ClassWithConstant;
import com.axonmobileiot.jsondoc.core.util.pojo.TemplateObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JSONDocTemplateBuilderTest {

	@Test
	public void testTemplate() throws IOException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		Set<Class<?>> classes = Set.of(TemplateObject.class);
		
		Map<String, Object> template = JSONDocTemplateBuilder.build(TemplateObject.class, classes);

		assertEquals(0, template.get("my_id"));
		assertEquals(0, template.get("idint"));
		assertEquals(0, template.get("idlong"));
		assertEquals("", template.get("name"));
		assertEquals("", template.get("gender"));
		assertEquals(true, template.get("bool"));
		assertEquals(new ArrayList(), template.get("intarrarr"));
		assertEquals(new JSONDocTemplate(), template.get("sub_obj"));
		assertEquals(new ArrayList(), template.get("untypedlist"));
		assertEquals(new ArrayList(), template.get("subsubobjarr"));
		assertEquals(new ArrayList(), template.get("stringlist"));
		assertEquals(new ArrayList(), template.get("stringarrarr"));
		assertEquals(new ArrayList(), template.get("integerarr"));
		assertEquals(new ArrayList(), template.get("stringarr"));
		assertEquals(new ArrayList(), template.get("intarr"));
		assertEquals(new ArrayList(), template.get("subobjlist"));
		assertEquals(new ArrayList(), template.get("wildcardlist"));
		assertEquals(new ArrayList(), template.get("longlist"));
		assertEquals("", template.get("namechar"));
		assertEquals(new HashMap(), template.get("map"));
		assertEquals(new HashMap(), template.get("mapstringinteger"));
		assertEquals(new HashMap(), template.get("mapsubobjinteger"));
		assertEquals(new HashMap(), template.get("mapintegersubobj"));
		assertEquals(new HashMap(), template.get("mapintegerlistsubsubobj"));
		
		System.out.println(mapper.writeValueAsString(template));
	}

	@Test
	public void testTemplateWithConstant() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final Set<Class<?>> classes = Set.of(ClassWithConstant.class);

        final Map<String, Object> template = JSONDocTemplateBuilder.build(ClassWithConstant.class, classes);
        assertEquals("", template.get("identifier"));
        assertNull(template.get(ClassWithConstant.THIS_IS_A_CONSTANT));

        final String serializedTemplate =
            "{" +
                "\"identifier\":\"\"" +
            "}";

        assertEquals(mapper.writeValueAsString(template), serializedTemplate);
	}
}
