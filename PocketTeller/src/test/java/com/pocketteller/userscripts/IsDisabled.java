package com.pocketteller.userscripts;

import com.projectname.base.ActionElements;
import com.projectname.utils.TestConstants;
import org.openqa.selenium.WebElement;

public class IsDisabled
{
  public boolean isDisabled(String keyword, String keywordtype, String object, String objectProp)
    throws Exception
  {
    boolean isDisabled = true;
    ActionElements acElem = new ActionElements();
    TestConstants tc = acElem.actionElement("", keywordtype, object, 
      objectProp);
    WebElement wb = tc.welement;
    if (wb.isEnabled()) {
    	isDisabled = false;
    }
    
    return isDisabled;
  }
}
