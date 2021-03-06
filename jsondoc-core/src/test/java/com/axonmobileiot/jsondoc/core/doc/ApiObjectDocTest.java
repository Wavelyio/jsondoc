package com.axonmobileiot.jsondoc.core.doc;

import com.axonmobileiot.jsondoc.core.annotation.ApiObject;
import com.axonmobileiot.jsondoc.core.annotation.ApiObjectField;
import com.axonmobileiot.jsondoc.core.annotation.ApiVersion;
import com.axonmobileiot.jsondoc.core.pojo.ApiObjectDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiObjectFieldDoc;
import com.axonmobileiot.jsondoc.core.pojo.ApiStage;
import com.axonmobileiot.jsondoc.core.pojo.ApiVisibility;
import com.axonmobileiot.jsondoc.core.scanner.DefaultJSONDocScanner;
import com.axonmobileiot.jsondoc.core.scanner.JSONDocScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ApiObjectDocTest {
	
	private JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
	
	@ApiObject(name="test-object", visibility = ApiVisibility.PUBLIC, stage = ApiStage.PRE_ALPHA)
	@ApiVersion(since = "1.0", until = "2.12")
	private class TestObject {
		
		@ApiObjectField(description="the test name", required = true)
		private String name;
		
		@ApiObjectField(description="the test age", required = false)
		private Integer age;
		
		@ApiObjectField(description="the test avg")
		private Long avg;
		
		@ApiObjectField(description="the test map")
		private Map<String, Integer> map;
		
		@SuppressWarnings("rawtypes")
		@ApiObjectField(description="an unparametrized list to test https://github.com/fabiomaffioletti/jsondoc/issues/5")
		private List unparametrizedList;
		
		@ApiObjectField(description="a parametrized list")
		private List<String> parametrizedList;
		
		@ApiObjectField(description="a wildcard parametrized list to test https://github.com/fabiomaffioletti/jsondoc/issues/5")
		private List<?> wildcardParametrized;

		@ApiObjectField(description="a Long array to test https://github.com/fabiomaffioletti/jsondoc/issues/27")
		private Long[] LongArray;

		@ApiObjectField(description="a long array to test https://github.com/fabiomaffioletti/jsondoc/issues/27")
		private long[] primitiveLongArray;
		
		@ApiObjectField(name = "foo_bar", description="a property to test https://github.com/fabiomaffioletti/jsondoc/pull/31", required = true)
		private String fooBar;
		
		@ApiObjectField(name = "version", description="a property to test version since and until", required = true)
		@ApiVersion(since = "1.0", until = "2.12")
		private String version;
		
		@ApiObjectField(name = "test-enum", description = "a test enum")
		private TestEnum testEnum;
		
		@ApiObjectField(name = "test-enum-with-allowed-values", description = "a test enum with allowed values", allowedvalues = { "A", "B", "C" })
		private TestEnum testEnumWithAllowedValues;

		@ApiObjectField(name = "orderedProperty", order = 1)
		private String orderedProperty;
	}
	
	@ApiObject(name = "test-enum")
	private enum TestEnum {
		TESTENUM1, TESTENUM2, TESTENUM3;
	}
	
	@ApiObject
	private class NoNameApiObject {
		@ApiObjectField
		private Long id;
	}
	
	@ApiObject
	private class TemplateApiObject {
		@ApiObjectField
		private Long id;
		
		@ApiObjectField
		private String name;
	}
	
	@ApiObject
		private class UndefinedVisibilityAndStage {
	}
	
	@Test
	public void testUndefinedVisibilityAndStageDoc() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(UndefinedVisibilityAndStage.class);
		ApiObjectDoc apiObjectDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assertions.assertEquals("UndefinedVisibilityAndStage", apiObjectDoc.getName());
		Assertions.assertEquals(ApiVisibility.UNDEFINED, apiObjectDoc.getVisibility());
		Assertions.assertEquals(ApiStage.UNDEFINED, apiObjectDoc.getStage());
	}

	@Test
	public void testTemplateApiObjectDoc() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(TemplateApiObject.class);
		ApiObjectDoc apiObjectDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assertions.assertEquals("TemplateApiObject", apiObjectDoc.getName());
		Iterator<ApiObjectFieldDoc> iterator = apiObjectDoc.getFields().iterator();
		Assertions.assertEquals("id", iterator.next().getName());
		Assertions.assertEquals("name", iterator.next().getName());
	}
	
	@Test
	public void testNoNameApiObjectDoc() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(NoNameApiObject.class);
		ApiObjectDoc apiObjectDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assertions.assertEquals("NoNameApiObject", apiObjectDoc.getName());
		Assertions.assertEquals("id", apiObjectDoc.getFields().iterator().next().getName());
		Assertions.assertEquals(1, apiObjectDoc.getJsondochints().size());
	}
	
	@Test
	public void testEnumObjectDoc() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(TestEnum.class);
		ApiObjectDoc childDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assertions.assertEquals("test-enum", childDoc.getName());
		Assertions.assertEquals(0, childDoc.getFields().size());
		Assertions.assertEquals(TestEnum.TESTENUM1.name(), childDoc.getAllowedvalues()[0]);
		Assertions.assertEquals(TestEnum.TESTENUM2.name(), childDoc.getAllowedvalues()[1]);
		Assertions.assertEquals(TestEnum.TESTENUM3.name(), childDoc.getAllowedvalues()[2]);
	}
	
	@Test
	public void testApiObjectDoc() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(TestObject.class);
		ApiObjectDoc childDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assertions.assertEquals("test-object", childDoc.getName());
		Assertions.assertEquals(14, childDoc.getFields().size());
		Assertions.assertEquals("1.0", childDoc.getSupportedversions().getSince());
		Assertions.assertEquals("2.12", childDoc.getSupportedversions().getUntil());
		Assertions.assertEquals(ApiVisibility.PUBLIC, childDoc.getVisibility());
		Assertions.assertEquals(ApiStage.PRE_ALPHA, childDoc.getStage());
		
		for (ApiObjectFieldDoc fieldDoc : childDoc.getFields()) {
			if(fieldDoc.getName().equals("wildcardParametrized")) {
				Assertions.assertEquals("List", fieldDoc.getJsondocType().getType().get(0));
			}
			
			if(fieldDoc.getName().equals("unparametrizedList")) {
				Assertions.assertEquals("List", fieldDoc.getJsondocType().getType().get(0));
			}
			
			if(fieldDoc.getName().equals("parametrizedList")) {
				Assertions.assertEquals("List of String", fieldDoc.getJsondocType().getOneLineText());
			}
			
			if(fieldDoc.getName().equals("name")) {
				Assertions.assertEquals("String", fieldDoc.getJsondocType().getType().get(0));
				Assertions.assertEquals("name", fieldDoc.getName());
				Assertions.assertEquals("true", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("age")) {
				Assertions.assertEquals("Integer", fieldDoc.getJsondocType().getType().get(0));
				Assertions.assertEquals("age", fieldDoc.getName());
				Assertions.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("avg")) {
				Assertions.assertEquals("Long", fieldDoc.getJsondocType().getType().get(0));
				Assertions.assertEquals("avg", fieldDoc.getName());
				Assertions.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("map")) {
				Assertions.assertEquals("Map", fieldDoc.getJsondocType().getType().get(0));
				Assertions.assertEquals("String", fieldDoc.getJsondocType().getMapKey().getType().get(0));
				Assertions.assertEquals("Integer", fieldDoc.getJsondocType().getMapValue().getType().get(0));
			}
			
			if(fieldDoc.getName().equals("LongArray")) {
				Assertions.assertEquals("Array of Long", fieldDoc.getJsondocType().getOneLineText());
				Assertions.assertEquals("LongArray", fieldDoc.getName());
				Assertions.assertEquals("false", fieldDoc.getRequired());
			}

			if(fieldDoc.getName().equals("primitiveLongArray")) {
				Assertions.assertEquals("Array of long", fieldDoc.getJsondocType().getOneLineText());
				Assertions.assertEquals("primitiveLongArray", fieldDoc.getName());
				Assertions.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("fooBar")) {
				Assertions.assertEquals("String", fieldDoc.getJsondocType().getOneLineText());
				Assertions.assertEquals("foo_bar", fieldDoc.getName());
				Assertions.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("version")) {
				Assertions.assertEquals("String", fieldDoc.getJsondocType().getOneLineText());
				Assertions.assertEquals("1.0", fieldDoc.getSupportedversions().getSince());
				Assertions.assertEquals("2.12", fieldDoc.getSupportedversions().getUntil());
			}
			
			if(fieldDoc.getName().equals("test-enum")) {
				Assertions.assertEquals("test-enum", fieldDoc.getName());
				Assertions.assertEquals(TestEnum.TESTENUM1.name(), fieldDoc.getAllowedvalues()[0]);
				Assertions.assertEquals(TestEnum.TESTENUM2.name(), fieldDoc.getAllowedvalues()[1]);
				Assertions.assertEquals(TestEnum.TESTENUM3.name(), fieldDoc.getAllowedvalues()[2]);
			}
			
			if(fieldDoc.getName().equals("test-enum-with-allowed-values")) {
				Assertions.assertEquals("A", fieldDoc.getAllowedvalues()[0]);
				Assertions.assertEquals("B", fieldDoc.getAllowedvalues()[1]);
				Assertions.assertEquals("C", fieldDoc.getAllowedvalues()[2]);
			}

			if(fieldDoc.getName().equals("orderedProperty")) {
				Assertions.assertEquals("orderedProperty", fieldDoc.getName());
				Assertions.assertEquals(1, fieldDoc.getOrder().intValue());
			} else {
				Assertions.assertEquals(Integer.MAX_VALUE, fieldDoc.getOrder().intValue());
			}

		}
	}

}
