package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	//Executing the query for tr_customer table! which will get the details of the customer!
	
	private static final String CUSTOMER_DETAIL_QUERY = """
			
			select * from tr_customer where id = 112921
			
			""";
	
	public static CustomerDBModel getCustomerInfo()
	{
		Connection conn =  DatabaseManager.getConnection();
		
		Statement statement;
		
		ResultSet resultSet;
		
		CustomerDBModel customerDBModel=null;
		
		try 
		{
			statement =  conn.createStatement();
			
			resultSet = statement.executeQuery(CUSTOMER_DETAIL_QUERY);
			
			while(resultSet.next())
			{
				customerDBModel = new CustomerDBModel(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("mobile_number"), resultSet.getString("mobile_number_alt"),resultSet.getString("email_id"), resultSet.getString("email_id_alt"));
				
			}
		} 
		
		catch (SQLException e) 
		{
		
			e.printStackTrace();
		}
		
		return customerDBModel;
	}
	

}
