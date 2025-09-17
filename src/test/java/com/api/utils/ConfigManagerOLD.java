package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	
	private ConfigManagerOLD() 
	{
		
	}
	
	private static Properties prop = new Properties();
	
	
	static
	{
        File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		
		FileReader fileReader = null;
		
		try 
		{
			fileReader = new FileReader(configFile);
			
			prop.load(fileReader);
		} 
		
		catch (IOException e) 
		
		{
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static String getProperty(String key)
	{
		
		
		
		
		return prop.getProperty(key);
	}

}
