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

public class TC013_OutOfStockProdToCart extends BaseClass{

	@Test
	public void test_VerifyOutOfStockProdToCart()
	{
		 logger.info("****** Starting OutOfStockinCartPage Validation Test ******");
		 String ProductName = "BeneFit Girl Meets Pearl";
		 
		 try {
	        HeaderSec hs = new HeaderSec(getDriver());
	        LoginPage lp = new LoginPage(getDriver());
	        ProductBasePage pb = new ProductBasePage(getDriver());
	        WishListPage wlp = new WishListPage(getDriver());
	        MyAccountPage map = new MyAccountPage(getDriver());
	        Shoppingcart cart = new Shoppingcart(getDriver());
	        
	        hs.ClickLorR();
	        lp.SetLoginName(p.getProperty("loginName"));
	        lp.Setpwd(p.getProperty("password"));
	        lp.clicklogin();
	        
	        hs.hoverOnMakeUp().clickMakeUpSubCategory("Cheeks");
	    	pb.ClickOutOfStockProduct();
	    	Thread.sleep(2000);
	    	pb.WishlistItem();
	    	hs.HoverUserOptions().clickUserMenuFromSubCategory("My wish list");
	    	wlp.clickAddToCartByProductNameinWishList(ProductName);
	    	Thread.sleep(2000);
	    	Assert.assertTrue(cart.isAsteriskPresentForProduct(ProductName),
	    		    "*** not found for expected out-of-stock product");
	    	
		 }
		 catch(Exception e)
		 {
			 logger.error("Exception during OutOfStockInCartPage validation: " + e.getMessage());
		     Assert.fail("Test failed due to an exception.");
		 }        
	}
}
