package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class LoginAPIExcelDataDrivenTest {
	
private AuthService authService;
	
	@BeforeMethod(description="Setting up the Auth Service reference")
	public void setup()
	{
		 authService = new AuthService();
	}
	
	
	@Test(description="Verifying if login API is working for FD user",
			groups= {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider="LoginAPIExcelDataProvider")
	public void loginAPITest(UserBean userBean) 
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
