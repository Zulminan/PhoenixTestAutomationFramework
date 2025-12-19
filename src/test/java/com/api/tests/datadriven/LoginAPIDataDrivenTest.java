package com.api.tests.datadriven;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDataDrivenTest {
	
	private AuthService authService;
	
	@BeforeMethod(description="Initializing the Auth Service")
	public void setup()
	{
		 authService = new AuthService();
	}
	
	@Test(description="Verifying if login API is working for FD user",
			groups= {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider="LoginAPIDataProvider")
	public void loginAPITest(UserBean userBean) throws IOException
	{
		
		authService.login(userBean)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body("message", Matchers.equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));
		
		
		
		
	}

}
