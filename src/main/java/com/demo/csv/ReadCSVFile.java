package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
		
		
		/* 
		 * File csvFile = new File(System.getProperty("user.dir")+File.separator+
		   "src"+File.separator+"main"+File.separator+"resources"+File.separator+"testData"+File.separator+File.separator+"LoginCreds.csv");
		   FileReader fileReader = new FileReader(csvFile);
		 * 
		 * */
		
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData"+File.separator+"LoginCreds.csv");
		
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		
		
		
		CSVReader csvReader = new CSVReader(inputReader);
		
		List<String[]> dataList = csvReader.readAll();
		
		for(String[] dataArray : dataList)
		{
			for(String data : dataArray)
			{
				System.out.print(data+",");
			}
			
			System.out.println();
		}
		
		
		String[] line = null;
		
		csvReader.readNext();
		
		while((line=csvReader.readNext()) != null)
		{
			
			
			System.out.println(line[0]+" "+line[1]);
		}

	}

}
