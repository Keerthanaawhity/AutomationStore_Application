package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pageobjects.RegistrationPage;
import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {


    public static String registeredLoginName;
    public static String registeredPassword;
    
	@Test(groups = "login")
	public void verify_acc_regn(ITestContext context)
	{
		
		logger.info("****** Starting TC001_AccReg Test ******");
		try {
			HeaderSec hs = new HeaderSec(getDriver());
			hs.ClickAccount();
			logger.info("****** Clicked Account *******");
			
			LoginPage lp = new LoginPage(getDriver());
			lp.ClickRegister();
			logger.info("****** Clicked Registration *******");
			
			RegistrationPage rp = new RegistrationPage(getDriver());
			
			String Loginname = "Oliverr";
			String Password = "Asterik@123";
			String FirstName = "John";
			//Personal Details
			rp.setFirstName(FirstName);
			rp.setLastName("David");
			rp.setEmail("oliverr@gmail.com");
			rp.setTelephone("123456");
			rp.setFax("778899");
			
			//Address
			rp.setCompany("Elxsi");
			rp.setAddress1("Buddhar Street");
			rp.setAddress2("People's Junction");
			rp.setCountry("India");
			rp.setZoneid("Karnataka");
			rp.setCity("Bangalore");
			rp.setPostcode("560038");
			
			//Login Details
			rp.setLoginName(Loginname);
			rp.setPassword(Password);
			rp.setConfirmpwd(Password);
			
	        context.setAttribute("loginName", Loginname);
	        context.setAttribute("password", Password);
	        
	        context.setAttribute("Accname", FirstName);
	        String Accountname = (String) context.getAttribute("Accname");
			
			//PrivacyPolicy
			rp.setAgree();
			rp.setContinue();

			//Validation
			
			//1
			if(!(rp.isValidLoginName(Loginname))) {
				String expectedError = "Login name must be alphanumeric and between 5 to 64 characters."; // Replace with actual expected message
		        Assert.assertEquals(rp.getLoginNameErrorMessage(), expectedError, "Unexpected login name error shown.");
			}
			else {
			Assert.assertTrue(rp.isValidLoginName(Loginname), "Invalid login name format!");}
			
			//2
			/*String confmsg = rp.getConfmsg();
			logger.info("****** Validating expected message *******");
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("The test is failed");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}*/
			
			//3
			String confacct = rp.getacctname().trim();
			logger.info("****** Validating expected message *******");
			if(confacct.equals(Accountname.trim()))
			{
				Assert.assertTrue(true);
			}
			else
			{
				 logger.error("Account name mismatch! Expected: " + Accountname + " Actual: " + confacct);
				 Assert.fail("Account name did not match.");
			}
		}
		catch(Exception e){

			logger.error("Exception occurred in account registration test: " + e.getMessage(), e);
		    Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}
}

