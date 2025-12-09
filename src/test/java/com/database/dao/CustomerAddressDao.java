package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {
	
	
	private static final String CUSTOMER_ADDRESS_DETAILS_QUERY = """
			
			select state,
                   country,
                   pincode,
                   area,
                   landmark,
                   street_name,
                   apartment_name,
                   flat_number 
 
                 from tr_customer_address where id = ?;
			
			""";
	
	private CustomerAddressDao()
	{
		
	}
		
	
	public static CustomerAddressDBModel getCustomerAddress(int customerAddressId)
	{
		Connection conn =  DatabaseManager.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		CustomerAddressDBModel customerAddressDBModel = null;
		
		try 
		{
			preparedStatement =  conn.prepareStatement(CUSTOMER_ADDRESS_DETAILS_QUERY);
			
			preparedStatement.setInt(1, customerAddressId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				customerAddressDBModel = new CustomerAddressDBModel(resultSet.getString("flat_number"), 
						resultSet.getString("apartment_name"), 
						resultSet.getString("street_name"), 
						resultSet.getString("landmark"), 
						resultSet.getString("area"), 
						resultSet.getString("pincode"), 
						resultSet.getString("country"), 
						resultSet.getString("state"));
			}
		} 
		
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return customerAddressDBModel;
	}

}
