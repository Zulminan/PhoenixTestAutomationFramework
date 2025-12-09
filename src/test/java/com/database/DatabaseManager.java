package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {
	
	private static final String DB_URL= EnvUtil.getValue("DB_URL");
	private static final String DB_USER_NAME= EnvUtil.getValue("DB_USER_NAME");
	private static final String DB_PASSWORD= EnvUtil.getValue("DB_PASSWORD");
	
	private static final int MAX_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
	
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	
	private static final int IDLE_TIMEOUT_IN_SECS = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SECS"));
	
	private static final int MAX_LIFE_TIME_IN_MINS = Integer.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
	
	private static final String HIKARI_CP_POOL_NAME = ConfigManager.getProperty("HIKARI_CP_POOL_NAME");
	
	
	
	
	private static HikariConfig hikariConfig;
	
	
	private volatile static HikariDataSource hikariDataSource;
	
	private static Connection connection;
	
	private DatabaseManager()
	{
		
	}
	
	public synchronized static void initializePool()
	{
		if(hikariDataSource==null)
		{
			synchronized(DatabaseManager.class)
			{
				if(hikariDataSource==null)
				{
					hikariConfig = new HikariConfig();
					
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USER_NAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAX_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS*1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SECS*1000);
					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS*60*1000);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);
					
					hikariDataSource = new HikariDataSource(hikariConfig);
					
					
				}
			}
					
		}
		
			
	}
	
	public static Connection getConnection()
	{
		if(hikariDataSource==null)
		{
			DatabaseManager.initializePool();
		}
		
		else if(hikariDataSource.isClosed())
		{
			System.err.println("HIKARI DATA SOURCE IS CLOSED...Returning null connection.");
            
			return null;
		}
		
		try 
		{
			connection = hikariDataSource.getConnection();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return connection;
	}

}
