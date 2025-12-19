package com.api.services;

import java.io.File;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;

public class MasterService {

	private static final String MASTER_ENDPOINT="/userdetails";
	
public void masterService(Role role)
{
	RestAssured
	.given()
	.spec(SpecUtil.requestSpecWithAuth(role))
	.when()
	.post(MASTER_ENDPOINT);
}
	
}
