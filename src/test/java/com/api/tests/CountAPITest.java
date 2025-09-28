package com.api.tests;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse()
	{
		RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get(File.separator+"dashboard"+File.separator+"count")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body("message",Matchers.equalTo("Success"))
		.and()
		.body("data.size()",Matchers.equalTo(3))
		.and()
		.body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.and()
		.body("data.key",Matchers.containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"countAPIResponseSchema-FD.json"));
		
	}
	
	
	@Test
	public void countAPITest_missingAuthToken()
	{
		RestAssured.given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get(File.separator+"dashboard"+File.separator+"count")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}

}
