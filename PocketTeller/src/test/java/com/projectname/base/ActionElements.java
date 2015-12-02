package com.projectname.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.projectname.actions.Mouseover;
import com.projectname.actions.ScrollBar;
import com.projectname.actions.WebFrames;
import com.projectname.utils.TestConstants;

public class ActionElements extends Keywords{

	Identifiers locaters = new Identifiers();
	public TestConstants actionElement(String keyword, String keywordtype,
			String object, String data) throws Exception {
		tc = new TestConstants();
		try {
			
			tc.welement = locaters.welement(keywordtype, object);
			
			switch (keyword.toUpperCase()) {
			case "CLICK":
				tc.welement.click();
				break;
			case "CHECKBOX":
			case "RADIOBUTTON":
				tc.welement.click();
				break;
			case "CLICKESC":
				iClass.initialize().log.info("Clicking on Escape Button");
				tc.welement.sendKeys(Keys.ESCAPE);
				break;
			case "CLICKENTER":
				tc.welement.sendKeys(Keys.ENTER);
				break;
			case "CLICKTAB":
				tc.welement.sendKeys(Keys.TAB);
				break;
			case "CLICKBACKSPC":
				tc.welement.sendKeys(Keys.BACK_SPACE);
				break;	
			case "GETELEMENTTEXT":
				String pageText = tc.welement.getText();
				iClass.initialize().log.info("Get Element Text-----------" + pageText);
				// pastevalue= getText(tc.welement);
				break;
			case "INPUT":
				tc.welement.clear();
				if (!tc.welement.getAttribute("value").isEmpty()) {
					return (TestConstants) tc.welement;
				} else {
					return (TestConstants) tc.welement;
				}
			case "KEYDOWN":
				tc.welement.sendKeys(Keys.DOWN);
				break;
			case "MOUSEOVER":
				Mouseover mo = new Mouseover();
				mo.mouseover(tc.welement, driver);
				break;
			case "PASTE":
				tc.welement.sendKeys(it.pastevalue);
				break;
			case "SCROLL":
				ScrollBar sbar = new ScrollBar();
				sbar.scrollBar(tc.welement, driver);
				break;
			case "SELECT":
				//System.out.println((TestConstants) tc.welement);
				return (TestConstants) tc.welement;
//				new Select(tc.welement).selectByVisibleText(data);
//				break;
			case "WEBSELECT":
				new Select(tc.welement).selectByVisibleText(data);
				break;
			case "SELECTFRAME":
				WebFrames wf = new WebFrames();
				wf.frame(tc.welement, driver);
				break;
			case "WAIT":
				Thread.sleep(Long.parseLong(data));
				break;
			case "WEBINPUT":
				tc.welement.clear();
				tc.welement.sendKeys(data);
				break;
			default:
				break;
			}
			tc.result = "Pass";
		} catch (Exception e) {
			tc.result = "Fail";
		}
		return tc;
	}

}
