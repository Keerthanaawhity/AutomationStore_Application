package testCases;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.CheckoutPage;
import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.OrderDetailsPage;
import pageobjects.OrderSuccessPage;
import pageobjects.ProductBasePage;
import pageobjects.Shoppingcart;
import pageobjects.Menus.Fragrance.FragranceMenPage;
import testBase.BaseClass;
import java.awt.AWTException;

public class TC006_CheckoutProduct extends BaseClass {

String OrderIDConf;

public void savePDFInvoice(String orderId) {
    try {
        Thread.sleep(3000); // wait for save dialog to appear

        File folder = new File("D:\\Invoices\\");
        if (!folder.exists()) folder.mkdirs();
        
        String filePath = "D:\\Invoices\\Order_" + OrderIDConf + ".pdf";
        
        //File saving - To be handled
        if (filePath == null || filePath.isEmpty()) {
            logger.info("******* filePath is null or empty! *********");
        }
        
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        logger.info("****** Pressed Print Button ********");;
        
        Thread.sleep(3000);
        // Copy file path to clipboard
        StringSelection selection = new StringSelection(filePath);
        logger.info("Setting file path to clipboard: " + filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        logger.info("Pasting path and pressing Enter");

        // Paste (Ctrl + V)
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(2000); 

        // Press Enter to save
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        Thread.sleep(1000); 
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        logger.info("File saved to: " + filePath);

    } catch (Exception e) {
        e.printStackTrace();
        logger.error("Error saving invoice: " + e.getMessage());
    }
}
	
	@Test
	public void test_checkoutProd() {
	    logger.info("******** Starting checkout validation *******");
	    SoftAssert softAssert = new SoftAssert();

	    try {
	        WebDriver driver = getDriver();

	        // Reuse add to cart step
	        String[] result = TC005_AddToCart.addProductToCart(driver, "ck one shock for him Deodorant", logger, softAssert);
	        String cartProductName = result[0];

	        Shoppingcart cart = new Shoppingcart(driver);
	        CheckoutPage checkout = new CheckoutPage(driver);
	        OrderSuccessPage order = new OrderSuccessPage(driver);
	        OrderDetailsPage ODPage = new OrderDetailsPage(driver);

	        softAssert.assertTrue(cart.verifyCartPriceCalculation(), "Checkout price is incorrect");

	        cart.ClickCheckout();
	        softAssert.assertEquals(checkout.getCheckoutPageHeader(), "CHECKOUT CONFIRMATION");
	        checkout.ClickReturnpolicy();
	        checkout.ClickReturnpolicyClosebtn();
	        logger.info("********** Closed Return Policy ***********");
	        softAssert.assertEquals(cartProductName, checkout.getCartProductName(), "Product Mismatch in Checkout cart");
	        logger.info("********** Clicking Confirm order Button ***********");
	        checkout.ClickconfirmOrderBtn();
	        
	        logger.info("********** Navigated to order success page ***********");
	        softAssert.assertEquals(order.getOrderSuccesstext(), "YOUR ORDER HAS BEEN PROCESSED!");
	        OrderIDConf = order.getOrderID();
	        order.ClickInvoicepageLink();
	        softAssert.assertEquals(OrderIDConf, ODPage.getInvoiceOrderId(), "OrderID Mismatch in Order Details Page");
	        softAssert.assertEquals(ODPage.getInvoiceStatus(), "Pending");
	        softAssert.assertAll();
	        
	        ODPage.ClickPrintButton();
	        savePDFInvoice(OrderIDConf);
	        
	        logger.info("****** File saved !!! ********");

	    } catch (Exception e) {
	        logger.error("Exception during checkoutProduct validation: " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
	    }
	}

}

//Need to add test case for edit shipping, edit payment, edit cart, confirm order
