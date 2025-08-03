package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.WishListPage;
import testBase.BaseClass;

public class TC010_DeleteProduct_From_WishList extends BaseClass{

	@Test
	public void verify_deleteFromWishList()
	{
		try {
			String productName = "Armani Code Pour Femme";
			
	    	HeaderSec hs = new HeaderSec(getDriver());
	    	LoginPage lp = new LoginPage(getDriver());
	    	WishListPage wlp = new WishListPage(getDriver());
	    	
	    	hs.ClickLorR();
	    	lp.SetLoginName(p.getProperty("loginName"));
	    	lp.Setpwd(p.getProperty("password"));
	    	lp.clicklogin();
	    	
	    	hs.HoverUserOptions().clickUserMenuFromSubCategory("My wish list");
	    	wlp.clickDeleteByProductNameinWishList(productName);
	    	Thread.sleep(5000);
	    	Assert.assertFalse(wlp.isProductPresentInWishlist(productName), "Product was not removed from wishlist");	
	    	
		}
		catch(Exception e)
		{
	        logger.error("Exception during Delete Product From Wishlist: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
		}
	}
}
