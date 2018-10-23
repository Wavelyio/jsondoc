package com.axonmobileiot.jsondoc.core.util;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSONDocTypeBuilderTest {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public String getString() {
		return null;
	}
	
	public Integer getInteger() {
		return null;
	}
	
	public Long getLong() {
		return null;
	}
	
	public int getInt() {
		return 0;
	}
	
	public long getlong() {
		return 0L;
	}
	
	public List<String> getListString() {
		return null;
	}
	
	public List<Set<String>> getListSetString() {
		return null;
	}
	
	public String[] getStringArray() {
		return null;
	}
	
	public Integer[] getIntegerArray() {
		return null;
	}
	
	public List<String>[] getListOfStringArray() {
		return null;
	}
	
	public Set<String>[] getSetOfStringArray() {
		return null;
	}
	
	public List getList() {
		return null;
	}
	
	public List<?> getListOfWildcard() {
		return null;
	}
	
	public List<?>[] getListOfWildcardArray() {
		return null;
	}
	
	public List[] getListArray() {
		return null;
	}
	
	public Set[] getSetArray() {
		return null;
	}
	
	public Map getMap() {
		return null;
	}

	public HashMap getHashMap() {
		return null;
	}

	public Map<String, Integer> getMapStringInteger() {
		return null;
	}
	
	public Map<List<String>, Integer> getMapListOfStringInteger() {
		return null;
	}
	
	public Map<String, Set<Integer>> getMapStringSetOfInteger() {
		return null;
	}
	
	public Map<List<String>, Set<Integer>> getMapListOfStringSetOfInteger() {
		return null;
	}
	
	public Map<List<Set<String>>, Set<Integer>> getMapListOfSetOfStringSetOfInteger() {
		return null;
	}
	
	public Map<?, Integer> getMapWildcardInteger() {
		return null;
	}
	
	public Map<?, ?> getMapWildcardWildcard() {
		return null;
	}
	
	public Map<List<?>, ?> getMapListOfWildcardWildcard() {
		return null;
	}
	
	public Map<Map, Integer> getMapMapInteger() {
		return null;
	}
	
	public Map<Map<String,Long>, Integer> getMapMapStringLongInteger() {
		return null;
	}
	
	public ResponseEntity<String> getResponseEntityString() {
		return null;
	}
	
	public ResponseEntity<List<String>> getResponseEntityListOfString() {
		return null;
	}
	
	public List<ParentPojo> getParentPojoList() {
		return null;
	}
	
	public <T> FooPojo<T> getSpecializedWGenericsPojo() {
		return null;
	}
	
	@ApiObject(name = "my_parent")
	class ParentPojo {
		
	}
	
	@ApiObject(name="fooPojo", group="foo")
	public class FooPojo<K> {
		@ApiObjectField
		private K fooField;
	}

	
	@Test
	public void testReflex() throws NoSuchMethodException, SecurityException, IOException {
		mapper.setSerializationInclusion(Include.NON_NULL);
		JSONDocType jsonDocType = new JSONDocType();
		
		Method method = JSONDocTypeBuilderTest.class.getMethod("getString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("integer", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getInt");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("int", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getLong");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("long", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getlong");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("long", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("list of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListSetString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("list of set of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getStringArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("array of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getIntegerArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("array of integer", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListOfStringArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("array of list of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getSetOfStringArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("array of set of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getList");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("list", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListOfWildcard");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("list of wildcard", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListOfWildcardArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("array of list of wildcard", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getListArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("array of list", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getSetArray");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("array of set", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMap");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getHashMap");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("hashmap", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapStringInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[string, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfStringInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[list of string, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapStringSetOfInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[string, set of integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfStringSetOfInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[list of string, set of integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfSetOfStringSetOfInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[list of set of string, set of integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapWildcardInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[wildcard, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapWildcardWildcard");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[wildcard, wildcard]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapListOfWildcardWildcard");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[list of wildcard, wildcard]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapMapInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[map, integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getMapMapStringLongInteger");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("map[map[string, long], integer]", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getResponseEntityString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("responseentity of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");

		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getResponseEntityListOfString");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("responseentity of list of string", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getParentPojoList");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("list of my_parent", jsonDocType.getOneLineText());
		System.out.println("---------------------------");
		
		jsonDocType = new JSONDocType();
		method = JSONDocTypeBuilderTest.class.getMethod("getSpecializedWGenericsPojo");
		JSONDocTypeBuilder.build(jsonDocType, method.getReturnType(), method.getGenericReturnType());
		System.out.println(mapper.writeValueAsString(jsonDocType));
		System.out.println(jsonDocType.getOneLineText());
		Assertions.assertEquals("fooPojo of T", jsonDocType.getOneLineText());
		System.out.println("---------------------------");		
	}

}
