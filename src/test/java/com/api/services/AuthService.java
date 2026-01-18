package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.response.Response;

public class AuthService {

	//Service class! it is going to hold the APIs that belong to Auth
	
	private static final String LOGIN_ENDPOINT = "login";
	
	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
	
	public Response login(Object userCredentials)
	{
		LOGGER.info("Making logging request for the payload {}",((UserBean)userCredentials).getUsername());
		
		Response response = given().spec(SpecUtil.requestSpec(userCredentials))
		.when()
		.post("/"+LOGIN_ENDPOINT);
		
		return response;
	}
}
