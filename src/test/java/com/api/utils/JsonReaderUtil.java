package com.api.utils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	
	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz)
	
	{	
		
	InputStream is =Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	T[] classArray = null;
	
	List<T> list =null;
	
	try 
	{
		classArray = objectMapper.readValue(is, clazz);
		list  = Arrays.asList(classArray);
	} 
	
	catch (IOException e) 
	{
		
		e.printStackTrace();
	}
	
	
	
	
	return list.iterator();
	
		
	}
	
}
