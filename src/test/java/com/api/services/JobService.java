package com.api.services;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT="/job/create";
	
	public Response createJob(Role role,CreateJobPayload createJobPayload)
	{
		Response response = RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(role, createJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
		
		return response;
		
		
	}
}
