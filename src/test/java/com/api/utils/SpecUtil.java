package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	

	//GET and DEL
	public static RequestSpecification requestSpec()
	{
		//to take care of the common request sections (methods)
		
	   RequestSpecification request = new RequestSpecBuilder()
	   .setBaseUri(ConfigManager.getProperty("BASE_URI"))
	   .and()
	   .setContentType(ContentType.JSON)
	   .and()
	   .setAccept(ContentType.JSON)
	   .and()
	   .log(LogDetail.URI)
	   .log(LogDetail.METHOD)
	   .log(LogDetail.HEADERS)
	   .log(LogDetail.BODY)
	   
	   .build();
	   
	   return request;
	}
	
	//POST, PUT, and PATCH
	public static RequestSpecification requestSpec(Object payload)
	{
		//to take care of the common request sections (methods)
		
	   RequestSpecification request = new RequestSpecBuilder()
	   .setBaseUri(ConfigManager.getProperty("BASE_URI"))
	   .and()
	   .setContentType(ContentType.JSON)
	   .and()
	   .setAccept(ContentType.JSON)
	   .and()
	   .log(LogDetail.URI)
	   .log(LogDetail.METHOD)
	   .log(LogDetail.HEADERS)
	   .log(LogDetail.BODY)
	   .and()
	   .setBody(payload)
	   
	   .build();
	   
	   return request;
	}
	
	public static ResponseSpecification responseSpec_OK()
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
		
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role)
	{
		RequestSpecification request = new RequestSpecBuilder()
				   .setBaseUri(ConfigManager.getProperty("BASE_URI"))
				   .and()
				   .setContentType(ContentType.JSON)
				   .and()
				   .setAccept(ContentType.JSON)
				   .and()
				   .addHeader("Authorization",AuthTokenProvider.getToken(role))
				   .log(LogDetail.URI)
				   .log(LogDetail.METHOD)
				   .log(LogDetail.HEADERS)
				   .log(LogDetail.BODY)
				   .build();
				   
				   return request;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role, Object payload)
	{
		RequestSpecification request = new RequestSpecBuilder()
				   .setBaseUri(ConfigManager.getProperty("BASE_URI"))
				   .and()
				   .setContentType(ContentType.JSON)
				   .and()
				   .setAccept(ContentType.JSON)
				   .and()
				   .addHeader("Authorization",AuthTokenProvider.getToken(role))
				   .log(LogDetail.URI)
				   .log(LogDetail.METHOD)
				   .log(LogDetail.HEADERS)
				   .log(LogDetail.BODY)
				   .setBody(payload)
				   .build();
				   
				   return request;
	}
	
	
	public static ResponseSpecification responseSpec_JSON(int statusCode)
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
	    .expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
		
	}
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode)
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
}
