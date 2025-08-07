package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.Menus.MakeUp.MakeupCheeksPage;
import testBase.BaseClass;

public class TC012_VerifyOutOfStockProduct extends BaseClass{

	@Test
	public void test_VerifyOutOfStockinProdPage()
	{
		 logger.info("****** Starting OutOfStockinProdPage Validation Test ******");
		 try
		 {
		    	HeaderSec hs = new HeaderSec(getDriver());
		    	ProductBasePage pb = new ProductBasePage(getDriver());
		    	hs.ClickLorR();
		    	LoginPage lp = new LoginPage(getDriver());
		    	lp.SetLoginName(p.getProperty("loginName"));
		    	lp.Setpwd(p.getProperty("password"));
		    	lp.clicklogin();
		    	
		    	hs.hoverOnMakeUp().clickMakeUpSubCategory("Cheeks");
		    	pb.ClickOutOfStockProduct();
		    	Assert.assertEquals(pb.getOutOfStockText(), "Out of Stock");
		    	
		 }
		 catch(Exception e)
		 {
			 logger.error("Exception during OutOfStockInProdPage validation: " + e.getMessage());
		     Assert.fail("Test failed due to an exception.");
		 }
	}
}
