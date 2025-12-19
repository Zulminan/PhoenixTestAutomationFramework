package com.api.services;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	public Response count(Role role)
	{
		Response response = RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response countWithNoAuth()
	{
		Response response = RestAssured
		.given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}

}
