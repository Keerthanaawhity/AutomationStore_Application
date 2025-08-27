package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.Shoppingcart;
import pageobjects.Menus.Fragrance.FragranceMenPage;
import testBase.BaseClass;
import java.util.ArrayList;
import java.util.List;

public class TC019_SortingLtoHVerification  extends BaseClass{

	@Test
	public void test_VerifyPriceLtoHSort()
	{
		try {
	    	HeaderSec hs = new HeaderSec(getDriver());
	    	LoginPage lp = new LoginPage(getDriver());
	    	FragranceMenPage fmp = new FragranceMenPage(getDriver());
	    	ProductBasePage pb = new ProductBasePage(getDriver());
	    	
	    	hs.ClickLorR();
	    	lp.SetLoginName(p.getProperty("loginName"));
	    	lp.Setpwd(p.getProperty("password"));
	    	lp.clicklogin();

	        hs.hoverOnFragrance().clickFragranceSubCategory("Men");
	        logger.info("************** Sorting the actual price list ***********");
	        List<Double> sortedPrices = fmp.getSortedPrices(getDriver());
	        logger.info("************** Sorting the actual price list ***********");
	        pb.ClickSortMenu();
	        pb.selectSortOptionByText(getDriver(), "Price Low > High");
	        List <Double> ActualPriceList = fmp.getAllElementPricelist(getDriver());
	        
	        Assert.assertTrue(ActualPriceList.equals(sortedPrices));
		}
		catch(Exception e)
		{
	        logger.error("Exception during Verify Price low to high sorting : " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
		}
	}
}

