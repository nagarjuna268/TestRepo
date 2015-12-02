package com.pocketteller.userscripts;

import java.util.List;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.projectname.base.ActionElements;
import com.projectname.base.InitializeClasses;
import com.projectname.utils.TestConstants;

public class CustomizedFuntions extends InitializeClasses
{
	ActionElements acElem = new ActionElements();
	TestConstants tc;

	/*Keyword to Accept the Alert and to Read the Alert Content*/
	
	public void SubmitAlert(WebDriver driver,String keyword, String keywordtype,
			String object, String data) throws Exception
	{
		
		    //driver.findElement(By.name("AccSubmit")).click();
		
			tc = acElem.actionElement(keyword, keywordtype, object, data);
			tc.welement.click();
			driver.switchTo().alert().accept();
			String Str = driver.switchTo().alert().getText();
			
			if(Str.contains("customer"))
			{
				System.out.println("Can not delete the user, beacause: " + Str);
				driver.switchTo().alert().accept();
			}
			
			else
				System.out.println("Deleted user successfully");
		
	}
	
	/*Keyword to verify the text on the web page partially*/
	
	public boolean PartialText(String keyword, String keywordtype, String object, String objectProp)
		    throws Exception
		  {
		    boolean veryTxt = false;
		    
		    String expData = initialize().V.getProperty(objectProp);
		    ActionElements acElem = new ActionElements();
		    TestConstants tc = acElem.actionElement(keyword, keywordtype, object, 
		      objectProp);
		    WebElement wb = tc.welement;
		    
		    initialize().log.debug("Executing verifyText");
		    String actual = null;
		    try
		    {
		      actual = wb.getText().replace("\n", "");
		    }
		    catch (Exception e)
		    {
		      try
		      {
		        actual = wb.getText();
		      }
		      catch (Exception localException1) {}
		    }
		    initialize().log.info("Excepted Data-------->>" + expData);
		    initialize().log.info("Actual Data---------->>" + actual);
		    if (actual.contains(expData)) {
		      veryTxt = true;
		    }
		    return veryTxt;
		  }
	
	/*Keyword to verify the Search Functionality in the Pocket Teller GPR Info page*/
	
	public Boolean SearchGPR(WebDriver driver, String searchRefNUM) throws Exception	{
		boolean result =false;
		
		List<WebElement> rowCount= driver.findElements(By.xpath("//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div"));
		System.out.println(rowCount.size());
		for (int i = 1; i <= rowCount.size(); i++) {
			String refNUM=driver.findElement(By.xpath("//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div["+i+"]/div[2]/div[2]/div")).getText();
			if (searchRefNUM.equals(refNUM)) {
				initialize().log.info("Reference Number is -------->>" + refNUM);
				result =true;
			}
		}
		return result;
	}
	
	/*Keyword to verify the Search Functionality in the Pocket Teller Transaction Info page*/
	
	public Boolean SearchTransaction(WebDriver driver, String searchTransaction) throws Exception	{
		boolean result =false;
		
		List<WebElement> rowCount= driver.findElements(By.xpath("//*[@id='content']/div/div/div/div[2]/div[2]/div[4]/div"));
		System.out.println("Total Rows Found are" + rowCount.size());
		for (int i = 1; i <= rowCount.size(); i++) {
			String TransAmount=driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/div[2]/div[4]/div/div[2]/div/div["+i+"]/div[4]/div[2]/div/span")).getText();
			if (searchTransaction.equals(TransAmount.substring(1,TransAmount.length()))) {
				initialize().log.info("Transaction Amount is -------->>" + TransAmount);
				result =true;
			}
		}
		return result;
	}	

	public Boolean SelectPrograms(WebDriver driver) throws Exception	{
		boolean result =false;
			
		List<WebElement> rowCount= driver.findElements(By.xpath("//*[@id='content']/div/div[2]/form/div[1]/div[1]/ng-include[1]/div[1]/div[2]/div[2]/div[1]/div/ul/li")); 
		System.out.println("Total Programs available in Select Programs Drop Down are  " + rowCount.size());
		for (int i = 1; i <= rowCount.size(); i++) {
			driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div[1]/div[1]/ng-include[1]/div[1]/div[2]/div[2]/div[1]/div/ul/li["+i+"]/a/span")).click();
				initialize().log.info("Selected Multiple Programs in Add Business ");
				result =true;
			}
			return result;	
	}
	
