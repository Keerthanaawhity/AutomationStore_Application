package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.Menus.Fragrance.FragranceMenPage;
import testBase.BaseClass;

public class TC020_SortingHtoLVerification extends BaseClass{

	@Test
	public void test_VerifyPriceHtoLSort()
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
	        List<Double> sortedPrices = fmp.getReverseSortedPrices(getDriver());
	        logger.info("************** Sorting the actual price list ***********");
	        pb.ClickSortMenu();
	        pb.selectSortOptionByText(getDriver(), "Price High > Low");
	        List <Double> ActualPriceList = fmp.getAllElementPricelist(getDriver());
	        
	        Assert.assertTrue(ActualPriceList.equals(sortedPrices));
		}
		catch(Exception e)
		{
	        logger.error("Exception during Verify Price high to low sorting : " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
		}
	}
}
