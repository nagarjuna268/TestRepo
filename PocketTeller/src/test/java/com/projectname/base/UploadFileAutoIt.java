package com.projectname.base;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class UploadFileAutoIt {

	static WebDriver driver;
	String URL = "http://localhost:9090/";
	@Test
	public void testUpload() throws InterruptedException, IOException
	{
		driver = new FirefoxDriver();
		driver.get(URL);
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.findElement(By.id("login_username")).sendKeys("super@pt.com");
		driver.findElement(By.id("login_password")).sendKeys("superBanana");
		driver.findElement(By.xpath("//*[@id='login-fields-wrapper']/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Users")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div[1]/div[2]/div[1]/div/input")).sendKeys("super");
		driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div[9]/div[2]/div/div/a[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='userState']/div/div[1]/ul/li[3]/button")).click();
		Thread.sleep(5000);
		String searchRefNUM="101967";
		driver.findElement(By.xpath("//*[@id='content']/div/div/div/div/div[2]/div[1]/div/input")).sendKeys(searchRefNUM);
		Thread.sleep(5000);
		
		 List<WebElement> rowCount= driver.findElements(By.xpath("//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div"));
		System.out.println(rowCount.size());
		for (int i = 1; i <= rowCount.size(); i++) {
			
			String refNUM=driver.findElement(By.xpath("//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div["+i+"]/div[2]/div[2]/div")).getText();
			if (searchRefNUM.equals(refNUM)) {
				driver.findElement(By.xpath("//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div["+i+"]/div[6]/div[2]/div/div/a/i")).click();
				//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div[6]/div[2]/div/div/a/i
				//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[6]/div[2]/div/div/a/i
			}
			System.out.println("refNumber====================="+refNUM);
			
		}
		
		
		
		
		//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div[1]
		//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[1]
		
		
		//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div
		//*[@id='content']/div/div/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div
		
		
		
		
		
		//driver.findElement(By.linkText("Bulk Upload")).click();
		//Thread.sleep(1000);
		//new Select(driver.findElement(By.xpath("//*[@id='businesssList']/div/div/select"))).selectByVisibleText("cyberbus");
		//driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div[1]/div/div/div[2]/div[1]/input")).click();
		
		//WebElement element = driver.findElement(By.name("file"));
		//element.click();
		//Runtime.getRuntime().exec("G:/Tutorial/AutoItScripts/upload.exe");
	}	
	
}