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

public class MasterAPITest {
	
	@Test
	public void masterAPITest()
	{
		RestAssured
		.given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.and()
		.and()
		.contentType("")
		.and()
		.accept(ContentType.JSON)
		.and()
		.log().uri()
		.log().body()
		.log().method()
		.log().headers()
		.when()
		.post(File.separator+"master")
		.then()
		.log().all()
		.and()
		.statusCode(200)
		.and()
		.time(Matchers.lessThan(1000L))
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
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization","")
		.and()
		.and()
		.contentType("")
		.and()
		.accept(ContentType.JSON)
		.and()
		.log().uri()
		.log().body()
		.log().method()
		.log().headers()
		.when()
		.post(File.separator+"master")
		.then()
		.log().all()
		.and()
		.statusCode(401);
	}

}
