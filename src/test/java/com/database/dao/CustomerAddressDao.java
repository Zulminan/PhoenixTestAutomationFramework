package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerAddressDao.class);
	
	
	private static final String CUSTOMER_ADDRESS_QUERY = """
			
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
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		CustomerAddressDBModel customerAddressDBModel = null;
		
		try 
		{
			LOGGER.info("Getting the connection from the Database Manager");
			
			conn =  DatabaseManager.getConnection();
			
			LOGGER.info("Executing the SQL Query {}",CUSTOMER_ADDRESS_QUERY);
			
			preparedStatement =  conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);
			
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
			LOGGER.error("Cannot convert the result set to the CustomerAddressDBModel bean",e);
			
			e.printStackTrace();
		}
		
		return customerAddressDBModel;
	}

}
