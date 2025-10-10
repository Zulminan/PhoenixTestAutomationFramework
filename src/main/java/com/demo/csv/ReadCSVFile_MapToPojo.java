package com.demo.csv;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPojo {

	public static void main(String[] args) throws IOException, CsvException {
		
		
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData"+File.separator+"LoginCreds.csv");
		
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		
		CSVReader csvReader = new CSVReader(inputReader);
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				                        .withType(UserBean.class)
				                        .withIgnoreEmptyLine(true)
				                        .build();
		
		 List<UserBean> userList  = csvToBean.parse();
		 
		 System.out.println(userList);
		
		 
		
	
	}

}
