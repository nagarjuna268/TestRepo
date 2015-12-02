package com.projectname.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.projectname.scripts.DriverScriptTest;
import com.projectname.utils.TestConstants;
import com.projectname.utils.TestUtil;

public class FunctionLibrary extends DriverScriptTest{

	
	public String className;

	Variables it = new Variables();


	/***********************************************************************************************************
	 * Description : Creates Test Suite for Test Report Created by : Santhosh R
	 * Created Date : 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 ***********************************************************************************************************/

	public void startTesting() throws Exception {
		String currentpath = new java.io.File(".").getCanonicalPath();
		String path = currentpath.replace("\\", "\\\\");
		ReportUtil
				.startTesting(
						path
								+ com.projectname.utils.TestConstants.TESTREPORT_RESULT_DIR_PATH,
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
						iClass.initialize().EMAIL.getProperty("Env"),
						iClass.initialize().EMAIL.getProperty("Version"));
	}

	/***********************************************************************************************************
	 * Description : Creates Test Suite for Email Report Created by : Santhosh R
	 * Created Date : 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 ***********************************************************************************************************/
	public void emailStartTesting() throws Exception {

		String currentpath = new java.io.File(".").getCanonicalPath();
		String path = currentpath.replace("\\", "\\\\");
		EmailReportUtil
				.startTesting(
						path
								+ com.projectname.utils.TestConstants.EMAIL_TESTREPORT_RESULT_DIR_PATH,
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
						iClass.initialize().EMAIL.getProperty("Env"),
						iClass.initialize().EMAIL.getProperty("Version"));
	}

	/***********************************************************************************************************
	 * Description : It sets Class Name to Results file Created by : Santhosh R
	 * Created Date : 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 ***********************************************************************************************************/
	public String setTestClassName(String className) {
		this.className = className;
		return className;
	}

	/***********************************************************************************************************
	 * Description : It Returns Class Name Created by : Santhosh R Created Date
	 * : 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 ***********************************************************************************************************/
	public String getTestClassName() {
		return className;
	}

	/***********************************************************************************************************
	 * Description : It will Generate Main Report Created by : Santhosh R
	 * Created Date : 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 ***********************************************************************************************************/
	public void mainReport(String browser, String keyword, String result,
			String fileName) {
		it.startTime = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
		if (result.equalsIgnoreCase("Fail")) {
			it.testStatus = result;
			// TestUtil.takeScreenShot(path+com.projectname.utils.TestConstants.TESTSUITE_RESULT_DIR_PATH+fileName);
			ReportUtil.addTestCase(browser, keyword, it.startTime,
					TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), it.testStatus);
		} else if (result.equalsIgnoreCase("Pass")
				|| result.equalsIgnoreCase("res")) {
			it.testStatus = "Pass";
			// TestUtil.takeScreenShot(path+com.projectname.utils.TestConstants.TESTSUITE_RESULT_DIR_PATH+fileName);
			ReportUtil.addTestCase(browser, keyword, it.startTime,
					TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), it.testStatus);
		}
	}

	/***********************************************************************************************************
	 * Description : It Creates Failures Report Step by Step Created by :
	 * Santhosh R Created Date : 10-Oct-2013 Updated by : Santhosh R LastUpdated
	 * :
	 ***********************************************************************************************************/

	public void report(String result, String stepDescription, String keyword,
			String fileName, String object, String testcaseid) throws Exception {
		it.startTime = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
		switch (result) {
		case "Fail":
			iClass.initialize().testStatus = result;
			TestUtil.takeScreenShot(iClass.initialize().path
					+ com.projectname.utils.TestConstants.TESTSUITE_RESULT_DIR_PATH
					+ fileName);
			ReportUtil.addKeyword(stepDescription, keyword, result, fileName);
			break;
		case "Pass":
			iClass.initialize().testStatus = result;
			TestUtil.takeScreenShot(iClass.initialize().path
					+ com.projectname.utils.TestConstants.TESTSUITE_RESULT_DIR_PATH
					+ fileName);
			ReportUtil.addKeyword(stepDescription, keyword, result, fileName);
			break;
		case "desc":
			iClass.initialize().testStatus = result;
			ReportUtil.addKeyword(stepDescription, keyword, result, fileName);
			break;
		default:
			break;
		}
	}

	/***********************************************************************************************************
	 * Description : It Creates Pass Report Step by Step Created by : Santhosh R
	 * Created Date : 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 ***********************************************************************************************************/
	public void reportSteps(String result, String desc, String keyword,
			String fileName, String object, String testcaseid, String testLinkID)
			throws Exception {
		switch (result) {
		case "Fail":
			report(result, desc, keyword, fileName, object, testcaseid);
			it.result = result;
			it.desc = desc;
			it.keyword = keyword;
			it.fileName = fileName;
			it.object = object;
			it.testcaseid = testcaseid;
			it.testLinkID = testLinkID;
			it.steps.add(it);
			it.rptFailCnt++;
			break;
		case "res":
		case "Pass":
			result = "Pass";
			report(result, desc, keyword, fileName, object, testcaseid);
			it.result = result;
			it.desc = desc;
			it.keyword = keyword;
			it.fileName = fileName;
			it.object = object;
			it.testcaseid = testcaseid;
			it.testLinkID = testLinkID;
			it.steps.add(it);
			break;
		case "desc":
			report(result, desc, keyword, fileName, object, testcaseid);
			break;
		default:
			break;
		}
	}

	/***********************************************************************************************************
	 * Description : It Creates Email Report Step by Step Created by : Santhosh
	 * R Created Date : 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 ***********************************************************************************************************/
	public void reportEmailMain(String browser, String result, String fileName,
			String keyword, int failcount) {
		System.out.println("Fail count--" + failcount);
		System.out.println("Main email report==========>>" + browser);
		if (failcount >= 1 || it.rptFailCnt >= 1) {
			it.testStatus = "Fail";
			EmailReportUtil.addTestCase(browser, keyword, it.startTime,
					TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), it.testStatus);
		} else {
			it.testStatus = "Pass";
			EmailReportUtil.addTestCase(browser, keyword, it.startTime,
					TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), it.testStatus);
		}
	}

	/***********************************************************************************************************
	 * Description : Generate Pie-Chart Created by : Santhosh R Created Date :
	 * 10-Oct-2013 Updated by : Santhosh R LastUpdated :
	 * 
	 * @throws Exception
	 ***********************************************************************************************************/
	public void generatePieChart() throws Exception {

		WebDriver driver = new FirefoxDriver();
		driver.get(it.path + TestConstants.PIECHART_HTML_PATH);
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		FileUtils.copyFile(scrFile, new File(it.path
				+ TestConstants.PIECHART_SCREENSHOT_PATH));
		driver.quit();

	}
}