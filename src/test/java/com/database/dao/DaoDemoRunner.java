package com.database.dao;

import com.database.model.CustomerProductDBModel;

public class DaoDemoRunner {

	public static void main(String[] args) {
		
	 	CustomerProductDBModel customerProductDBModel = CustomerProductDao.getProductInfoFromDB(115821);
		
		System.out.println(customerProductDBModel);

	}

}






