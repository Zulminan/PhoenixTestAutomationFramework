package com.dataproviders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakeDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class);

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		
		LOGGER.info("Loading data from CSV file testData/LoginCreds.csv");
		
		return CSVReaderUtil.loadCSV("testData" + File.separator + "LoginCreds.csv", UserBean.class);
	}

	
	@DataProvider(name="CreateJobAPIDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		
		LOGGER.info("Loading data from CSV file testData/CreateJobData.csv");
		
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
		
		LOGGER.info("Generating the fake create job data with the faker count {},",fakerCountInt);
		
		Iterator<CreateJobPayload> payloadIterator = FakeDataGenerator.generateFakeCreateJobData(fakerCountInt);
		
		return payloadIterator;
	}
	
	
	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIJsonDataProvider() {
		
		LOGGER.info("Loading the data from JSON file testData/loginAPITestData.json");
		return JsonReaderUtil.loadJSON("testData" + File.separator + "loginAPITestData.json", UserBean[].class);
	}
	
	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> craeteJobAPIJsonDataProvider() {
		
		LOGGER.info("Loading the data from JSON file testData/CreateJobAPIData.json");
		
		return JsonReaderUtil.loadJSON("testData" + File.separator + "CreateJobAPIData.json", CreateJobPayload[].class);
	}
	
	
	
	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIExcelDataProvider() throws IOException {
		
		LOGGER.info("Loading the data from Excel file testData/PhoenixTestData.xlsx and sheet is LoginTestData");
		
		return ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx", "LoginTestData", UserBean.class);
	}
	
	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload
	> createJobAPIExcelDataProvider() throws IOException {
		
		LOGGER.info("Loading the data from Excel file testData/PhoenixTestData.xlsx and sheet is CreateJobTestData");
		
		Iterator<CreateJobBean> iterator =  ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx", "CreateJobTestData", CreateJobBean.class);
		List<CreateJobPayload> payLoadList = new ArrayList<CreateJobPayload>();
		
		while(iterator.hasNext())
		{
			CreateJobBean bean = iterator.next();
			
			CreateJobPayload tempPayload = CreateJobBeanMapper.mapper(bean);
			
			payLoadList.add(tempPayload);
			
			
		}
		
		return payLoadList.iterator();
			
	}
	
	

	

	    @DataProvider(name = "CreateJobAPIDBDataProvider", parallel = true)
		public static Iterator<CreateJobPayload> createJobAPIDBDataProvider() {
	    	
	    	LOGGER.info("Loading the data from Database for CreateJobPayload");
			
			List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPayloadData();
			
			List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
			
			
			for(CreateJobBean createJobBean :beanList)
			{
				CreateJobPayload payload = CreateJobBeanMapper.mapper(createJobBean);
				
				payloadList.add(payload);
			}
			
			
          return payloadList.iterator();
		}

	

}
