package com.api.tests;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	private DashboardService dashboardService;
	
	@BeforeMethod(description="Setting up the DashboardService instance")
	public void setup()
	{
		dashboardService = new DashboardService();
		
	}
	
	
	@Test(description="Verify if count API is giving correct response",groups= {"api","regression","smoke"})
	public void verifyCountAPIResponse()
	{
		dashboardService.count(Role.FD)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body("message",Matchers.equalTo("Success"))
		.and()
		.body("data.size()",Matchers.equalTo(3))
		.and()
		.body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.and()
		.body("data.key",Matchers.containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"countAPIResponseSchema-FD.json"));
		
	}
	
	
	@Test(description="Verifying if count API is giving correct status code for invalid token",groups= {"api","negative","regression","smoke"})
	public void countAPITest_missingAuthToken()
	{
		dashboardService.countWithNoAuth()
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}

}
