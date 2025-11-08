package com.dataproviders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil2;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData" + File.separator + "LoginCreds.csv", UserBean.class);
	}

	
	@DataProvider(name="CreateJobAPIDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData"+File.separator+"CreateJobData.csv", CreateJobBean.class);
		
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		CreateJobBean tempBean;
		
		CreateJobPayload tempPayload;
		
		while(createJobBeanIterator.hasNext())		
		{
			 tempBean = createJobBeanIterator.next();
			 tempPayload = CreateJobBeanMapper.mapper(tempBean);
			 payloadList.add(tempPayload);
		}
		
		return payloadList.iterator();
		
	}
	
	@DataProvider(name="createJobAPIFakerDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
		
		String fakerCount = System.getProperty("fakerCount","5");
		
		int fakerCountInt = Integer.parseInt(fakerCount);
		
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		
		return payloadIterator;
	}
	
	
	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserCredentials> loginAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData" + File.separator + "loginAPITestData.json", UserCredentials[].class);
	}
	
	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> craeteJobAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData" + File.separator + "CreateJobAPIData.json", CreateJobPayload[].class);
	}
	
	
	
	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIExcelDataProvider() throws IOException {
		return ExcelReaderUtil2.loadTestData("testData/PhoenixTestData.xlsx", "LoginTestData", UserBean.class);
	}
	
	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload
	> createJobAPIExcelDataProvider() throws IOException {
		Iterator<CreateJobBean> iterator =  ExcelReaderUtil2.loadTestData("testData/PhoenixTestData.xlsx", "CreateJobTestData", CreateJobBean.class);
		List<CreateJobPayload> payLoadList = new ArrayList<CreateJobPayload>();
		
		while(iterator.hasNext())
		{
			CreateJobBean bean = iterator.next();
			
			CreateJobPayload tempPayload = CreateJobBeanMapper.mapper(bean);
			
			payLoadList.add(tempPayload);
			
			
		}
		
		return payLoadList.iterator();
			
	}
}
