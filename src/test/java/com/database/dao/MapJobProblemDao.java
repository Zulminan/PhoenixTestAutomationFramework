package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemModel;

public class MapJobProblemDao {
	
	
private static final String PROBLEM_QUERY = """

			select * from map_job_problem where tr_job_head_id = ?;			
			
			""";
	
	
	
	private MapJobProblemDao()
	{
		
	}
	
	public static MapJobProblemModel getProblemDetails(int tr_job_head_id)
	{
		Connection conn =  DatabaseManager.getConnection();
		
		 PreparedStatement statement;
		 
		 MapJobProblemModel mapJobProblemModel = null;
		 
		 ResultSet resultSet;
		
		 try 
		 {
			statement = conn.prepareStatement(PROBLEM_QUERY);
			
			statement .setInt(1, tr_job_head_id);
			 
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
			
			e.printStackTrace();
		}
		 
		 return mapJobProblemModel;
		 
		 
	}
	

}
