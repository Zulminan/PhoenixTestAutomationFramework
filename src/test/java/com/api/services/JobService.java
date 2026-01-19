package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT="/job/create";
	
	private static final String SEARCH_ENDPOINT = "/job/search";
	
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);
	
	public Response createJob(Role role,CreateJobPayload createJobPayload)
	{
		LOGGER.info("Making request to {} with the role {} and payload {}",CREATE_JOB_ENDPOINT,role,createJobPayload);
		
		Response response = RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(role, createJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
		
		return response;
		
		
	}
	
	public Response search(Role role, Object payload)
	{
		LOGGER.info("Making request to {} with the role {} and payload {}",SEARCH_ENDPOINT,role,payload);
		
		Response response = RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(role))
		.body(payload)
		.when()
		.post(SEARCH_ENDPOINT);
		
		return response;
		
		
	}
}
