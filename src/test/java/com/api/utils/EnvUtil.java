package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	
	private static Dotenv dotEnv;
	
	static
	{
		dotEnv = Dotenv.load();
	}
	
	private EnvUtil()
	{
		
	}
	
	public static String getValue(String varName)
	{
		return dotEnv.get(varName);
	}

}
