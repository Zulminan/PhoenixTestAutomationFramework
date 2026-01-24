package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Details;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

@Listeners(com.listeners.APITestListener.class)

public class DetailsAPITest {
	
	private DashboardService dashboardService;
	
	private Details detailPayload;

	
	@BeforeMethod(description="Instantiating the Dashboard service and creating detail payload")
	public void setup()
	{
		dashboardService = new DashboardService();
		
		detailPayload = new Details("created_today");
	}
	
	@Test(description="Verify if the details API is working properly",groups={"api","smoke","e2e"})
	public void detailAPITest()
	{
		dashboardService.details(Role.FD, detailPayload)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"));
	}
}
