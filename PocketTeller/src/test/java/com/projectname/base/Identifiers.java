package com.projectname.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Identifiers extends Keywords {

	public WebElement welement(String keywordtype, String object)
			throws Exception {
		WebElement welement = null;
		driverWait = new WebDriverWait(driver, 10);
		try {
			switch (keywordtype.toUpperCase()) {
			case "ID":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.id(object)));
				welement = driver.findElement(By.id(object));
				return welement;
			case "XPATH":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(object)));
				welement = driver.findElement(By.xpath(object));
				return welement;
			case "LINKTEXT":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.linkText(object)));
				welement = driver.findElement(By.linkText(object));
				return welement;
			case "TAGNAME":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.id(object)));
				welement = driver.findElement(By.tagName(object));
				return welement;	
			case "PLINK":
				driverWait
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.partialLinkText(object)));
				welement = driver.findElement(By.partialLinkText(object));
				return welement;
			case "CLASSNAME":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.className(object)));
				welement = driver.findElement(By.className((object)));
				return welement;
			case "NAME":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.name(object)));
				welement = driver.findElement(By.name((object)));
				return welement;
			case "CSS":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector(object)));
				welement = driver.findElement(By.cssSelector((object)));
				return welement;
			default:
				break;
			}
		} catch (Exception e) {
			switch (keywordtype.toUpperCase()) {
			case "ID":
				Thread.sleep(900);
				welement = driver.findElement(By.id(object));
				return welement;
			case "XPATH":
				Thread.sleep(900);
				welement = driver.findElement(By.xpath(object));
				return welement;
			case "LINKTEXT":
				Thread.sleep(900);
				welement = driver.findElement(By.linkText(object));
				return welement;
			case "TAGNAME":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.id(object)));
				welement = driver.findElement(By.tagName(object));
				return welement;	
			case "PLINK":
				Thread.sleep(900);
				welement = driver.findElement(By.partialLinkText(object));
				return welement;
			case "CLASSNAME":
				Thread.sleep(900);
				welement = driver.findElement(By.className((object)));
				return welement;
			case "NAME":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.name(object)));
				welement = driver.findElement(By.name((object)));
				return welement;
			case "CSS":
				driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector(object)));
				welement = driver.findElement(By.cssSelector((object)));
				return welement;
			default:
				break;

			}
			e.getMessage();
		}
		return welement;
	}
}
