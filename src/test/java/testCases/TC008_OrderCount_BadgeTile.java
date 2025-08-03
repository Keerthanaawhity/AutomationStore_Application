package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testBase.BaseClass;

public class TC008_OrderCount_BadgeTile extends BaseClass{

	@Test
	public void test_badge_tile() {
		try {
		    HeaderSec hs = new HeaderSec(getDriver());
		    LoginPage lp = new LoginPage(getDriver());
		    MyAccountPage map = new MyAccountPage(getDriver());
		    
		    hs.ClickLorR();
		    lp.SetLoginName(p.getProperty("loginName"));
		    lp.Setpwd(p.getProperty("password"));
		    lp.clicklogin();
		    
		    String TotalOrders = map.getNoOfOrdersBadge();
		    Assert.assertEquals(TotalOrders, map.getNoOfOrdersinTile(), "No of Orders Mismatch in badge and Tile");
		    

	}
	catch(Exception e){
        logger.error("Exception during Order Count Verification: " + e.getMessage());
        Assert.fail("Order Count Verification failed due to an exception.");
		}
	}
}
