package testCases;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.WishListPage;
import testBase.BaseClass;

public class TC015_SelectTshirtColorSize extends BaseClass{

	@Test
	public void test_VerifyTshirtPurcahse()
	{
	try {
    	HeaderSec hs = new HeaderSec(getDriver());
    	LoginPage lp = new LoginPage(getDriver());

    	ProductBasePage pb = new ProductBasePage(getDriver());
    	
    	hs.ClickLorR();
    	lp.SetLoginName(p.getProperty("loginName"));
    	lp.Setpwd(p.getProperty("password"));
    	lp.clicklogin();
    	
    	hs.hoverOnAppareles().clickApparelsSubCategory("T-shirts");
    	pb.openProductPage("Designer Men Casual Formal Double Cuffs Grandad Band Collar Shirt Elegant Tie");
    	pb.SelectShirtColor("Blue");
    	pb.SelectShirtSize("Medium");

    	BigDecimal[] prices = pb.getUpdatedandExpectedTotalPrice("20");
    	Thread.sleep(2000);
        BigDecimal expected = prices[0];
        BigDecimal actual = prices[1];
        Assert.assertEquals(actual, expected, "Price mismatch!");
	}
	catch(Exception e)
	{
        logger.error("Exception during Tshirt Size and color selection: " + e.getMessage());
        Assert.fail("Test failed due to an exception.");
	}
	}
}
