package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest()
	{
		
		Customer customer = new Customer("Zulminan", "Ahmed", "9876543210", "", "ahmedzulminan@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("401", "Orchid Apartment", "Punjabi Gali", "IndusInd Bank ATM ", "Okhla", "110025", "India", "Delhi");
		
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "68941865683928", "68941865683928", "68941865683928", "2025-04-06T18:30:00.000Z", 1, 1);
		
		Problems problems = new Problems(1, "Battery Issue");
		
		Problems[] problemsArray = new Problems[1];
		
		problemsArray[0] = problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,problemsArray);
		
		
		
		
		RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.log().all()
		.body("message",Matchers.equalTo("Job created successfully. "));

		
	}

}
