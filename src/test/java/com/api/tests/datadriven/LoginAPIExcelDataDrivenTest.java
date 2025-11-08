package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIExcelDataDrivenTest {
	
	
	@Test(description="Verifying if login API is working for FD user",
			groups= {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider="LoginAPIExcelDataProvider")
	public void loginAPITest(UserBean userBean) 
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
