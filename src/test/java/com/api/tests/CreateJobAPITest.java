package com.api.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Model;
import com.api.request.model.Problems;
import com.api.services.JobService;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	private CreateJobPayload createJobPayload;
	
	private JobService jobService;
	
	@BeforeMethod(description="Creating create job API request payload and instantiating the Job Service")
	public void setup()
	{
        Customer customer = new Customer("Zulminan", "Ahmed", "9876543210", "", "ahmedzulminan@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("401", "Orchid Apartment", "Punjabi Gali", "IndusInd Bank ATM ", "Okhla", "110025", "India", "Delhi");
		
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "86991365683924", "86991365683924", "86991365683924", DateTimeUtil.getTimeWithDaysAgo(10), 
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		
		List<Problems> problemsList = new ArrayList<Problems>();
		
		problemsList.add(problems);
		
		createJobPayload = new CreateJobPayload(ServiceLocation.SERVCE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct,problemsList);
		
		jobService = new JobService();
	}
	
	
	@Test(description="Verify if create job API is able to create Inwarranty jobs",groups= {"api","regression","smoke"})
	public void createJobAPITest()
	{
		jobService.createJob(Role.FD, createJobPayload)
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
