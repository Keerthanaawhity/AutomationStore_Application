package testCases;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.ProductBasePage;
import pageobjects.WishListPage;
import testBase.BaseClass;

public class TC009_WishlistBadgeCount extends BaseClass{

	@Test
	public void test_WishlistCountVerification_Badge_Page()
	{
	    try {

	        HeaderSec hs = new HeaderSec(getDriver());
	        LoginPage lp = new LoginPage(getDriver());
	        ProductBasePage pb = new ProductBasePage(getDriver());
	        WishListPage wlp = new WishListPage(getDriver());
	        MyAccountPage map = new MyAccountPage(getDriver());
	        
	        hs.ClickLorR();
	        lp.SetLoginName(p.getProperty("loginName"));
	        lp.Setpwd(p.getProperty("password"));
	        lp.clicklogin();
	        
	    	Map<String, String> productCategoryMap = new LinkedHashMap<>();
	    	productCategoryMap.put("Armani Code Pour Femme", "Women");
	    	productCategoryMap.put("Pour Homme Eau de Toilette", "Men");
	    	
	        pb.addMultipleProductsToWishlist(productCategoryMap, hs);
	        hs.HoverUserOptions().clickUserMenuFromSubCategory("My wish list");
	        String totalwishlistitems = Integer.toString(wlp.getTotalProductsinWishlistPage());
	        hs.ClickAccount();
	        Assert.assertEquals(totalwishlistitems,map.getNoOfWishlistBadge());

	        }
	    catch(Exception e)
	    {
	        logger.error("Exception during wishlist count verification: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");	
	    }
	}
}
