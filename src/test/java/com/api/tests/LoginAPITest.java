package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	
	@Test
	public void loginAPITest() throws IOException
	{
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(userCredentials)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when()
		.post("/login")
		.then()
		.log().all()
		.statusCode(200)
		.and()
		.time(Matchers.lessThan(1000L))
		.and()
		.body("message", Matchers.equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));
		
		
		
		
	}

}
