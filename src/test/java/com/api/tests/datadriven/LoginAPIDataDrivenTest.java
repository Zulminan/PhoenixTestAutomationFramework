package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDataDrivenTest {
	
	
	@Test(description="Verifying if login API is working for FD user",
			groups= {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider="LoginAPIDataProvider")
	public void loginAPITest(UserBean userBean) throws IOException
	{
		
		
		
		given().spec(SpecUtil.requestSpec(userBean))
		.when()
		.post("/login")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body("message", Matchers.equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));
		
		
		
		
	}

}
