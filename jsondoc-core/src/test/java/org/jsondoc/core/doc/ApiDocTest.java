package org.jsondoc.core.doc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthBasic;
import org.jsondoc.core.annotation.ApiAuthBasicUser;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiParams;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiAuthType;
import org.jsondoc.core.pojo.ApiDoc;
import org.jsondoc.core.pojo.ApiErrorDoc;
import org.jsondoc.core.pojo.ApiMethodDoc;
import org.jsondoc.core.pojo.ApiParamDoc;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.DefaultJSONDocScanner;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.jsondoc.core.util.controller.Test3Controller;
import org.jsondoc.core.util.pojo.Child;
import org.jsondoc.core.util.pojo.Pizza;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiDocTest {
	
	private JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
	
	@Api(description = "An interface controller", name = "interface-controller")
	private interface InterfaceController {
		
		@ApiMethod(path = "/interface", verb = ApiVerb.GET)
		public String inter();
	}
	
	@SuppressWarnings("unused")
	private class InterfaceControllerImpl implements InterfaceController {

		@Override
		public String inter() {
			return null;
		}
		
	}

	@Api(name = "test-controller", description = "a-test-controller")
	@ApiVersion(since = "1.0", until = "2.12")
	@ApiAuthNone
	private class TestController {

		@ApiMethod(path = "/name", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		String name(@ApiPathParam(name = "name") String name, @ApiBodyObject String body) {
			return null;
		}

		@ApiMethod(path = "/age", verb = ApiVerb.GET, description = "a-test-method", responsestatuscode = "204")
		public @ApiResponseObject
		Integer age(@ApiPathParam(name = "age") Integer age, @ApiBodyObject Integer body) {
			return null;
		}

		@ApiMethod(path = "/avg", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		Long avg(@ApiPathParam(name = "avg") Long avg, @ApiBodyObject Long body) {
			return null;
		}

		@ApiMethod(path = "/map", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		Map<String, Integer> map(@ApiPathParam(name = "map") Map<String, Integer> map, @ApiBodyObject Map<String, Integer> body) {
			return null;
		}

		@SuppressWarnings("rawtypes")
		@ApiMethod(path = "/unparametrizedList", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		List unparametrizedList(@ApiPathParam(name = "unparametrizedList") List unparametrizedList, @ApiBodyObject List body) {
			return null;
		}

		@ApiMethod(path = "/parametrizedList", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		List<String> parametrizedList(@ApiPathParam(name = "parametrizedList") List<String> parametrizedList, @ApiBodyObject List<String> body) {
			return null;
		}

		@ApiMethod(path = "/wildcardParametrizedList", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		List<?> wildcardParametrizedList(@ApiPathParam(name = "wildcardParametrizedList") List<?> wildcardParametrizedList, @ApiBodyObject List<?> body) {
			return null;
		}
		
		@ApiMethod(path = "/LongArray", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		Long[] LongArray(@ApiPathParam(name = "LongArray") Long[] LongArray, @ApiBodyObject Long[] body) {
			return null;
		}

		@ApiMethod(path = "/longArray", verb = ApiVerb.GET, description = "a-test-method")
		public @ApiResponseObject
		long[] longArray(@ApiPathParam(name = "longArray") long[] LongArray, @ApiBodyObject long[] body) {
			return null;
		}
		
		@ApiMethod(path = "/version", verb = ApiVerb.GET, description = "a-test-method for api version feature")
		@ApiVersion(since = "1.0", until = "2.12")
		public @ApiResponseObject
		String version(@ApiPathParam(name = "version") String version, @ApiBodyObject String body) {
			return null;
		}
		
		@ApiMethod(path="/child", description = "A method returning a child", verb = ApiVerb.GET)
		public @ApiResponseObject 
		Child child(@ApiPathParam(name = "child") Child child, @ApiBodyObject Child body) {
			return null;
		}
		
		@ApiMethod(path="/pizza", description = "A method returning a pizza", verb = ApiVerb.GET)
		public @ApiResponseObject 
		Pizza pizza(@ApiPathParam(name = "pizza") Pizza pizza, @ApiBodyObject Pizza body) {
			return null;
		}

		@ApiMethod(path = "/multiple-request-methods", verb = { ApiVerb.GET, ApiVerb.POST }, description = "a-test-method-with-multiple-request-methods")
		public @ApiResponseObject
		Integer multipleRequestMethods(@ApiPathParam(name = "multiple-request-methods") Integer multipleRequestMethods, @ApiBodyObject Integer body) {
			return null;
		}
		
	}
	
	@Api(name = "test-controller-with-basic-auth", description = "a-test-controller with basic auth annotation")
	@ApiAuthBasic(roles = { "ROLE_USER", "ROLE_ADMIN" }, testusers = {@ApiAuthBasicUser(username = "test-username", password = "test-password")})
	private class TestControllerWithBasicAuth {
		
		@ApiMethod(path="/basicAuth", description = "A method with basic auth", verb = ApiVerb.GET)
		@ApiAuthBasic(roles = {"ROLE_USER"}, testusers = {@ApiAuthBasicUser(username = "test-username", password = "test-password")})
		public String basicAuth() {
			return null;
		}
		
		@ApiMethod(path="/noAuth", description = "A method with no auth", verb = ApiVerb.GET)
		@ApiAuthNone
		public String noAuth() {
			return null;
		}
		
		@ApiMethod(path="/undefinedAuthWithAuthOnClass", description = "A method with undefined auth but with auth info on class declaration", verb = ApiVerb.GET)
		public String undefinedAuthWithAuthOnClass() {
			return null;
		}
		
	}
	
	@Api(name = "test-controller-with-no-auth-annotation", description = "a-test-controller with no auth annotation")
	private class TestControllerWithNoAuthAnnotation {
		
		@ApiMethod(path="/basicAuth", description = "A method with basic auth", verb = ApiVerb.GET)
		@ApiAuthBasic(roles = {"ROLE_USER"}, testusers = {@ApiAuthBasicUser(username = "test-username", password = "test-password")})
		public String basicAuth() {
			return null;
		}
		
		@ApiMethod(path="/noAuth", description = "A method with no auth", verb = ApiVerb.GET)
		@ApiAuthNone
		public String noAuth() {
			return null;
		}
		
		@ApiMethod(path="/undefinedAuthWithoutAuthOnClass", description = "A method with undefined auth and without auth info on class declaration", verb = ApiVerb.GET)
		public String undefinedAuthWithoutAuthOnClass() {
			return null;
		}
		
	}
	
	@Api(name = "test-old-style-servlets", description = "a-test-old-style-servlet")
	private class TestOldStyleServlets {
		
		@ApiMethod(path="/oldStyle", description = "A method params on method level", verb = ApiVerb.GET)
		@ApiParams(pathparams = {
				@ApiPathParam(name = "name", clazz = String.class)
		})
		public String oldStyle() {
			return null;
		}
		
		@ApiMethod(path="/oldStyleWithList", description = "A method params on method level", verb = ApiVerb.GET)
		@ApiParams(pathparams = {
				@ApiPathParam(name = "name", clazz = List.class)
		})
		public String oldStyleWithList() {
			return null;
		}
		
		@ApiMethod(path="/oldStyleWithMap", description = "A method params on method level", verb = ApiVerb.GET)
		@ApiParams(pathparams = {
				@ApiPathParam(name = "name", clazz = Map.class)
		})
		public String oldStyleWithMap() {
			return null;
		}
		
		@ApiMethod(path="/oldStyleMixed", description = "A method params on method level", verb = ApiVerb.GET)
		@ApiParams(pathparams = {
					@ApiPathParam(name = "name", clazz = String.class),
					@ApiPathParam(name = "age", clazz = Integer.class),
					@ApiPathParam(name = "undefined")},
				queryparams = {
					@ApiQueryParam(name = "q", clazz = String.class, defaultvalue = "qTest")}
		)
		public String oldStyleMixed(@ApiPathParam(name = "age") Integer age) {
			return null;
		}
		
		@ApiMethod(path="/oldStyleResponseObject", description = "A method with populated ApiResponseObject annotation", verb = ApiVerb.GET)
		@ApiResponseObject(clazz = List.class)
		public void oldStyleResponseObject() {
			return;
		}
		
		@ApiMethod(path="/oldStyleBodyObject", description = "A method with populated ApiBodyObject annotation", verb = ApiVerb.GET)
		@ApiBodyObject(clazz = List.class)
		public void oldStyleBodyObject() {
			return;
		}

	}
	
	@Api(name = "test-errors-warnings-hints", description = "a-test-for-incomplete-documentation")
	private class TestErrorsAndWarningsAndHints {
		
		@ApiMethod
		public String oldStyle() {
			return null;
		}
		
	}
	
	@Api(name = "test-errors-warnings-hints-method-display-as-summary", description = "a-test-for-incomplete-documentation-for-method-display-summary")
	private class TestErrorsAndWarningsAndHintsMethodSummary {
		
		@ApiMethod
		public String summary() {
			return null;
		}
		
	}
	
	@Api(name = "test-declared-methods", description = "a-test-for-declared-methods")
	private class TestDeclaredMethods {
		
		@ApiMethod(path = "/protectedMethod")
		protected String protectedMethod() {
			return null;
		}
		
		@ApiMethod(path = "/privateMethod")
		private String privateMethod() {
			return null;
		}
		
	}
	
	@Api(name = "ISSUE-110", description = "ISSUE-110")
	private class TestMultipleParamsWithSameMethod {
		
	    @ApiMethod(path = "/search", description = "search one by title")
	    public @ApiResponseObject List findByTitle(@ApiQueryParam(name = "title") String title) {
	        return null;
	    }

	    @ApiMethod(path = "/search", description = "search one by content")
	    public @ApiResponseObject List findByContent(@ApiQueryParam(name = "content") String content) {
	        return null;
	    }
	    
	    @ApiMethod(path = "/search", description = "search one by content and field")
	    public @ApiResponseObject List findByContent(@ApiQueryParam(name = "content") String content, @ApiQueryParam(name = "field") String field) {
	        return null;
	    }
		
	}

	@Test
	public void testApiErrorsDoc() {

		final ApiDoc apiDoc = jsondocScanner.getApiDocs(Set.of(Test3Controller.class),
				MethodDisplay.URI).iterator().next();

		final Set<ApiMethodDoc> methods = apiDoc.getMethods();
		final ApiMethodDoc apiMethodDoc = methods.iterator().next();
		final List<ApiErrorDoc> apiErrors = apiMethodDoc.getApierrors();

		Assertions.assertEquals(1, methods.size());
		Assertions.assertEquals(3, apiErrors.size());
		Assertions.assertEquals("1000", apiErrors.get(0).getCode());
		Assertions.assertEquals("A test error #1", apiErrors.get(0).getDescription());
		Assertions.assertEquals("2000", apiErrors.get(1).getCode());
		Assertions.assertEquals("400", apiErrors.get(2).getCode());

	}

	@Test
	public void testApiDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TestController.class);
		ApiDoc apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("test-controller", apiDoc.getName());
		Assertions.assertEquals("a-test-controller", apiDoc.getDescription());
		Assertions.assertEquals("1.0", apiDoc.getSupportedversions().getSince());
		Assertions.assertEquals("2.12", apiDoc.getSupportedversions().getUntil());
		Assertions.assertEquals(ApiAuthType.NONE.name(), apiDoc.getAuth().getType());
		Assertions.assertEquals(DefaultJSONDocScanner.ANONYMOUS, apiDoc.getAuth().getRoles().get(0));

		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			
			if (apiMethodDoc.getPath().contains("/name")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("string", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				Assertions.assertEquals("200 - OK", apiMethodDoc.getResponsestatuscode());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("name")) {
						Assertions.assertEquals("string", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/age")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("204", apiMethodDoc.getResponsestatuscode());
				Assertions.assertEquals("integer", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("integer", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("age")) {
						Assertions.assertEquals("integer", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/avg")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("long", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("long", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("avg")) {
						Assertions.assertEquals("long", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/map")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("map[string, integer]", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("map[string, integer]", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("map")) {
						Assertions.assertEquals("map[string, integer]", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/parametrizedList")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("list of string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("list of string", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("parametrizedList")) {
						Assertions.assertEquals("list of string", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
				
			}

			if (apiMethodDoc.getPath().contains("/wildcardParametrizedList")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("list of wildcard", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("list of wildcard", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("wildcardParametrizedList")) {
						Assertions.assertEquals("list of wildcard", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/LongArray")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("array of long", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("array of long", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("LongArray")) {
						Assertions.assertEquals("array of long", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}

			if (apiMethodDoc.getPath().contains("/longArray")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("array of long", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assertions.assertEquals("array of long", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				for (ApiParamDoc apiParamDoc : apiMethodDoc.getPathparameters()) {
					if(apiParamDoc.getName().equals("longArray")) {
						Assertions.assertEquals("array of long", apiParamDoc.getJsondocType().getOneLineText());
					}
				}
			}
			
			if (apiMethodDoc.getPath().contains("/version")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("1.0", apiMethodDoc.getSupportedversions().getSince());
				Assertions.assertEquals("2.12", apiMethodDoc.getSupportedversions().getUntil());
			}
			
			if (apiMethodDoc.getPath().contains("/child")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("child", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/pizza")) {
				Assertions.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
				Assertions.assertEquals("customPizzaObject", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/multiple-request-methods")) {
				Assertions.assertEquals(2, apiMethodDoc.getVerb().size());
				Iterator<ApiVerb> iterator = apiMethodDoc.getVerb().iterator();
				Assertions.assertEquals(ApiVerb.GET, iterator.next());
				Assertions.assertEquals(ApiVerb.POST, iterator.next());
			}
			
		}

		classes.clear();
		classes.add(TestControllerWithBasicAuth.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("test-controller-with-basic-auth", apiDoc.getName());
		Assertions.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiDoc.getAuth().getType());
		Assertions.assertEquals("ROLE_USER", apiDoc.getAuth().getRoles().get(0));
		Assertions.assertEquals("ROLE_ADMIN", apiDoc.getAuth().getRoles().get(1));
		Assertions.assertTrue(apiDoc.getAuth().getTestusers().size() > 0);
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/basicAuth")) {
				Assertions.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiMethodDoc.getAuth().getType());
				Assertions.assertEquals("ROLE_USER", apiMethodDoc.getAuth().getRoles().get(0));
				Assertions.assertTrue(apiMethodDoc.getAuth().getTestusers().size() > 0);
			}
			
			if (apiMethodDoc.getPath().contains("/noAuth")) {
				Assertions.assertEquals(ApiAuthType.NONE.name(), apiMethodDoc.getAuth().getType());
				Assertions.assertEquals(DefaultJSONDocScanner.ANONYMOUS, apiMethodDoc.getAuth().getRoles().get(0));
			}
			
			if (apiMethodDoc.getPath().contains("/undefinedAuthWithAuthOnClass")) {
				Assertions.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiMethodDoc.getAuth().getType());
				Assertions.assertEquals("ROLE_USER", apiMethodDoc.getAuth().getRoles().get(0));
				Assertions.assertEquals("ROLE_ADMIN", apiMethodDoc.getAuth().getRoles().get(1));
			}
			
		}
		
		classes.clear();
		classes.add(TestControllerWithNoAuthAnnotation.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("test-controller-with-no-auth-annotation", apiDoc.getName());
		Assertions.assertNull(apiDoc.getAuth());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/basicAuth")) {
				Assertions.assertEquals(ApiAuthType.BASIC_AUTH.name(), apiMethodDoc.getAuth().getType());
				Assertions.assertEquals("ROLE_USER", apiMethodDoc.getAuth().getRoles().get(0));
				Assertions.assertTrue(apiMethodDoc.getAuth().getTestusers().size() > 0);
			}
			
			if (apiMethodDoc.getPath().contains("/noAuth")) {
				Assertions.assertEquals(ApiAuthType.NONE.name(), apiMethodDoc.getAuth().getType());
				Assertions.assertEquals(DefaultJSONDocScanner.ANONYMOUS, apiMethodDoc.getAuth().getRoles().get(0));
			}
			
			if (apiMethodDoc.getPath().contains("/undefinedAuthWithoutAuthOnClass")) {
				Assertions.assertNull(apiMethodDoc.getAuth());
			}
			
		}
		
		classes.clear();
		classes.add(TestOldStyleServlets.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("test-old-style-servlets", apiDoc.getName());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/oldStyle")) {
				Assertions.assertEquals(1, apiMethodDoc.getPathparameters().size());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleWithList")) {
				Assertions.assertEquals(1, apiMethodDoc.getPathparameters().size());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleWithMap")) {
				Assertions.assertEquals(1, apiMethodDoc.getPathparameters().size());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleMixed")) {
				Assertions.assertEquals(3, apiMethodDoc.getPathparameters().size());
				Assertions.assertEquals(1, apiMethodDoc.getQueryparameters().size());
				Assertions.assertEquals("qTest", apiMethodDoc.getQueryparameters().iterator().next().getDefaultvalue());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleResponseObject")) {
				Assertions.assertEquals("list", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/oldStyleBodyObject")) {
				Assertions.assertEquals("list", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
			}
		}
		
		classes.clear();
		classes.add(TestErrorsAndWarningsAndHints.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("test-errors-warnings-hints", apiDoc.getName());
		ApiMethodDoc apiMethodDoc = apiDoc.getMethods().iterator().next();
		Assertions.assertEquals(1, apiMethodDoc.getJsondocerrors().size());
		Assertions.assertEquals(1, apiMethodDoc.getJsondocwarnings().size());
		Assertions.assertEquals(2, apiMethodDoc.getJsondochints().size());
		
		classes.clear();
		classes.add(TestErrorsAndWarningsAndHintsMethodSummary.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.SUMMARY).iterator().next();
		apiMethodDoc = apiDoc.getMethods().iterator().next();
		Assertions.assertEquals(1, apiMethodDoc.getJsondocerrors().size());
		Assertions.assertEquals(1, apiMethodDoc.getJsondocwarnings().size());
		Assertions.assertEquals(3, apiMethodDoc.getJsondochints().size());
		
		classes.clear();
		classes.add(InterfaceController.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("interface-controller", apiDoc.getName());
		apiMethodDoc = apiDoc.getMethods().iterator().next();
		Assertions.assertNotNull(apiMethodDoc);
		Assertions.assertEquals("/interface", apiMethodDoc.getPath().iterator().next());
		
		classes.clear();
		classes.add(TestDeclaredMethods.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals("test-declared-methods", apiDoc.getName());
		Assertions.assertEquals(2, apiDoc.getMethods().size());
		
		
		classes.clear();
		classes.add(TestMultipleParamsWithSameMethod.class);
		apiDoc = jsondocScanner.getApiDocs(classes, MethodDisplay.URI).iterator().next();
		Assertions.assertEquals(3, apiDoc.getMethods().size());
		
	}

}
