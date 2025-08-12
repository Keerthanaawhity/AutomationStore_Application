package testCases;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HeaderSec;
import pageobjects.LoginPage;
import pageobjects.ProductBasePage;
import pageobjects.Shoppingcart;
import testBase.BaseClass;

public class TC017_PrintProduct extends BaseClass{

	String ProdName = "Designer Men Casual Formal Double Cuffs Grandad Band Collar Shirt Elegant Tie";
	@Test
	public void test_VerifyPrintProduct()
	{
		try {
	    	HeaderSec hs = new HeaderSec(getDriver());
	    	LoginPage lp = new LoginPage(getDriver());
	    	Shoppingcart cart = new Shoppingcart(getDriver());
	    	ProductBasePage pb = new ProductBasePage(getDriver());
	    	
	    	hs.ClickLorR();
	    	lp.SetLoginName(p.getProperty("loginName"));
	    	lp.Setpwd(p.getProperty("password"));
	    	lp.clicklogin();
	    	
	    	hs.hoverOnAppareles().clickApparelsSubCategory("T-shirts");
	    	pb.openProductPage(ProdName);
	    	pb.ClickProdPrintButton();
	    	saveProductPDF(ProdName);
	    	
		}
		catch(Exception e)
		{
	        logger.error("Exception during Verify Print Product : " + e.getMessage());
	        Assert.fail("Test failed due to an exception.");
		}
	}
	
	
	public void saveProductPDF(String ProdName) {
	    try {
	        Thread.sleep(3000);

	        File folder = new File("D:\\PrintProduct\\");
	        if (!folder.exists()) folder.mkdirs();
	        
	        String filePath = "D:\\PrintProduct\\" + ProdName + ".pdf";
	        
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
	        
	        File pdfFile = new File(filePath);
	        assert pdfFile.exists() : "PDF file does not exist: " + filePath;

	       logger.info("File saved to: " + filePath);

	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.error("Error Printing and saving product: " + e.getMessage());
	    }
	}
}