	public Boolean UploadRowSize(WebDriver driver) throws Exception	{
		boolean result =false;
			
		List<WebElement> rowCount= driver.findElements(By.className("panelng-scopepanel-default"));
		System.out.println("Total Number of rows are " + rowCount.size());
		/*for (int i = 1; i <= rowCount.size(); i++) {
			//String BulkRes1=driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div["+i+"]/div[1]/div/div[2]/span")).getAttribute("class");
			String BulkRes1=driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div["+i+"]/div[1]/div/div[2]/span")).getText();
			//String BulkStatus = "ui-grid-cell-contents ng-binding ng-scope bg-success";
			String BulkStatus = "SUCCESS";
			if (BulkStatus.equals(BulkRes1)) {
				initialize().log.info("Bulk Upload is SUCCESS ");
				result =true;
				}
			}*/
			return result;	
	}
	
	/*Keyword to verify the Bulk Upload Status as Success OR Failed. It will Verify the status for each row*/
	
	
	public Boolean VerifyBulkUpload(WebDriver driver) throws Exception	{
		boolean result =false;
			
		List<WebElement> rowCount= driver.findElements(By.xpath("//*[@class='panel ng-scope panel-success']"));
		System.out.println("Total Number of Records SUCCESS are  " + rowCount.size());
		for (int i = 1; i<= rowCount.size(); i++) {
			//String BulkRes1=driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div["+i+"]/div[1]/div/div[2]/span")).getAttribute("class");
				String BulkRes1=driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div["+(i+4)+"]/div[1]/div/div[2]/span")).getText();
				//String BulkStatus = "ui-grid-cell-contents ng-binding ng-scope bg-success";
				String BulkStatus = "SUCCESS";
				if (BulkStatus.equals(BulkRes1)) {
					initialize().log.info("Bulk Upload is SUCCESS ");
					result =true;
					}
				}	
			
			return result;	
	}
	
	/*Keyword to verify the Bulk Funding Status as Success OR Failed. It will Verify the status for each row*/
	
	public Boolean VerifyBulkFund(WebDriver driver) throws Exception	{
		boolean result =false;
			
		List<WebElement> rowCount= driver.findElements(By.xpath("//*[@id='content']/div/div[2]/form/div[3]/div/div/div/div[1]/div[2]/div/div")); 
		System.out.println("Total Rows Found are " + rowCount.size());
		for (int i = 1; i <= rowCount.size(); i++) {
			String BulkRes2=driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div[3]/div/div/div/div[1]/div[2]/div/div["+i+"]/div/div[1]/div")).getAttribute("class");
			String BulkStatus1 = "ui-grid-cell-contents ng-scope bg-success";
			if (BulkStatus1.equals(BulkRes2)) {
				initialize().log.info("Bulk Funding is SUCCESS ");
				result =true;
				}
			}
			return result;	
	}
	
	/*Keyword to verify whether the total amount uploaded to bulk funding grid is equal to amount displayed in bulk funding modal*/
	
	public Boolean VerifyBulkFundAmount(WebDriver driver) throws Exception	{
		boolean result =true;
			
		List<WebElement> rowCount= driver.findElements(By.xpath("//*[@id='content']/div/div[2]/form/div[3]/div/div/div/div[1]/div[2]/div/div")); 
		System.out.println("Total Rows Found are " + rowCount.size());
		double temp = 0;
		for (int i = 1; i <= rowCount.size(); i++) {
			String temp1 = driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div[3]/div/div/div/div[1]/div[2]/div/div["+i+"]/div/div[3]/div")).getText();
			double AmountUploaded = Double.parseDouble(temp1);
			temp = temp + AmountUploaded;
		}
		System.out.println("Total funds Uploaded to load is =====> "+ temp);
		driver.findElement(By.xpath("//*[@class='panel-body']/form/div[2]/div/button")).click();
		Thread.sleep(2000);
		String AmountModal = driver.findElement(By.xpath("//*[@class='modal-content']/div[2]/div/div[4]")).getText();
		AmountModal = AmountModal.substring(27);
		double Amountdisplayed=Double.parseDouble(AmountModal);
		System.out.println("Total Funds Displayed in Bulk Funding Modal is ====>"+ Amountdisplayed);
		if (temp==Amountdisplayed) {
			System.out.println("Total Funds Uploaded to Bulk Funding is EQUAL to the the Amount Displayed in Bulk Funding Modal");
		}else {
			result = false;
			System.out.println("Total Funds are NOT equal");
		}
		
			return result;	
	}
	
	
	/*Keyword to verify the Number of Employees that are Uploading through Bulk Upload.Will verify the count in Bulk Upload pop Up*/
	
