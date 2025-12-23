package com.api.tests;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class LoginAPITest {
	
	private UserCredentials userCredentials;
	
	private AuthService authService;
	
	
	
	
	@BeforeMethod(description="Create the payload for the login API")
	public void setup()
	{
		userCredentials = new UserCredentials("iamfd", "password");
		
		authService = new AuthService();
		
	}
	
	
	@Test(description="Verifying if login API is working for FD user",groups= {"api","regression","smoke"})
	public void loginAPITest() throws IOException
	{
		
		
		authService.login(userCredentials)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body("message", Matchers.equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));
		
		
		
		
	}

}
