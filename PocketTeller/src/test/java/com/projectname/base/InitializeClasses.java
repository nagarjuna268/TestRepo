package com.projectname.base;

import java.io.FileInputStream;
import java.util.Properties;

import jxl.Workbook;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.projectname.utils.TestConstants;

public class InitializeClasses {
	
	Variables it = new Variables();
	/***********************************************************************************************************
	 * Description : Initialize all the Paths for Project Resources Files
	 * Created by : Santhosh R Created Date : 10-Oct-2013 Updated by : Santhosh
	 * R LastUpdated :
	 ***********************************************************************************************************/
	public Variables initialize() throws Exception {
		it.currentpath = new java.io.File(".").getCanonicalPath();
		it.path = it.currentpath.replace("\\", "\\\\");

		// Reading test data file
		it.fi = new FileInputStream(it.path + TestConstants.TEST_DATA_DIR_PATH);
		it.testDataWorkBook = Workbook.getWorkbook(it.fi);

		it.fi = new FileInputStream(it.path + TestConstants.CONTROLLER_DIR_PATH);
		it.controllerwb = Workbook.getWorkbook(it.fi);

		// Reading object properties
		it.OR = new Properties();
		it.fi = new FileInputStream(it.path + TestConstants.OBJECT_REPOSTRY_DIR_PATH);
		it.OR.load(it.fi);
		// Reading objects from Verification file
		it.V = new Properties();
		it.fi = new FileInputStream(it.path + TestConstants.VALIDATION_DIR_PATH);
		it.V.load(it.fi);

		it.EMAIL = new Properties();
		it.fi = new FileInputStream(it.path + TestConstants.EMAIL_DIR_PATH);
		it.EMAIL.load(it.fi);

		it.log = Logger.getLogger(FunctionLibrary.class.getName());
		PropertyConfigurator.configure("log4j.properties");
		return it;

	}
}
