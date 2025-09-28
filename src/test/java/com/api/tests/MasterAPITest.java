package com.api.tests;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	public void masterAPITest()
	{
		RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.post(File.separator+"master")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body("message",Matchers.equalTo("Success"))
		.and()
		.body("data",Matchers.notNullValue())
		.and()
		.body("data",Matchers.hasKey("mst_oem"))
		.and()
		.and()
		.body("data",Matchers.hasKey("mst_model"))
		.and()
		.body("$",Matchers.hasKey("message"))
		.and()
		.body("data.mst_oem.size()",Matchers.greaterThan(0))
		.and()
		.body("data.mst_oem.id",Matchers.everyItem(Matchers.notNullValue()))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"masterAPIResponseSchema.json"));
		
		
		
	}
	
	
	@Test
	public void invalidTokenMasterAPITest()
	{
		RestAssured
		.given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post(File.separator+"master")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}

}
