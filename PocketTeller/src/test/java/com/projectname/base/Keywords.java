package com.projectname.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import jxl.Sheet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.Select;

import com.pocketteller.userscripts.CustomizedFuntions;
import com.pocketteller.userscripts.IsDisabled;
import com.projectname.actions.Alertverify;
import com.projectname.actions.Authentication;
import com.projectname.actions.AutoIT;
import com.projectname.actions.CloseBrowser;
import com.projectname.actions.CompairText;
import com.projectname.actions.CompareTwoFiles;
import com.projectname.actions.GetAttributeALT;
import com.projectname.actions.GetAttributeValue;
import com.projectname.actions.GetWebPageLinks;
import com.projectname.actions.IsDisplayed;
import com.projectname.actions.IsEnabled;
import com.projectname.actions.IsSelected;
import com.projectname.actions.TestData;
import com.projectname.actions.VerifyPageTitle;
import com.projectname.actions.VerifyText;
import com.projectname.actions.WaitForText;
import com.projectname.actions.WebTable;
import com.projectname.actions.WindowHandling;
import com.projectname.utils.TestConstants;

public class Keywords extends FunctionLibrary {

	TestConstants tc;
	WindowHandling wh = new WindowHandling();
	TestData td = new TestData();
	

	
	/**
	 * Retrieving objects form control sheet
	 * 
	 * @param i
	 * @param colom
	 * @param tdshetnum
	 * @param csheet
	 * @param fileName
	 * @param keyword2
	 * @param stepDescription
	 * @throws Exception
	 */
	public String controlScript(String browser, int row, int colom,
			String tdshetnum, Sheet csheet, String testcaseid,
			String stepDescription, String keyword2, String fileName)
			throws Exception {
		ActionElements acElem = new ActionElements();
		CustomizedFuntions cf=new CustomizedFuntions();
		fb = new FunctionLibrary();
		iClass.initialize().controllerSheet = csheet;
		it.steps = new ArrayList<Variables>();
		Robot r = new Robot();
		String result = "Pass";
		int webtableCounter = 0;
		for (int k = 1; k < iClass.initialize().controllerSheet.getRows(); k++) {
			TestConstants tc = null;
			String testLinkID = iClass.initialize().controllerSheet.getCell(0, k).getContents();
			String desc = iClass.initialize().controllerSheet.getCell(1, k).getContents();
			String keyword = iClass.initialize().controllerSheet.getCell(2, k).getContents()
					.toUpperCase();
			String keywordtype = iClass.initialize().controllerSheet.getCell(3, k).getContents();
			String objectProp = iClass.initialize().controllerSheet.getCell(4, k).getContents();
			String object = iClass.initialize().OR.getProperty(objectProp);
			String data = null;
			Object testdata = null;
			String webObject = null;
			try {
				switch (keyword) {
				case "ACCEPTALERT":
					iClass.initialize().log.info("Accepting Alert");
					try {
						driver.switchTo().alert().accept();
					} catch (Exception e) {

					}
					result = "Pass";
					break;
				case "SEARCHGPR":
					iClass.initialize().log.info("Search in GPRInfo");
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					 boolean res=cf.SearchGPR(driver, data);
					if (res) {
						result = "Pass";
					}else{
						result="Fail";
					}
					colom++;
					break;
				case "SEARCHTRANS":
					iClass.initialize().log.info("Search in Transactions");
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					 boolean res1=cf.SearchTransaction(driver, data);
					if (res1) {
						result = "Pass";
					}else{
						result="Fail";
					}
					colom++;
					break;
				case "UPLOADCOUNT":
					iClass.initialize().log.info("Verifying the Number of Users that are Uploading");
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					 boolean res2=cf.UploadCount(driver, data);
					if (res2) {
						result = "Pass";
					}else{
						result="Fail";
					}
					colom++;
					break;
				case "SELECTPROGRAMS":
					iClass.initialize().log.info("Selecting Multiple Programs while Business Creation");
					 boolean Status2=cf.SelectPrograms(driver);
					if (Status2) {
						result = "Pass";
						iClass.initialize().log.info("Multiple Programs are Selected");
					}else{
						result="Fail";
					}
					break;	
					
				case "VERIFYBULKUPLOAD":
					iClass.initialize().log.info("Verifying Bulk Upload Status");
					 boolean Status=cf.VerifyBulkUpload(driver);
					if (Status) {
						result = "Pass";
					}else{
						result="Fail";
					}
					colom++;
					break;	
				case "VERIFYBULKFUND":
					iClass.initialize().log.info("Verifying Bulk Funding Status");
					 boolean Status1=cf.VerifyBulkFund(driver);
					if (Status1) {
						result = "Pass";
					}else{
						result="Fail";
					}
					colom++;
					break;
				case "VERIFYBULKFUNDAMOUNT":
					iClass.initialize().log.info("Verifying Bulk Fund Amount in Modal & Funds Uploaded");
					 boolean FundingAmount=cf.VerifyBulkFundAmount(driver);
					if (FundingAmount) {
						result = "Pass";
					}else{
						result="Fail";
					}
					colom++;
					break;	
				case "PIIDISABLED":
					iClass.initialize().log.info("Verifying the Display of PII Tab view in user view as Disabled");
					 boolean StatusR=cf.PIIDISABLED(driver);
					if (StatusR) {
						result = "Pass";
					iClass.initialize().log.info("PII Tab in User View is Disabled");
					}else{
						result="Fail";
					}
					break;
				case "EDITDISABLED":
					iClass.initialize().log.info("Verifying the Display of Edit in user List as Disabled");
					 boolean StatusX=cf.EDITDISABLED(driver);
					if (StatusX) {
						result = "Pass";
					iClass.initialize().log.info("Edit in User List is Disabled");
					}else{
						result="Fail";
					}
					break;	
				case "UPLOADFILES":
					iClass.initialize().log.info("Executing Auto IT Script");
					AutoIT upFile = new AutoIT();
					if (browser.equalsIgnoreCase("mf")) {
						upFile.autoIT(object+"_firefox.exe");
					}else if (browser.equalsIgnoreCase("gc")) {
						System.out.println("UPloadfile name====="+object+"_chrome.exe");
						upFile.autoIT(object+"_chrome.exe");
					}else if (browser.equalsIgnoreCase("ie")) {
						upFile.autoIT(object+"_ie.exe");
					}else if (browser.equalsIgnoreCase("safari")) {
						upFile.autoIT(object+"_safari.exe");
					}
					break;
				case "USERCARD":
					iClass.initialize().log.info("Create and Activate Card, Load Money and Show GPR for BE User");
					CustomizedFuntions cf1=new CustomizedFuntions(); 
					boolean resUser=cf1.UserCard(driver);
					if (resUser) {
						result = "Pass";
					}else{
						result="Fail";
					}
					
					colom++;
					break;	
				case "CHECKBOX":
				case "RADIOBUTTON":
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					iClass.initialize().log.info("Clicking on " + data
							+ " Radiobutton");
					String buttonvalue = iClass.initialize().OR.getProperty(data);
					tc = acElem.actionElement(keyword, keywordtype, buttonvalue, data);
					tc.result = result;
					colom++;
					break;
				case "COMPARETWOFILES":
					CompareTwoFiles ctf = new CompareTwoFiles();
					iClass.initialize().log.info("Compareing two files-----");
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					result = ctf.compareTwoFiles(keyword, keywordtype, object,
							data);
					colom++;
					break;
				case "CLICK":
					iClass.initialize().log.info("Clicking on Button " + object);
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					result = "Pass";
					break;
				case "CLICKENTER":
					iClass.initialize().log.info("Clicking on Enter Key");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "CLICKBACKSPC":
					iClass.initialize().log.info("Clicking on Back Space Key");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;	
				case "CLICKESC":
					iClass.initialize().log.info("Clicking on Escape Button");
					Thread.sleep(3000);
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "CLICKTAB":
					iClass.initialize().log.info("Clicking on TAB Button");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "CLOSEBROWSER":
					CloseBrowser cb = new CloseBrowser();
					iClass.initialize().log.info("Closing Browser");
					cb.closeBrowser(driver);
					result = "Pass";
					break;
				case "CLOSEWINDOW":
					iClass.initialize().log.info("Closing Child Window");
					wh.closeWindow(driver);
					result = "Pass";
					break;
				case "COMPARETEXT":
					iClass.initialize().log.info("Compairing Text");
					CompairText ct = new CompairText();
					result = ct.compairText(keyword, keywordtype, object,
							objectProp);
					break;
				case "DESC":
					result = "desc";
					break;
				case "DEFAULTCONTENT":
					iClass.initialize().log.info("Switch to default content");
					driver.switchTo().defaultContent();
					result = "Pass";
					break;
				case "GETALLLINKS":
					iClass.initialize().log.info("Getting All Links from Webpage");
					GetWebPageLinks gwpl = new GetWebPageLinks();
					gwpl.getLinks(driver);
					result = "Pass";
					break;
				case "GETATTRIBUTEVALUE":
					GetAttributeValue gavalue = new GetAttributeValue();
					iClass.initialize().log
							.info("Getting the value form WebPage-----");
					result = gavalue.getAttributeValue(keyword, keywordtype,
							object, objectProp);
					break;
				case "GETATTRIBUTEALT":
					GetAttributeALT galt = new GetAttributeALT();
					iClass.initialize().log
							.info("Getting the value form WebPage-----");
					result = galt.getAttributeAlt(keyword, keywordtype, object,
							objectProp);
					break;
				case "GETELEMENTTEXT":
					iClass.initialize().log.info("Getting Text From Webpage");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "GETPAGETITLE":
					iClass.initialize().log.info("Getting Page Title");
					driver.getTitle();
					result = "Pass";
					break;
				case "GOBACK":
					iClass.initialize().log.info("Clicking on Back Button");
					driver.navigate().back();
					result = "Pass";
					break;
				case "GOFORWARD":
					driver.navigate().forward();
					result = "Pass";
					break;
				case "INPUT":
					iClass.initialize().log.info("Entering Data into " + object);
					data = null;
					System.out.println("testdata===" + colom + row + tdshetnum);
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					if (!tc.welement.getAttribute("value").isEmpty()) {
						 tc.welement.sendKeys(data);
					} else {
						 tc.welement.sendKeys(data);
					}
					result = "Pass";
					colom++;
					break;
				case "CLEAR":
					iClass.initialize().log.info("Clearing Text");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.welement.clear();
					result = "Pass";
					break;
				case "ISDISPLAYED":
					IsDisplayed idis = new IsDisplayed();
					if (idis.isDisplayed(keyword, keywordtype, object,
							objectProp)) {
						result = "Pass";
						iClass.initialize().log.info("Element is Displayed");
					} else {
						result = "Fail";
					}
					break;
				case "ISNOTDISPLAYED":
					if (cf.isNOTDisplayed(keyword, keywordtype, object, objectProp)) {
						result = "Pass";
						iClass.initialize().log.info("Element NOT displayed");
					} else {
						result = "Fail";
					}
					break;	
				case "ISSELECTED":
					IsSelected is = new IsSelected();
					if (is.isSelected(keyword, keywordtype, object, objectProp)) {
						result = "Pass";
						iClass.initialize().log.info("Element is Selected");
					} else {
						result = "Fail";
					}
					break;
				case "ISENABLED":
					IsEnabled ie = new IsEnabled();
					if (ie.isEnabled(keyword, keywordtype, object, objectProp)) {
						result = "Pass";
						iClass.initialize().log.info("Element is Enabled");
					} else {
						result = "Fail";
					}
					break;
				case "VERIFYTOOLTIP":
				iClass.initialize().log.info("Verifying the Tool Tip Text ");    
				if (cf.toolTip(driver,keyword, keywordtype, object, objectProp)) {
					result = "Pass";
				} else {
					result = "Fail";
				}
				break;
				case "ISDISABLED":
					IsDisabled id = new IsDisabled();
					if (id.isDisabled(keyword, keywordtype, object, objectProp)) {
						result = "Pass";
						iClass.initialize().log.info("Element is Disabled");
					} else {
						result = "Fail";
					}
					break;
				case "CLICKANDHOLD":
					iClass.initialize().log.info("Click and Hold " + object);
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;	
				case "KEYDOWN":
					iClass.initialize().log.info("Clicking on Down Button");
					Thread.sleep(3000);
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "MOUSEOVER":
					iClass.initialize().log.info("Mouse Over to " + object);
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "OPENNEWTAB":
					iClass.initialize().log.info("Open New Tab");
					try {
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_N);
					} catch (Exception e) {
						driver.findElement(By.tagName("body")).sendKeys(
								Keys.CONTROL + "n");
					}
					result = "Pass";
					break;
				case "OPENURL":
					iClass.initialize().log.info("Navigating to ---" + object);
					driver.navigate().to(object);
					result = "Pass";
					break;
				case "PASTE":
					iClass.initialize().log.info("Paste Text into Textbox");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "REFRESH":
					iClass.initialize().log.info("Refreshing Page........");
					driver.navigate().refresh();
					result = "Pass";
					break;
				case "SCROLL":
					iClass.initialize().log.info("Scroll down");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "SELECT":
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					iClass.initialize().log.info("Selecting DropDown Value " + data);
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					new Select(tc.welement).selectByVisibleText(data);
					tc.result = result;
					colom++;
					break;
				case "WEBSELECT":
					testdata = td.testData(colom, row, tdshetnum);
					data = (String) testdata;
					webObject = object + webtableCounter;
					System.out.println("Webtable counter value-====>>"
							+ webObject);
					tc = acElem.actionElement(keyword, keywordtype, webObject, data);
					tc.result = result;
					colom++;
					break;
				case "SELECTFRAME":
					iClass.initialize().log.info("Switch to frame------");
					tc = acElem.actionElement(keyword, keywordtype, object, data);
					tc.result = result;
					break;
				case "SWITCHTONEWWINDOW":
					iClass.initialize().log.info("Switch to New Window");
					wh.windowhandle(driver);
					result = "Pass";
					break;
				case "SWITCHTOPARENTWINDOW":
					iClass.initialize().log.info("Switching to Parent Window");
					wh.Mainwindow(driver);
					result = "Pass";
					break;
				case "VERIFYALERT":
					Alertverify av = new Alertverify();
					iClass.initialize().log.info("Verifying Alert-----");
					result = av.alertverify(objectProp, driver);
					break;
				case "VERIFYTEXT":
					iClass.initialize().log.info("Verifying Text");
					VerifyText vt = new VerifyText();
					if (vt.verifyText(keyword, keywordtype, object, objectProp)) {
						result = "Pass";
					} else {
						result = "Fail";
					}
					break;
				case "PARTIALTEXT":
				     iClass.initialize().log.info("Verifying Partial Text");
				     CustomizedFuntions cfun = new CustomizedFuntions();
				     if (cfun.PartialText(keyword, keywordtype, object, objectProp)) {
				      result = "Pass";
				     } else {
				      result = "Fail";
				     }
				     break;
				case "VERIFYPAGETITLE":
					VerifyPageTitle vpt = new VerifyPageTitle();
					iClass.initialize().log.info("Verifying Page Title");
					result = vpt.verifyPageTitle(driver, keyword, keywordtype,
							object, objectProp);
					break;
				case "WAIT":
					iClass.initialize().log.info("Loading Page");
					Thread.sleep(6000);
					result = "Pass";
					break;
				case "WAITFORTEXT":
					iClass.initialize().log.info("Loading Page");
					WaitForText wft = new WaitForText();
					wft.waitForText();
					break;
				case "WEBTABLE":
					WebTable wtable = new WebTable();
					webtableCounter = wtable.webTable(object, driver);
					break;
				case "AUTOIT":
					iClass.initialize().log.info("Executing Auto IT Script");
					AutoIT ait = new AutoIT();
					ait.autoIT(objectProp);
					break;
				case "AUTHENTICATION":
					iClass.initialize().log.info("Executing VBScript");
					Authentication auth = new Authentication();
					auth.authentication();
					break;
				default:
					break;
				}
				reportSteps(result, desc, keyword, fileName + testLinkID
						+ ".png", object, testcaseid, testLinkID);

			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
			} catch (Exception e) {
				result = "Fail";
				iClass.initialize().log.info("", e);
				it.failcount++;
				report(result, desc, keyword, fileName, object, testcaseid);
				e.printStackTrace();
				it.result = result;
				it.desc = desc;
				it.keyword = keyword;
				it.fileName = fileName;
				it.object = object;
				it.testcaseid = testcaseid;
				it.steps.add(it);
				break;
			}
		}
		reportEmailMain(browser, result, fileName, testcaseid, it.failcount);
		for (int i = 0; i < it.steps.size(); i++) {
			String re = it.steps.get(i).result;
			String ds = it.steps.get(i).desc;
			String testLinkID = it.steps.get(i).testLinkID;
			switch (re) {
			case "Fail":
				EmailReportUtil.addTestCaseSteps(ds, re, testLinkID);
				break;
			case "Pass":
			case "res":
				EmailReportUtil.addTestCaseSteps(ds, re, testLinkID);
				break;
			default:
				break;
			}
		}
		return result;
	}

}
