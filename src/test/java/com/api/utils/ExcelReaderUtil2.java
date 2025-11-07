package com.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtil2 {
	
	private ExcelReaderUtil2()
	{
		
	}

	public static Iterator<UserCredentials> loadTestData()
	{
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData"+File.separator+"PhoenixTestData.xlsx");
		
		XSSFWorkbook myWorkBook = null;
	
		try 
		{
			myWorkBook = new XSSFWorkbook(is);
		} 
		
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		
		XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");
		
		XSSFRow rowHeader = mySheet.getRow(0);
		
		
		//Locate the Column Index
		
		int userNameIndex = -1;
		
		int passwordIndex = -1;
		
		
		for(Cell cell : rowHeader)
		{
			if(cell.getStringCellValue().trim().equalsIgnoreCase("username"))
			{
				userNameIndex = cell.getColumnIndex();
			}
			
			if(cell.getStringCellValue().trim().equalsIgnoreCase("password"))
			{
				passwordIndex = cell.getColumnIndex();
			}
		}
		
		int lastRowIndex = mySheet.getLastRowNum();
		
		XSSFRow rowData;
		
		UserCredentials userCredentials = null;
		
		List<UserCredentials> userList = new ArrayList<UserCredentials>(); 
		
		for(int rowIndex=1;rowIndex<=lastRowIndex;rowIndex++)
		{
			 rowData = mySheet.getRow(rowIndex);
			 
			 userCredentials = new UserCredentials(rowData.getCell(userNameIndex).toString(),rowData.getCell(passwordIndex).toString());
			 
			 userList.add(userCredentials);
		}
		
		
		
	return userList.iterator();
		
		
		
	  
	   
	   
	  
		
		
	}

}
