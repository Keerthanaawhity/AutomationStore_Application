package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.MyOrderHistoryPage;
import testBase.BaseClass;

public class TC007_RetreiveOrderCount extends BaseClass {
	
	@Test(priority=1)
	public void test_OrderCount() {
		try {
			    HeaderSec hs = new HeaderSec(getDriver());
			    LoginPage lp = new LoginPage(getDriver());
			    MyAccountPage map = new MyAccountPage(getDriver());
			    MyOrderHistoryPage moh = new MyOrderHistoryPage(getDriver());
			    
			    hs.ClickLorR();
			    lp.SetLoginName(p.getProperty("loginName"));
			    lp.Setpwd(p.getProperty("password"));
			    lp.clicklogin();
			    
			    String TotalOrders = map.getNoOfOrdersBadge();
			    map.ClickOrderHistoryBtn();
			    String Pagerfooter = moh.getTotalIteminPage();
			    Assert.assertEquals(TotalOrders, Pagerfooter, "No of Orders Mismatch in badge and pager");
			    

		}
		catch(Exception e){
	        logger.error("Exception during Order Count Retreival: " + e.getMessage());
	        Assert.fail("Fetch order count failed due to an exception.");
		}
    
	}
}
