package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter{
	
	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		System.out.println("---------------- Hello From The Filter !!! ----------------");
		
		redactPayload(requestSpec);
		
		Response response = ctx.next(requestSpec, responseSpec);
		
		System.out.println("---------------- I Got The Response In Filter !!! ----------------");
		
		redactResponseBody(response);
		
		return response;
		
	}
	
	//Create a method which is going to RECACT / Hide the password from the request Payload
	
	

	public void redactPayload(FilterableRequestSpecification requestSpec)
	{
		String requestPayload = requestSpec.getBody().toString(); //Print the request body in String format
		
		//Journey to hide the payload
		
		requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\":\"[REDACTED]\"");
		
		LOGGER.info("REQUEST PAYLOAD : {}",requestPayload);
	}
	
	public void redactResponseBody(Response response) 
	{
		
		String responseBody = response.asPrettyString();
		
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[REDACTED]\"");
		
		LOGGER.info("RESPONSE BODY : {}",responseBody);
		
		
		
	}

}
