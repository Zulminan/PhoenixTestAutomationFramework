package com.database.dao;

import com.database.model.JobHeadModel;

public class DaoDemoRunner {

	public static void main(String[] args) {
		
	 	JobHeadModel jobHeadModel = JobHeadDao.getDataFromJobHead(116975);
		
		System.out.println(jobHeadModel);

	}

}






