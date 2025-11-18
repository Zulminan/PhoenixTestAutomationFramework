package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemoHikariCP {

	public static void main(String[] args) throws SQLException {
		
		
		DatabaseManager.getConnection();
		
		

Connection connection =  DatabaseManager.getConnection();
		
		Statement statement =  connection.createStatement();
		
		ResultSet resultSet =  statement.executeQuery("select first_name,last_name,mobile_number from tr_customer");
		
		while(resultSet.next())
		{
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String mobileNumber = resultSet.getString("mobile_number");
			
			System.out.println(firstName+" | "+lastName+" | "+mobileNumber);
		}
		
		

	}

}
 