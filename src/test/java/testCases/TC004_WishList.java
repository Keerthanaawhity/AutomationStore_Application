package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.WishListPage;
import pageobjects.Menus.Fragrance.FragranceWomenPage;
import testBase.BaseClass;

public class TC004_WishList extends BaseClass {

	@Test
	public void verify_Wishlist() {
	    logger.info("****** Starting Wishlist links Validation Test ******");
	    
	    try {
	    	HeaderSec hs = new HeaderSec(getDriver());
	    	hs.ClickLorR();
	    	
	    	LoginPage lp = new LoginPage(getDriver());
	    	lp.SetLoginName(p.getProperty("loginName"));
	    	
	    	lp.Setpwd(p.getProperty("password"));
	    	lp.clicklogin();
	    	
	    	hs.hoverOnFragrance().clickFragranceSubCategory("Women");
	    	
	    	ProductBasePage pb = new ProductBasePage(getDriver());
	    	pb.openProductPage("Armani Code Pour Femme");
	    	pb.WishlistItem();
	    	
	    	//UserOptionsPage uop = new UserOptionsPage(getDriver());
	    	hs.HoverUserOptions().clickUserMenuFromSubCategory("My wish list");
	    	FragranceWomenPage fwp = new FragranceWomenPage(getDriver());
	    	
	    	WishListPage wlistp = new WishListPage(getDriver());
	    	Assert.assertEquals(fwp.getProductName(),wlistp.getCartProdname() );
	    	
	    }
	    catch(Exception e){
	    	logger.error("Exception during wishlist validation: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
	    }
	}
}
