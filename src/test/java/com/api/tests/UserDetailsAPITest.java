package com.api.tests;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	
	public void userDetailsAPITest() throws IOException
	{
		
		Header authHeader = new Header("Authorization",AuthTokenProvider.getToken(Role.FD));
		
		RestAssured
		.given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header(authHeader)
		.and()
		.accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().body()
		.log().headers()
		.when()
		.get("/userdetails")
		.then()
		.log().all()	
		.statusCode(200)
		.and()
		.time(Matchers.lessThan(1000L))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"));
	}

}
