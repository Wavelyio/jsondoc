package com.axonmobileiot.jsondoc.core.util.controller;

import com.axonmobileiot.jsondoc.core.annotation.*;
import com.axonmobileiot.jsondoc.core.pojo.ApiVerb;
import com.axonmobileiot.jsondoc.core.util.pojo.Parent;

import java.util.List;
import java.util.Map;

@Api(name="Test2Controller", description="My test controller #2")
public class Test2Controller {
	
	@ApiMethod(
			path="/test2", 
			verb=ApiVerb.POST,
			description="test method for controller 2", 
			consumes={"application/json", "application/xml"},
			produces={"application/json", "application/xml"}
	)
	@ApiVersion(since = "1.0", until = "2.12")
	@ApiAuthBasic(roles = "ROLE_USER", testusers = {@ApiAuthBasicUser(username = "test-username", password = "test-password")})
	public @ApiResponseObject String save(@ApiBodyObject List<String> names) {
		return null;
	}
	
	@ApiMethod(
			path="/test2",
			verb=ApiVerb.PUT,
			description="update method for controller 2",
			consumes={"application/json", "application/xml"},
			produces={"application/json", "application/xml"}
	)
	@ApiVersion(since = "1.0", until = "2.12")
	@ApiAuthBasic(roles = "ROLE_USER", testusers = {@ApiAuthBasicUser(username = "test-username", password = "test-password")})
	public @ApiResponseObject String update(@ApiBodyObject List<String> names) {
		return null;
	}

	@ApiMethod(
			path="/test2",
			verb=ApiVerb.PATCH,
			description="patch update method for controller 2",
			consumes={"application/json", "application/xml"},
			produces={"application/json", "application/xml"}
	)
	@ApiVersion(since = "1.0", until = "2.12")
	@ApiAuthBasic(roles = "ROLE_USER", testusers = {@ApiAuthBasicUser(username = "test-username", password = "test-password")})
	public @ApiResponseObject String patch(@ApiBodyObject Map<String, Object> properties) {
		return null;
	}

	@ApiMethod(
			path="/testMap", 
			verb=ApiVerb.GET, 
			description="map method for controller 2", 
			consumes={"application/json", "application/xml"},
			produces={"application/json", "application/xml"}
	)
	public @ApiResponseObject Map<String, Parent> map(@ApiBodyObject List<String> names) {
		return null;
	}
	
	@ApiMethod(
			path="/testMapBody", 
			verb=ApiVerb.GET, 
			description="map body method for controller 2", 
			consumes={"application/json", "application/xml"},
			produces={"application/json", "application/xml"}
	)
	public @ApiResponseObject String map(@ApiBodyObject Map<String, Parent> names) {
		return null;
	}
	
	@ApiMethod(
			path="/testDelete", 
			verb=ApiVerb.DELETE, 
			description="delete test method for controller 2", 
			consumes={},
			produces={"application/json", "application/xml"}
	)
	public @ApiResponseObject void delete(@ApiPathParam(name="parent", description="A parent object") Parent parent) {
		
	}
	
	@ApiMethod(
			path="/testOptions", 
			verb=ApiVerb.OPTIONS, 
			description="options test method for controller 2", 
			consumes={},
			produces={"application/json", "application/xml"}
	)
	public @ApiResponseObject String options() {
		return "options";
	}
	
	@ApiMethod(
			path="/testHead", 
			verb=ApiVerb.HEAD, 
			description="head test method for controller 2", 
			consumes={},
			produces={"application/json", "application/xml"}
	)
	public @ApiResponseObject String head() {
		return "head";
	}

	@ApiMethod(
			path="/testtrace", 
			verb=ApiVerb.TRACE, 
			description="trace test method for controller 2", 
			consumes={},
			produces={"application/json", "application/xml"}
			)
	public @ApiResponseObject String trace() {
		return "trace";
	}

}
