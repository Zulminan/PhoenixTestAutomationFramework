package com.api.tests;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse()
	{
		RestAssured
		.given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.and()
		.accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().body()
		.log().headers()
		.when()
		.get(File.separator+"dashboard"+File.separator+"count")
		.then()
		.log().all()
		.statusCode(200)
		.and()
		.time(Matchers.lessThan(1000L))
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
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().body()
		.log().headers()
		.when()
		.get(File.separator+"dashboard"+File.separator+"count")
		.then()
		.log().all()
		.statusCode(401);
	}

}
