package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	//GET and DEL
	@Step("Setting up the BaseURI, Content Type as Application/JSON and attaching the SensitiveData filter ")
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
	   .addFilter(new SensitiveDataFilter())
	   .addFilter(new AllureRestAssured())
	   .build();
	   
	   return request;
	}
	
	//POST, PUT, and PATCH
	@Step("Setting up the BaseURI, Content Type as Application/JSON and attaching the SensitiveData filter")
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
	   .setBody(payload)
	   .addFilter(new SensitiveDataFilter())
	   .addFilter(new AllureRestAssured())
	   .and()
	   
	   
	   .build();
	   
	   return request;
	}
	
	@Step("Expecting the response to have Content type as Application/Json, Status code 200 and Response Time less than 1000 ms")
	public static ResponseSpecification responseSpec_OK()
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(1000L))
		.build();
		
		return responseSpecification;
		
	}
	
	@Step("Setting up the BaseURI, Content Type as Application/JSON and attaching the SensitiveData filter for a role")
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
				   .addFilter(new SensitiveDataFilter())
				   .addFilter(new AllureRestAssured())
				   .build();
				   
				   return request;
	}
	
	@Step("Setting up the BaseURI, Content Type as Application/JSON and attaching the SensitiveData filter for a role and attaching payload")
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
				   .setBody(payload)
				   .addFilter(new SensitiveDataFilter())
				   .addFilter(new AllureRestAssured())
				   .build();
				   
				   return request;
	}
	
	@Step("Expecting the response to have Content type as Application/Json and Response Time less than 1000 ms and statuc code")
	public static ResponseSpecification responseSpec_JSON(int statusCode)
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
	    .expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.build();
		
		return responseSpecification;
		
	}
	
	
	@Step("Expecting the response to have Content type as Text and Response Time less than 1000 ms and statuc code")
	public static ResponseSpecification responseSpec_TEXT(int statusCode)
	{
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.build();
		
		return responseSpecification;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
}
