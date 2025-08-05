package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.Shoppingcart;
import pageobjects.WishListPage;
import testBase.BaseClass;

public class TC011_AddToCartFromWishlist extends BaseClass{

	@Test
	public void test_VerifyAddToCartFromWishList()
	{
		try {
			String productName = "Pour Homme Eau de Toilette";
			
	    	HeaderSec hs = new HeaderSec(getDriver());
	    	LoginPage lp = new LoginPage(getDriver());
	    	WishListPage wlp = new WishListPage(getDriver());
	    	ProductBasePage pb = new ProductBasePage(getDriver());
	    	Shoppingcart cart = new Shoppingcart(getDriver());
	    	
	    	hs.ClickLorR();
	    	lp.SetLoginName(p.getProperty("loginName"));
	    	lp.Setpwd(p.getProperty("password"));
	    	lp.clicklogin();
	    	
	    	hs.HoverUserOptions().clickUserMenuFromSubCategory("My wish list");
	    	String wlpProductname = wlp.getCartProdname();
	    	wlp.clickAddToCartByProductNameinWishList(productName);
	    	Thread.sleep(2000);
	    	pb.AddToCartItem();
	    	Assert.assertEquals(wlpProductname, cart.getCartProdname(), "Product Mismatch in Cart from wishlist");	    	
		}
		catch(Exception e)
		{
	        logger.error("Exception during AddtoCart Product From Wishlist: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
		}
	}
}
