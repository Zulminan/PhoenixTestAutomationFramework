package com.api.tests;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.MasterService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)

@Epic("Job Management")
@Feature("Master API")
public class MasterAPITest {
	
	
	private MasterService masterService;
	
	@BeforeMethod(description="Instantiating the Master Service Object")
	public void setup()
	{
		masterService = new MasterService();
	}
	
	@Story("Master API should bring OEM details, Problem type, Warranty status")
	@Description("Verifying if master API is giving correct response")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test(description="Verifying if master API is giving correct response",groups= {"api","regression","smoke"})
	public void masterAPITest()
	{
		masterService.master(Role.FD)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body("message",Matchers.equalTo("Success"))
		.and()
		.body("data",Matchers.notNullValue())
		.and()
		.body("data",Matchers.hasKey("mst_oem"))
		.and()
		.and()
		.body("data",Matchers.hasKey("mst_model"))
		.and()
		.body("$",Matchers.hasKey("message"))
		.and()
		.body("data.mst_oem.size()",Matchers.greaterThan(0))
		.and()
		.body("data.mst_oem.id",Matchers.everyItem(Matchers.notNullValue()))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"masterAPIResponseSchema.json"));
		
		
		
	}
	
	
	@Test(description="Verifying if master API is giving correct status code for invalid token",groups= {"api","negative","regression","smoke"})
	public void invalidTokenMasterAPITest()
	{
		RestAssured
		.given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post(File.separator+"master")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}

}
