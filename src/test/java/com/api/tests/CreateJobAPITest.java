package com.api.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest()
	{
		
		Customer customer = new Customer("Zulminan", "Ahmed", "9876543210", "", "ahmedzulminan@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("401", "Orchid Apartment", "Punjabi Gali", "IndusInd Bank ATM ", "Okhla", "110025", "India", "Delhi");
		
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "86941365683924", "86941365683924", "86941365683924", DateTimeUtil.getTimeWithDaysAgo(10), 1, 1);
		
		Problems problems = new Problems(1, "Battery Issue");
		
		List<Problems> problemsList = new ArrayList<Problems>();
		
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,problemsList);
		
		
		RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.log().all()
		.body("message",Matchers.equalTo("Job created successfully. "))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema"+File.separator+"createJobResponseSchema.json"))
        .and()
        .body("data.mst_service_location_id",Matchers.equalTo(1))
		.and()
		.body("data.job_number", Matchers.startsWith("JOB_"));
        
		
	}

}
