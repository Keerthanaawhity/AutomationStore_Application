package testCases;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.Shoppingcart;
import pageobjects.Menus.Fragrance.FragranceMenPage;
import testBase.BaseClass;

public class TC005_AddToCart extends BaseClass{

	public static String[] addProductToCart(WebDriver driver, String productName, Logger logger, SoftAssert softAssert) {
	    try {
	        HeaderSec hs = new HeaderSec(driver);
	        LoginPage lp = new LoginPage(driver);
	        Shoppingcart cart = new Shoppingcart(driver);
	        FragranceMenPage fmp = new FragranceMenPage(driver);
	        ProductBasePage pb = new ProductBasePage(driver);

	        hs.ClickLorR();
	        lp.SetLoginName(p.getProperty("loginName"));
	        lp.Setpwd(p.getProperty("password"));
	        lp.clicklogin();

	        hs.hoverOnFragrance().clickFragranceSubCategory("Men");

	        logger.info("******* Adding product to cart *******");
	        pb.addToCartByProductName(productName);

	        logger.info("******* Opening Product page *******");
	        pb.openProductPage(productName);
	        String expectedModelName = pb.getProdModelName();

	        hs.ClickShoppingcart();

	        return new String[] { cart.getCartProdname(), expectedModelName };

	    } catch (Exception e) {
	        logger.error("Exception during Add to cart validation: " + e.getMessage());
	        Assert.fail("Add to Cart failed due to an exception.");
	        return null;
	    }
	}

	
	@Test
	public void verify_AddToCart() {
	    logger.info("****** Starting Add to cart Validation Test ******");
	    SoftAssert softAssert = new SoftAssert();

	    try {
	        WebDriver driver = getDriver();
	        String productName = "ck one shock for him Deodorant";

	        String[] result = addProductToCart(driver, productName, logger, softAssert);
	        String cartProductName = result[0];
	        String expectedModelName = result[1];

	        Shoppingcart cart = new Shoppingcart(driver);
	        FragranceMenPage fmp = new FragranceMenPage(driver);

	        softAssert.assertEquals(fmp.getProductName(), cartProductName, "Product name mismatch in cart");
	        softAssert.assertEquals(expectedModelName, cart.getCartModel(), "Product model mismatch in cart");
	        softAssert.assertTrue(cart.verifyCartPriceCalculation(), "Price calculation incorrect");

	        softAssert.assertAll();

	    } catch (Exception e) {
	        logger.error("Exception during cart verification: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
	    }
	}

}
