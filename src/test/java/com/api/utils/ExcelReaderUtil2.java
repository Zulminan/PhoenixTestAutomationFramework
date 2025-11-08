package com.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {
	
	private ExcelReaderUtil2()
	{
		
	}

	public static <T>Iterator<T> loadTestData(String xlsxFileName,String sheetName, Class<T> clazz) throws IOException 
	{
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData"+File.separator+"PhoenixTestData.xlsx");
		
		XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
		
		
		XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");
		
		List<T> list = Poiji.fromExcel(mySheet, clazz);
		
		
		return list.iterator();
		
		
	  
	   
	   
	  
		
		
	}

}
