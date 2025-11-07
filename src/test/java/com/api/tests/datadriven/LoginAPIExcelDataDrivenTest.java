package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIExcelDataDrivenTest {
	
	
	@Test(description="Verifying if login API is working for FD user",
			groups= {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider="LoginAPIExcelDataProvider")
	public void loginAPITest(UserCredentials userCredentials) throws IOException
	{
		
		
		
		given().spec(SpecUtil.requestSpec(userCredentials))
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
