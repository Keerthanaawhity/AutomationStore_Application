package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.ProductBasePage;
import pageobjects.Shoppingcart;
import pageobjects.WishListPage;
import testBase.BaseClass;

public class TC014_OutOfStockAlertinCart extends BaseClass{

	@Test
	public void test_VerifyOutOfStockAlert()
	{
		 logger.info("****** Starting OutOfStockAlert Validation Test ******");
		 
		 try {
	        HeaderSec hs = new HeaderSec(getDriver());
	        LoginPage lp = new LoginPage(getDriver());
	        Shoppingcart cart = new Shoppingcart(getDriver());
	        
	        hs.ClickLorR();
	        lp.SetLoginName(p.getProperty("loginName"));
	        lp.Setpwd(p.getProperty("password"));
	        lp.clicklogin();
	        
	    	hs.ClickShoppingcart();
	    	cart.isOosAlertDisplayed();
	    	
		 }
		 catch(Exception e)
		 {
			 logger.error("Exception during OutOfStockAlert validation: " + e.getMessage());
		     Assert.fail("Test failed due to an exception.");
		 }
        
        
	}
}
