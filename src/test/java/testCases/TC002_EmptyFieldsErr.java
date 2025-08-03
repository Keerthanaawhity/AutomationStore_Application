package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.RegistrationPage;
import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import testBase.BaseClass;

public class TC002_EmptyFieldsErr extends BaseClass{
	
	@Test(groups="Alert")
	public void verify_empty_fields_error_messages() {
	    logger.info("****** Starting Empty Fields Validation Test ******");

	    try {
	        HeaderSec hs = new HeaderSec(getDriver());
	        hs.ClickAccount();

	        LoginPage lp = new LoginPage(getDriver());
	        lp.ClickRegister();

	        RegistrationPage rp = new RegistrationPage(getDriver());

	        // Leave all fields empty
	        rp.setContinue();  // Click Continue to trigger validation errors

	        // Assert error messages
	        Assert.assertTrue(rp.getFirstNameError().contains("First Name must be between 1 and 32 characters!"), "First name error not shown.");
	        Assert.assertTrue(rp.getLastNameError().contains("Last Name must be between 1 and 32 characters!"), "Last name error not shown.");
	        Assert.assertTrue(rp.getEmailError().contains("Email Address does not appear to be valid!"), "Email error not shown.");
	        Assert.assertTrue(rp.getAddr1Error().contains("Address 1 must be between 3 and 128 characters!"), "Address1 error not shown.");
	        Assert.assertTrue(rp.getCityError().contains("City must be between 3 and 128 characters!"), "City error not shown.");
	        Assert.assertTrue(rp.getZoneError().contains("Please select a region / state!"), "State error not shown.");
	        Assert.assertTrue(rp.getZipcodeError().contains("Zip/postal code must be between 3 and 10 characters!"), "Zipcode error not shown.");
	        Assert.assertTrue(rp.getpasswordError().contains("Password must be between 4 and 20 characters!"), "Password error not shown.");
	        Assert.assertTrue(rp.getPrivacyError().contains("Error: You must agree to the Privacy Policy!"), "Privacy policy error not shown.");

	    } catch (Exception e) {
	        logger.error("Exception during empty field validation: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
	    }
	}


}
