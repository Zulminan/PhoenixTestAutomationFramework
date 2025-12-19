package com.api.services;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserService {
	
	private static final String USERDETAILS_ENDPOINT="/userdetails";
	
	public Response userDetails(Role role)
	{
		Response response = RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get(USERDETAILS_ENDPOINT);
		
		return response;
	}

}
