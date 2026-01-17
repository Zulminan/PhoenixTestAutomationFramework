package com.api.utils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class AuthTokenProvider {
	
	private static Map<Role,String> tokenCache = new ConcurrentHashMap<Role,String>();
	
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);
	
	private AuthTokenProvider()
	{
		
	}
	
	public static String getToken(Role role) {
		
	
		LOGGER.info("Checking if the token for {} is present in the cache",role);
		
		if(tokenCache.containsKey(role))
		{
			LOGGER.info("Token found for {}",role);
			
			return tokenCache.get(role);
		}
		
		
		LOGGER.info("Token not found making the login request for the role {}",role);
		
		UserCredentials userCredentials = null;
		
		if(role==Role.FD)
		{
			userCredentials = new UserCredentials("iamfd", "password");
		}
		
		else if(role==Role.SUP)
			{
			userCredentials = new UserCredentials("iamsup", "password");
			}
		
		else if(role==Role.ENG)
		{
		userCredentials = new UserCredentials("iameng", "password");
		}
		
		else if(role==Role.QC)
		{
		userCredentials = new UserCredentials("iamqc", "password");
		}
		
		
		String token = RestAssured.given()
		            .baseUri(ConfigManager.getProperty("BASE_URI"))
		            .and()
		            .contentType(ContentType.JSON)
		            .and()
		            .accept(ContentType.JSON)
		            .and()
		            .body(userCredentials)
		            .log().uri()
		            .log().body()
		            .log().method()
		            .log().headers()
		            .when()
		            .post(File.separator+"login")
		            .then()
		            .log().ifValidationFails()
		            .statusCode(200)
		            .and()
		            .time(Matchers.lessThan(1000L))
		            .body("message",Matchers.equalTo("Success"))
		            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"loginResponseSchema.json"))
		            .extract().path("data.token");
		
		LOGGER.info("Token Cached for future request");
		
                  tokenCache.put(role, token); 		
 		
		           return token;
		
		
		            
	}

}
