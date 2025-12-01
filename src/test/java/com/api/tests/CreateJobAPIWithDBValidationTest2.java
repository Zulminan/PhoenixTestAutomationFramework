package com.api.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
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
import com.api.response.model.CreateJobResponseModel;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIWithDBValidationTest2 {
	
	private CreateJobPayload createJobPayload;
	
	private Customer customer;
	
	private CustomerAddress customerAddress;
	
	private CustomerProduct customerProduct;
	
	
	@BeforeMethod(description="Creating create job API request payload")
	public void setup()
	{
        customer = new Customer("Zulminan", "Ahmed", "9876543210", "", "ahmedzulminan@gmail.com", "");
		
		customerAddress = new CustomerAddress("401", "Orchid Apartment", "Punjabi Gali", "IndusInd Bank ATM ", "Okhla", "110025", "India", "Delhi");
		
		customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "32999878216814", "32999878216814", "32999878216814", DateTimeUtil.getTimeWithDaysAgo(10), 
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		
		List<Problems> problemsList = new ArrayList<Problems>();
		
		problemsList.add(problems);
		
		createJobPayload = new CreateJobPayload(ServiceLocation.SERVCE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct,problemsList);
		
	}
	
	
	@Test(description="Verify if create job API is able to create Inwarranty jobs",groups= {"api","regression","smoke"})
	public void createJobAPITest()
	{
		CreateJobResponseModel createJobResponseModel = RestAssured.given()
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
		.body("data.job_number", Matchers.startsWith("JOB_"))
		.extract().as(CreateJobResponseModel.class);
		
		System.out.println(createJobResponseModel);
		
		int customerId = createJobResponseModel.getData().getTr_customer_id();
		
		CustomerDBModel customerDataDromDB = CustomerDao.getCustomerInfo(customerId);
		
		Assert.assertEquals(customer.first_name(),customerDataDromDB.getFirst_name());
		Assert.assertEquals(customer.last_name(),customerDataDromDB.getLast_name());
		Assert.assertEquals(customer.mobile_number(),customerDataDromDB.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(),customerDataDromDB.getMobile_number_alt());
		Assert.assertEquals(customer.email_id(),customerDataDromDB.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(),customerDataDromDB.getEmail_id_alt());
		
		CustomerAddressDBModel customerAddressFromDB = CustomerAddressDao.getCustomerAddress(customerDataDromDB.getTr_customer_address_id());
		
		Assert.assertEquals(customerAddressFromDB.getFlat_number(),customerAddress.flat_number());
		Assert.assertEquals(customerAddressFromDB.getApartment_name(),customerAddress.apartment_name());
		Assert.assertEquals(customerAddressFromDB.getStreet_name(),customerAddress.street_name());
		Assert.assertEquals(customerAddressFromDB.getLandmark(),customerAddress.landmark());
		Assert.assertEquals(customerAddressFromDB.getArea(),customerAddress.area());
		Assert.assertEquals(customerAddressFromDB.getPincode(),customerAddress.pincode());
		Assert.assertEquals(customerAddressFromDB.getCountry(),customerAddress.country());
		Assert.assertEquals(customerAddressFromDB.getState(),customerAddress.state());
		
		int tr_customer_product_id = createJobResponseModel.getData().getTr_customer_product_id();
		
		CustomerProductDBModel customerProductDBData =   CustomerProductDao.getProductInfoFromDB(tr_customer_product_id);
		
		Assert.assertEquals(customerProductDBData.getDop(), customerProduct.dop());
		Assert.assertEquals(customerProductDBData.getSerial_number(), customerProduct.serial_number());
		Assert.assertEquals(customerProductDBData.getImei1(), customerProduct.imei1());
		Assert.assertEquals(customerProductDBData.getImei2(), customerProduct.imei2());
		Assert.assertEquals(customerProductDBData.getPopurl(), customerProduct.popurl());	
		Assert.assertEquals(customerProductDBData.getMst_model_id(), customerProduct.mst_model_id());
		
		
		
	}

}
