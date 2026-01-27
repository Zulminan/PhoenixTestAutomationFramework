package com.api.tests;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)

@Epic("User Management")
@Feature("Authentication")
public class LoginAPITest {
	
	private UserBean userCredentials;
	
	private AuthService authService;
	
	@BeforeMethod(description="Create the payload for the login API")
	public void setup()
	{
		userCredentials = new UserBean("iamfd", "password");
		
		authService = new AuthService();
		
	}
	
	@Story("Valid User Should Be Able To Login Into The System")
	@Description("Verify if FD user is able to login via API")
	@Severity(SeverityLevel.BLOCKER)
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
