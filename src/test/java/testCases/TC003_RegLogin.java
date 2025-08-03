package testCases;

import testBase.BaseClass;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pageobjects.RegistrationPage;
import pageobjects.HeaderSec;
import pageobjects.LoginPage;

public class TC003_RegLogin extends BaseClass{
	
	@Test(dependsOnMethods = {"testCases.TC001_AccountRegistration.verify_acc_regn"}, groups="login")
	public void verify_login_with_registered_credentials(ITestContext context) {
        String loginName = (String) context.getAttribute("loginName");
        String password = (String) context.getAttribute("password");
        String FirstName = (String) context.getAttribute("Accname");

	    //Assert.assertNotNull(loginName, "Login name was not set in registration test!");
	    //Assert.assertNotNull(password, "Password was not set in registration test!");

	    HeaderSec hs = new HeaderSec(getDriver());
	    hs.ClickAccount();

	    LoginPage lp = new LoginPage(getDriver());
	    lp.SetLoginName(loginName);
	    lp.Setpwd(password);
	    lp.clicklogin();
	    
	    RegistrationPage rp = new RegistrationPage(getDriver());
	    String Accname = rp.getacctname().trim();
	    if(Accname.equals(FirstName.trim()))
		{
			Assert.assertTrue(true);
		}
		else
		{
			 logger.error("Account name mismatch! Expected: " + FirstName + ", Actual: " + Accname);
			 Assert.fail("Account name did not match.");
		}	

	    // Add your validation here (like checking page title or welcome message)
	}

}