package com.api.tests.datadriven;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIExcelDataDrivenTest {

	private JobService jobService;
	
	@BeforeMethod
	public void setup()
	{
		jobService = new JobService();
	}

	@Test(description="Verify if create job API is able to create Inwarranty jobs",groups= {"api","regression","datadriven","excel"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,dataProvider="CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload)
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
