package com.api.tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.AuthService;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
private UserService userService;
	
	@BeforeMethod(description="Setting up the UserService instance")
	public void setup()
	{
		userService = new UserService();
	}
	
	
	@Test(description="Verify if the userdetails API response is shown correctly",groups= {"api","smoke","regression"})
	public void userDetailsAPITest() throws IOException
	{
		
		userService.userDetails(Role.FD)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"userDetailsResponseSchema.json"));
	}

}
