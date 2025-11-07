package com.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData"+File.separator+"PhoenixTestData.xlsx");
		
		
		XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
		
		XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");
		
	   int lastRowIndex = mySheet.getLastRowNum();
	   
	   XSSFRow rowHeader = mySheet.getRow(0);
	   
	   int lastIndexOfCol = rowHeader.getLastCellNum()-1;
	   
	   XSSFRow myRow;
	   XSSFCell myCell;
	   
	   for(int rowIndex=0;rowIndex<=lastRowIndex;rowIndex++)
	   {
		   for(int colIndex=0;colIndex<=lastIndexOfCol;colIndex++)
		   {
			   myRow = mySheet.getRow(rowIndex);
			   myCell = myRow.getCell(colIndex);
			   
			   System.out.print(myCell+" ");
			   
		   }
		   
		   System.out.println();
	   }
	   
	  
		
		
	}

}