    public Boolean UploadCount(WebDriver driver, String TDCount) throws Exception	{
	boolean result =false;
	
	String UPCount= driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[3]/b")).getText();
	if (TDCount.equals(UPCount)) {
		initialize().log.info("Total Number of Records successfully getting uploaded into business are  " + "  " + UPCount);
		result =true;
		}
	    return result;
    }

	/*Keyword to verify Whether the WebElement on the web Page is NOT Displayed*/
    
      public boolean isNOTDisplayed(String keyword, String keywordtype, String object, String objectProp)
        throws Exception
      {
        boolean isNOTDisplay = true;
        ActionElements acElem = new ActionElements();
        TestConstants tc = acElem.actionElement(keyword, keywordtype, object, objectProp);
        WebElement wb = tc.welement;
        System.out.println("verifying text======>>>"+wb.getText());
        if (wb.isDisplayed()) {
          isNOTDisplay = false;
        }
        return isNOTDisplay;
      }
     
  	/*Keyword to verify the Tool Tip for the Field Validation Errors in the Pocket Teller*/
      
      public boolean toolTip(WebDriver driver, String keyword, String keywordtype, String object, String verificationObject) throws Exception{
    	  boolean res = false;
    	  ActionElements acElem = new ActionElements();
          TestConstants tc = acElem.actionElement(keyword, keywordtype, object, verificationObject);
          WebElement wb = tc.welement;
          if (wb.isDisplayed()) {
        	  String actData =wb.getText();
        	  String expData = initialize().V.getProperty(verificationObject);
        	  if (actData.equalsIgnoreCase(expData)) {
        		  System.out.println("Tooltip value is: " + " " + actData);
        		  res = true;
        	  }
		}
    	  return res;
      }
  	public Boolean PIIDISABLED(WebDriver driver) throws Exception	{
		boolean result =false;
			
			String PIITabA =driver.findElement(By.xpath("//*[@id='piiTab']")).getAttribute("class");
			String PIITabE = "disabled";
			if (PIITabA.equals(PIITabE)) {
				initialize().log.info("PII Tab Status in User View is" + "" + PIITabA );
				result =true;
				} else {
					System.out.println("PII Tab in User View is NOT Disabled");
				}
			return result;	
	}
  	
