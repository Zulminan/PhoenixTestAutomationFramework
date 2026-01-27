package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemModel;

import io.qameta.allure.Step;

public class MapJobProblemDao {
	
	private static final Logger LOGGER = LogManager.getLogger(MapJobProblemDao.class);
	
	
private static final String PROBLEM_QUERY = """

			select * from map_job_problem where tr_job_head_id = ?;			
			
			""";
	
	
	
	private MapJobProblemDao()
	{
		
	}
	
	@Step("Retrieving the problem details information from DB for the specific job head id")
	public static MapJobProblemModel getProblemDetails(int tr_job_head_id)
	{
		Connection conn =  DatabaseManager.getConnection();
		
		 PreparedStatement statement;
		 
		 MapJobProblemModel mapJobProblemModel = null;
		 
		 ResultSet resultSet;
		
		 try 
		 {
			 LOGGER.info("Getting the connection from the Database Manager");
			 
			statement = conn.prepareStatement(PROBLEM_QUERY);
			
			statement .setInt(1, tr_job_head_id);
			
			LOGGER.info("Executing the SQL Query {}",PROBLEM_QUERY);
			 
			 resultSet =  statement.executeQuery();
			 
			 while(resultSet.next())
			 {
				  mapJobProblemModel = new MapJobProblemModel
						 (
								 resultSet.getInt("id"),
								 resultSet.getInt("tr_job_head_id"),
								 resultSet.getInt("mst_problem_id"),
								 resultSet.getString("remark")
								 
								 );
			 }
		} 
		 
		 catch (SQLException e) 
		 
		 {
			 LOGGER.error("Cannot convert the ResultSet to the mapJobProblemModel bean",e);
			
			e.printStackTrace();
		}
		 
		 return mapJobProblemModel;
		 
		 
	}
	

}
