package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.Shoppingcart;
import testBase.BaseClass;

public class TC016_VerifyMaxLimit extends BaseClass{

	@Test
	public void test_VerifyMaxLimitTshirt()
	{
		try {
	    	HeaderSec hs = new HeaderSec(getDriver());
	    	LoginPage lp = new LoginPage(getDriver());
	    	Shoppingcart cart = new Shoppingcart(getDriver());
	    	ProductBasePage pb = new ProductBasePage(getDriver());
	    	
	    	hs.ClickLorR();
	    	lp.SetLoginName(p.getProperty("loginName"));
	    	lp.Setpwd(p.getProperty("password"));
	    	lp.clicklogin();
	    	
	    	hs.hoverOnAppareles().clickApparelsSubCategory("T-shirts");
	    	pb.openProductPage("Designer Men Casual Formal Double Cuffs Grandad Band Collar Shirt Elegant Tie");
	    	pb.SelectQuantity("11");
	    	pb.AddToCartItem();
	    	Thread.sleep(2000);
	    	Assert.assertEquals(cart.getAlertforMaxLimit(), "Allowed product's quantity exceeds. Quantity was set to maximum.");
	    	
		}
		catch(Exception e)
		{
	        logger.error("Exception during Verify MaxLimit of Tshirt : " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
		}
	}
}