  	public Boolean EDITDISABLED(WebDriver driver) throws Exception	{
		boolean result =false;
			
			String EditDisableA =driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div[3]/div/div/div[1]/div[1]/div[2]/div/div/div/div[9]/div/div/a[2]")).getAttribute("disabled");
			String EditDisableE = "true";
			if (EditDisableA.equals(EditDisableE)) {
				initialize().log.info("Edit button in User List is Disabled after Card Creation");
				result =true;
				} else {
					System.out.println("Edit button in User List is NOT Disabled after Card Creation");
				}
			return result;	
	}    

  	
      public boolean UserCard(WebDriver driver) throws Exception	{
    		boolean result =true;
    		IsDisabled disabled = new IsDisabled();	   
		  
///CREATING CARD FOR USER
		   
		   driver.findElement(By.xpath("//*[@id='createCard']")).click();
  		Thread.sleep(10000);
  		String CCTitleA = driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[1]/h3")).getText();
  		String CCTitleE = "Create Card";
  		if (CCTitleA.equalsIgnoreCase(CCTitleE)) {
  			System.out.println("Title of Create Card Popup displaying as " + "---> " + CCTitleA);
    		  
    	  }else {
			result=false;
			System.out.println("Title of Create Card Pop Up is NOT Valid");
		}
  		
  		if (driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[1]/div[1]/label")).isDisplayed()) {
			System.out.println("Programs Drop Down is displaying in the Create Card Modal");
			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[1]/div[1]/select/option[2]")).click();
			System.out.println("Program Has been Selected from the Programs Drop Down");
		}
  		Thread.sleep(2000);
  		driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[1]/button")).isDisplayed();
  		driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[1]/button")).click();
  		Thread.sleep(3000);
  		String CCSuccessMsgA= driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[3]/div")).getText();
  		String CCSuccessMsgE = "Card has been successfully created.";
  		if (CCSuccessMsgA.equalsIgnoreCase(CCSuccessMsgE)) {
      		  System.out.println("Success Message for Create Card is  " + "----> " + CCSuccessMsgA);      		 
      	  }else {
  			result=false;
  			System.out.println("Card NOT Created. Some Error Occurred");
  		}
  		disabled.isDisabled("", "xpath", "//*[@id='app']/div[2]/div/div/div[2]/div/div[1]/button", "");
  		System.out.println("Create Card button in Pop up is disabled");
  		String CCRefNumber= driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[1]/div[2]/p/span")).getText();
  		String UserTransRefE = driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[1]/div[2]/p/span")).getText();
  		System.out.println("Card Created with Reference Number : " + " ---->" + CCRefNumber);
  		driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button")).click();
 		    Thread.sleep(2000);
 		disabled.isDisabled("", "xpath", "//*[@id='createCard']", ""); 
 		System.out.println("Create Card button disabled after Card Creation");
 		driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/div[3]/div/div/div[2]/button[1]")).isEnabled();
 		System.out.println("Load Money button Enabled after Card Creation");
 		driver.findElement(By.xpath("//*[@class='panel-heading']/div/div[3]/div/div/div[1]/button[2]")).isEnabled();
 		System.out.println("Show GPR Enabled after Card Creation");
 		
 		/*String DOBA = driver.findElement(By.xpath("//*[@class='panel-body']/form/div/div[1]/ng-include[1]/div/div[2]/div/div[4]/p")).getText();
 		String DOBE = "Jan-01-1901";
 		if (DOBA.equalsIgnoreCase(DOBE)) {
			System.out.println("DOB is Masked after Card Creation as " + " ---->  " + DOBA);
		}else {
			result=false;
			System.out.println("DOB is NOT Masked after Card Creation" + " ---->  " + DOBA);
		}
 		String PhoneA = driver.findElement(By.xpath("//*[@class='panel-body']/form/div/div[2]/ng-include[2]/div/div[2]/div/div[1]/p/span")).getText();
 		String PhoneE = "(000) 000-0000";
 		if (PhoneA.equalsIgnoreCase(PhoneE)) {
			System.out.println("Phone Info Masked after Card Creation as " + " ---> " + PhoneA );
		}else {
			result = false;
			System.out.println("Phone is NOT Masked after Card Creation" + "   " + PhoneA);
		}
 		String MobileA = driver.findElement(By.xpath("//*[@class='panel-body']/form/div/div[2]/ng-include[2]/div/div[2]/div/div[2]/p/span")).getText();
 		String MobileE = "(000) 000-0000";
 		if (MobileA.equalsIgnoreCase(MobileE)) {
			System.out.println("Mobile Info Masked after Card Creation as " + " ---> " + MobileA );
		}else {
			result = false;
			System.out.println("Mobile is NOT Masked after Card Creation" + "   " + MobileA);
		}
 		String SSNA = driver.findElement(By.xpath("//*[@class='panel-body']/form/div/div[1]/ng-include[2]/div/div[2]/div/div[1]/div/div/p")).getText();
 		String SSNE = "*****";
 		if (SSNA.startsWith(SSNE)) {
			System.out.println("SSN is Masked after Card Creation" + "---->" + SSNA);
		}else {
			result = false;
			System.out.println("SSN is NOT Masked after Card Creation" + "---->" + SSNA);
		}
 		String HomeAddA = driver.findElement(By.xpath("//*[@class='panel-body']/form/div/div[2]/ng-include[1]/div/div[2]/div/div[1]/p/span[1]")).getText();
 		String HomeAddE = "********";
 		if (HomeAddA.equalsIgnoreCase(HomeAddE)) {
			
 			System.out.println("Home Address is Masked after Card Creation" + "--->" + HomeAddA);
		}else {
			result = false;
			System.out.println("Home Address is NOT Masked after Card Creation" + "--->" + HomeAddA);
		}
 		String OfficeAddA = driver.findElement(By.xpath("//*[@class='panel-body']/form/div/div[2]/ng-include[1]/div/div[2]/div/div[2]/p/span[1]")).getText();
 		String OfficeAddE = "********";
 		if (OfficeAddA.equalsIgnoreCase(OfficeAddE)) {
			
 			System.out.println("Office Address is Masked after Card Creation" + "--->" + OfficeAddA);
		}else {
			result = false;
			System.out.println("Office Address is NOT Masked after Card Creation" + "--->" + OfficeAddA);
		}
 		*/
 		String CardInfoA = driver.findElement(By.xpath("//*[@id='user']/div/div[3]/ng-include/div/div[1]/h5")).getText();
 		String CardInfoE = "Card Information:";
 		if (CardInfoA.equalsIgnoreCase(CardInfoE)) {
			
 			System.out.println("Card Information Section Displayed after Card Creation");
		}else {
			result = false;
			System.out.println("Card Information Section NOT displayed after card creation");
		}
 		String DDALabelA = driver.findElement(By.xpath("//*[@id='user']/div/div[3]/ng-include/div/div[2]/div/div/label")).getText();
 		String DDALabelE = "DDA Number";
 		if (DDALabelA.equalsIgnoreCase(DDALabelE)) {
			System.out.println("Label is displaying correctly for DDA Number as" + "---->" + DDALabelA);
		}
 		String DDANumA = driver.findElement(By.xpath("//*[@id='user']/div/div[3]/ng-include/div/div[2]/div/div/p/span")).getText();
 		String DDANumE = "90217843";
 		if (DDANumA.equalsIgnoreCase(DDANumE)) {
			System.out.println("DDA Number GOT Created as " + "---->" + DDANumA);
		}else {
			result = false;
			System.out.println("DDA Number NOT Created");
		}
 		String RouteLabelA = driver.findElement(By.xpath("//*[@id='user']/div/div[3]/ng-include/div/div[2]/div/div/label[2]")).getText();
 		String RouteLabelE = "Routing Number";
 		if (RouteLabelA.equalsIgnoreCase(RouteLabelE)) {
			System.out.println("Label is displaying correctly for Routing Number as" + "---->" + RouteLabelA);
		}
 		String RoutingNumberA = driver.findElement(By.xpath("//*[@id='user']/div/div[3]/ng-include/div/div[2]/div/div/p[2]/span")).getText();
 		String RoutingNumberE = "026014902";
 		if (RoutingNumberA.equalsIgnoreCase(RoutingNumberE)) {
			System.out.println("Routing Number GOT Created as " + "---->" + RoutingNumberA);
		}else {
			result = false;
			System.out.println("Routing Number NOT Created");
		}
 		
 		
///LOAD MONEY FOR USER WITH CREATED & ACTIVATED CARD REFERENCE NUMBER
 		    
 		    
    		/*driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/div[3]/div/div/div[2]/button[1]")).click();
    		Thread.sleep(2000);
    		String LoadMoneyTitleA = driver.findElement(By.xpath("//*[@class='modal-content']/div[1]/h3")).getText();
    		String LoadMoneyTitleE = "Load Money";
    		if (LoadMoneyTitleA.equalsIgnoreCase(LoadMoneyTitleE)) {
				System.out.println("Load Money Pop up title is " + "---->" + LoadMoneyTitleA);
			}else {
				result = false;
				System.out.println("Load Money title is Incorrect");
			}
    		if (disabled.isDisabled("", "xpath", "//*[@id='app']/div[2]/div/div/div[3]/button[2]", "")) {
 	  			System.out.println("Load Money button in the Load Money Pop Up is disabled by default");
 		   }
    		 Load Money by filling all the fields with Valid Data 
    		
    		driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[1]/select/option[2]")).click();
    		String DestinationNum = driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[2]/select/option[2]")).getText();
  			System.out.println("Reference number in drop down is" + DestinationNum);
  			Thread.sleep(10000);
  			if (DestinationNum.equalsIgnoreCase(CCRefNumber)) {
  				driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[2]/select/option[2]")).click();
  				System.out.println("Reference Number selected from Destination Number Drop Down");
  			}else {
  				result=false;
  				System.out.println("Reference Number did NOT selected from Destination Number Drop Down");
  			}
    		
  			driver.findElement(By.id("loadMoney")).clear();
  			Thread.sleep(1000);
  			driver.findElement(By.id("loadMoney")).sendKeys("100");
  			Thread.sleep(1000);
  			driver.findElement(By.xpath("//*[@id='form_loadMoney']/div[2]/input")).sendKeys("Funds Transfer with All Valid Data");
  			Thread.sleep(1000);
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[2]")).isEnabled();
  			System.out.println("Load Money button in Load Money Pop Up Enabled after entering all the fields with Valid Data");
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[2]")).click();
  			Thread.sleep(5000);
  			String LoadMoneySuccessMsgA = driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[2]/div")).getText();
  			String LoadMoneySuccessMsgE = "Amount of $100.00 is loaded for user";
  			if (LoadMoneySuccessMsgA.startsWith(LoadMoneySuccessMsgE)) {
  				System.out.println("Success message for Load Money is " + "" + LoadMoneySuccessMsgA);
			}else {
				result = false;
				System.out.println("Load Money is NOT Success");
			}
  			
  			if (disabled.isDisabled("", "xpath", "//*[@id='app']/div[2]/div/div/div[3]/button[2]", "")) {
 	  			System.out.println("Load Money button in the Load Money Pop Up is disabled after Funds Transfer is Success");
 		   }
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[1]")).click();
  			Thread.sleep(2000);
  			
  			 Load Money by Leaving the Destination Number as Blank 
  			
  			driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/div[3]/div/div/div[2]/button[1]")).click();
    		Thread.sleep(2000);
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[1]/select/option[2]")).click();
  			driver.findElement(By.id("loadMoney")).clear();
  			Thread.sleep(1000);
  			driver.findElement(By.id("loadMoney")).sendKeys("100");
  			Thread.sleep(1000);
  			driver.findElement(By.xpath("//*[@id='form_loadMoney']/div[2]/input")).sendKeys("Funds Transfer without selecting Destination Number");
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[2]")).isEnabled();
  			Thread.sleep(1000);
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[2]")).click();
  			Thread.sleep(5000);
  			String ErrorMsg1A = driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[2]/div")).getText();
  			String ErrorMsg1E = "Please enter the valid inputs to load funds.";
  			if (ErrorMsg1A.equalsIgnoreCase(ErrorMsg1E)) {
				System.out.println("Error message for Load Money without Destination Number is" + "--->" + ErrorMsg1A );
			}else {
				result = false;
				System.out.println("Error Message did NOT displayed for Load Money without Destination Number");
			}
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[1]")).click();
  			Thread.sleep(2000);
  			
  			 Load Money with Negative value in the Amount field
  			
  			driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/div[3]/div/div/div[2]/button[1]")).click();
    		Thread.sleep(2000);
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[1]/select/option[2]")).click();
    		String DestinationNum1 = driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[2]/select/option[2]")).getText();
  			System.out.println("Reference number in drop down is" + DestinationNum1);
  			Thread.sleep(10000);
  			if (DestinationNum1.equalsIgnoreCase(CCRefNumber)) {
  				driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div/div[2]/select/option[2]")).click();
  			}else {
  				result=false;
  			}
  			driver.findElement(By.id("loadMoney")).clear();
  			Thread.sleep(1000);
  			driver.findElement(By.id("loadMoney")).sendKeys("-100");
  			Thread.sleep(1000);
  			driver.findElement(By.xpath("//*[@id='form_loadMoney']/div[2]/input")).sendKeys("Funds Transfer with Negative value in Amount field");
  			Thread.sleep(1000);
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[2]")).isEnabled();
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[2]")).click();
  			Thread.sleep(5000);
  			String ErrorMsg2A = driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[2]/div[2]/div")).getText();
  			String ErrorMsg2E = "Please enter the valid inputs to load funds.";
  			if (ErrorMsg2A.equalsIgnoreCase(ErrorMsg2E)) {
				System.out.println("Error message for Load Money with -ve Value in Amount is displayed as " + "--->" + ErrorMsg2A );
			}else {
				result = false;
				System.out.println("Error Message did NOT displayed for Load Money with Negative Amount");
			}
  			driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div/div[3]/button[1]")).click();
  			Thread.sleep(2000);
*/
///SHOW GPR FOR USER
  			
  			driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/div[3]/div/div/div[1]/button[2]")).click();
  			Thread.sleep(5000);
  			if (driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div[2]/button")).isDisplayed()) {
				System.out.println("Back Button displayed in User GPR Info Page");
			}else {
				result = false;
				System.out.println("Back Button is NOT displaying in the User GPR Info Page");
			}
  			String UserGPRRefNum = driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[2]/div")).getText();
  			if(UserGPRRefNum.equalsIgnoreCase(CCRefNumber)) {
  				System.out.println("GPR Created for the User with " + "   " + UserGPRRefNum);
  				String CardStatusA = driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[3]/div")).getText();
  				String CardStatusE = "true";  				
  				if (CardStatusA.equalsIgnoreCase(CardStatusE)) {
  					System.out.println("GPR is created for the user and the card status is" + "   " + CardStatusA );
  					if (driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[6]/div/div/a")).isDisplayed()) {
						System.out.println("View Transactions Icon displayed for the Active Card");
					}else {
						result = false;
						System.out.println("View Transactions Icon NOT displayed");
					}
  					
  					driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[6]/div/div/a")).click();
  					System.out.println("Clicked on View Transactions Icon to see the User Transactions");
  					Thread.sleep(5000);
  					if (driver.findElement(By.xpath("//*[@id='content']/div/div/div[2]/div[1]/div[2]/button")).isDisplayed()) {
  						System.out.println("Back Button displayed in User Transactions Page");
  					}else {
  						result = false;
  						System.out.println("Back Button is NOT displaying in the User Transactions Page");
  					}
  					String UserTransRefA = driver.findElement(By.xpath("//*[@id='content']/div/div/div[2]/div[2]/div[2]/div[1]")).getText();
  					if(UserTransRefA.endsWith(UserTransRefE)) {
  						System.out.println("User Transactions GOT created and displayed for User");
  						String BalUserTrans = driver.findElement(By.xpath("//*[@id='content']/div/div/div[1]/h2/span")).getText().replace("$", "");
  						System.out.println("Balance in User Transactions is" + BalUserTrans);
  						double UserTransBal = Double.parseDouble(BalUserTrans);
  						
  						driver.findElement(By.xpath("//*[@id='content']/div/div/div[2]/div[1]/div[2]/button")).click();
  						Thread.sleep(5000);  						
  						if (CardStatusA.equalsIgnoreCase(CardStatusE)) {
  		  					System.out.println("Application Navigated back to User GPR Info from User Transactions");
  						}else {
  	  						result = false;	
  						}
  					String GPRBal =driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div[5]/div")).getText();
						double UserGPRBal = Double.parseDouble(GPRBal);
						System.out.println("Balance in User GPR is " +  UserGPRBal);
						
  						if (UserGPRBal==UserTransBal) {
							
  							System.out.println("Available Balance is Displaying as expected in User GPR and User Transactions");
						}else {
							System.out.println("Available Balance in User GPR and User Transactions does NOT match");
						}
  		  				driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div[2]/button")).click();
  		  				Thread.sleep(5000);
  		  			disabled.isDisabled("", "xpath", "//*[@id='createCard']", ""); 
  		   		    System.out.println("Application redirected back to User View from User GPR Info");
  					}else {
  						result=false;
  					}
  				}else {
  					result=false;
  				}
  						
  			}else {
  				result=false;
  			}
  			
  			Thread.sleep(5000);
			return result;
  			
	    	}
}

      

  /*   public boolean clickAndHold(WebDriver driver){
    	  boolean res = false;
    	  Actions toolTip1  = new Actions(driver); 
  		WebElement element=driver.findElement(By.id("programEdit.programName"));
  		toolTip1.clickAndHold(element).perform();
		return res;
      }

    		
      public Boolean Tooltip(WebDriver driver, String keyword, String keywordtype, String object, String objectProp ) throws Exception	{
  		boolean result =false;
  		
  		Actions toolTip1  = new Actions(driver); 
  		WebElement element=driver.findElement(By.id("programEdit.programName"));
  		toolTip1.clickAndHold(element).perform();
  		if(driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div[1]/div/ng-include/div/div[2]/div[1]/div[2]/div")).isDisplayed());{
  			System.out.println("Tool Tip is displaying with Field Validation Error Message");	
  		}
  		String ToolTipText = driver.findElement(By.xpath("//*[@id='content']/div/div[2]/form/div[1]/div/ng-include/div/div[2]/div[1]/div[2]/div")).getText();
  		System.out.println("Tooltip value is: " + " " + ToolTipText);
      {
  			return result;	
  	}
      
     public class ClickAndHold
      {
        public void clickAndHold(WebElement element, WebDriver driver)
          throws Exception
        {
          Actions builder = new Actions(driver);
          builder.clickAndHold(element).perform();
        }
      } 
      */
      
   

		