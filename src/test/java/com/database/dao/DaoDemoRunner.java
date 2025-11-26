package com.database.dao;

import org.testng.Assert;

import com.api.request.model.Customer;
import com.database.model.CustomerDBModel;

public class DaoDemoRunner {

	public static void main(String[] args) {
		
		CustomerDBModel customerDBModel = CustomerDao.getCustomerInfo();
		
		Customer customer = new Customer("Zulminan", "Ahmed", "9876543210", "", "ahmedzulminan@gmail.com", "");
		
		
		System.out.println(customer.first_name());
		
		Assert.assertEquals(customerDBModel.getFirst_name(), customer.first_name());

	}

}
