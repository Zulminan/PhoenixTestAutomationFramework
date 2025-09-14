package com.api.tests;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	
	@Test
	public void loginAPITest()
	{
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
		.baseUri("http://64.227.160.186:9000/v1")
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
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"))
		.extract().path("data.token");
		
		
		
		
	}

}
