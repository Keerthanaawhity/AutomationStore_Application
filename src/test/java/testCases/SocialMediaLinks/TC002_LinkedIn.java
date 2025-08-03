package testCases.SocialMediaLinks;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HeaderSec;
import testBase.BaseClass;

public class TC002_LinkedIn extends BaseClass {

	@Test
	public void verify_LinkedInlink() {
	    logger.info("****** Starting LinkedIn link Validation Test ******");
	    
	    try {
	    	HeaderSec hp = new HeaderSec(getDriver());
	    	hp.ClickLinkedIn();
	        switchToNewTab();
	        Assert.assertTrue(getDriver().getCurrentUrl().contains("linkedin"), 
	             "LinkedIn URL validation failed: " + getDriver().getCurrentUrl());   	
	    }
	    catch(Exception e){
	    	logger.error("Exception during linkedin link validation: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
	    }
	}
}
