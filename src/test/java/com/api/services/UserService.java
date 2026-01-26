package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserService {
	
	private static final String USERDETAILS_ENDPOINT="/userdetails";
	
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	
	@Step("Making UserDetails API request")
	public Response userDetails(Role role)
	{
		LOGGER.info("Making request to {} for the role {}",USERDETAILS_ENDPOINT,role);
		
		Response response = RestAssured
		.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get(USERDETAILS_ENDPOINT);
		
		return response;
	}

}
