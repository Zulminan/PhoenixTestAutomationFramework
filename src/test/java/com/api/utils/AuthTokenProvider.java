package com.api.utils;

import java.io.File;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		
	}
	
	public static String getToken(Role role) {
		
		//I want to make a login request and I will extract the token.
		
		UserCredentials userCredentials = null;
		
		if(role==Role.FD)
		{
			userCredentials = new UserCredentials("iamfd", "password");
		}
		
		else if(role==Role.SUP)
			{
			userCredentials = new UserCredentials("iamsup", "password");
			}
		
		else if(role==Role.ENG)
		{
		userCredentials = new UserCredentials("iameng", "password");
		}
		
		else if(role==Role.QC)
		{
		userCredentials = new UserCredentials("iamqc", "password");
		}
		
		
		String token = RestAssured.given()
		            .baseUri(ConfigManager.getProperty("BASE_URI"))
		            .and()
		            .contentType(ContentType.JSON)
		            .and()
		            .accept(ContentType.JSON)
		            .and()
		            .body(userCredentials)
		            .log().uri()
		            .log().body()
		            .log().method()
		            .log().headers()
		            .when()
		            .post(File.separator+"login")
		            .then()
		            .log().ifValidationFails()
		            .statusCode(200)
		            .and()
		            .time(Matchers.lessThan(1000L))
		            .body("message",Matchers.equalTo("Success"))
		            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"loginResponseSchema.json"))
		            .extract().path("data.token");
		
		           return token;
		
		
		            
	}

}
