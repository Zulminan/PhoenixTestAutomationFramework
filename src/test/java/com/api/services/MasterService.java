package com.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MasterService {

	private static final String MASTER_ENDPOINT="/master";
	
	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);
	
@Step("Making Master API request")	
public Response master(Role role)
{
	
	LOGGER.info("Making request to {} for the role {}",MASTER_ENDPOINT,role);
	
	Response response = RestAssured
	.given()
	.spec(SpecUtil.requestSpecWithAuth(role))
	.when()
	.post(MASTER_ENDPOINT);
	
	return response;
	
	
}
	
}
