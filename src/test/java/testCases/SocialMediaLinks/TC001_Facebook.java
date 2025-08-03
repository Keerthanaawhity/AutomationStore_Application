package testCases.SocialMediaLinks;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HeaderSec;
import testBase.BaseClass;

public class TC001_Facebook extends BaseClass{
	@Test
	public void verify_fblink() {
	    logger.info("****** Starting Facebook link Validation Test ******");
	    
	    try {
	    	HeaderSec hp = new HeaderSec(getDriver());
	    	hp.ClickFacebook();
	        switchToNewTab();
	        Assert.assertTrue(getDriver().getCurrentUrl().contains("facebook"), 
	             "Facebook URL validation failed: " + getDriver().getCurrentUrl());   	
	    }
	    catch(Exception e){
	    	logger.error("Exception during facebook link validation: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
	    }
	}

}
