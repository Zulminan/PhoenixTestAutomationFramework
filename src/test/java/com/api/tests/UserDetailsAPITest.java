package com.api.tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)

@Epic("User Management")
@Feature("User Details")
public class UserDetailsAPITest {
	
private UserService userService;
	
	@BeforeMethod(description="Setting up the UserService instance")
	public void setup()
	{
		userService = new UserService();
	}
	
	@Story("User details should be shown")
	@Description("Verify if the userdetails API response is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
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
