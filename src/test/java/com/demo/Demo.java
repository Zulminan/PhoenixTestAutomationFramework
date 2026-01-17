package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {
	
	private static Logger logger = LogManager.getLogger(Demo.class);

	public static void main(String[] args) {
		
		logger.info("Inside the main() method!!");
	}
	
}
