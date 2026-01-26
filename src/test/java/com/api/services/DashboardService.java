package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	private static final String DETAIL_ENDPOINT = "/dashboard/details";
	
	private static final Logger LOGGER = LogManager.getLogger(DashboardService.class);
	
	
	@Step("Making Count API Request for the Role ")
	public Response count(Role role)
	{
		LOGGER.info("Making request to the {} for the role {}",COUNT_ENDPOINT,role);
		
		Response response = RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	@Step("Making Count API Request without Auth token ")
	public Response countWithNoAuth()
	{
		LOGGER.info("Making request to the {} with no Auth Token",COUNT_ENDPOINT);
		
		Response response = RestAssured
		.given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	@Step("Making Details API Request")
	public Response details(Role role, Object payload)
	{
		LOGGER.info("Making request to the {} with role {} and the payload {}",DETAIL_ENDPOINT,role,payload);
		
		Response response = RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.body(payload)
		.when()
		.post(DETAIL_ENDPOINT);
		
		return response;
	}
	
	

}
