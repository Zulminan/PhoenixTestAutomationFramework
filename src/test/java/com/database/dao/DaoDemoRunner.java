package com.database.dao;

import com.database.model.MapJobProblemModel;

public class DaoDemoRunner {

	public static void main(String[] args) {
		
		MapJobProblemModel mapJobProblemModel = MapJobProblemDao.getProblemDetails(118957);
		
		System.out.println(mapJobProblemModel);

	}

}






