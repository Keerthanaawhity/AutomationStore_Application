package testCases.SocialMediaLinks;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HeaderSec;
import testBase.BaseClass;

public class TC003_Twitter extends BaseClass{

	@Test
	public void verify_twitterlink() {
	    logger.info("****** Starting Twitter link Validation Test ******");
	    
	    try {
	    	HeaderSec hp = new HeaderSec(getDriver());
	    	hp.ClickTwitter();
	        switchToNewTab();
	        Assert.assertTrue(getDriver().getCurrentUrl().contains("x"), 
	             "Twitter URL validation failed: " + getDriver().getCurrentUrl());   	
	    }
	    catch(Exception e){
	    	logger.error("Exception during twitter link validation: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
	    }
	}
}
