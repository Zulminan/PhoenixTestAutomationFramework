package com.api.tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	
	public void userDetailsAPITest() throws IOException
	{
		
		
		RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get(File.separator+"userdetails")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"userDetailsResponseSchema.json"));
	}

}
