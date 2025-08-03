package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSuccessPage extends Basepage{

	public OrderSuccessPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//body[@class='checkout-success']//h1//span[@class='maintext']") WebElement OrderSuccesstext;
	@FindBy(xpath="//body[1]/div[1]/div[2]/div[1]/div[1]/div[1]//section//p[2]") WebElement OrderIdText;
	@FindBy(xpath="//a[normalize-space()='invoice page']") WebElement InvoicePageLink;
	@FindBy(xpath="//a[normalize-space()='store owner']") WebElement ContactStoreOwnerLink;
	@FindBy(xpath="//body[1]/div[1]/div[2]/div[1]/div[1]/div[1]//section//p[5]") WebElement ThankYouMessage;
	@FindBy(xpath="//a[normalize-space()='Continue']") WebElement ContinueBtn;
	
	public String getOrderSuccesstext()
	{
		return OrderSuccesstext.getText();
	}
	
	public String getOrderID()
	{
		String OrderIDtext = OrderIdText.getText();
		String OrderID = OrderIDtext.replaceAll("[^0-9]", "").trim();
		return OrderID;
	}
	
	public void ClickInvoicepageLink()
	{
		InvoicePageLink.click();
	}
	
	public void ClickContactStoreOwner()
	{
		ContactStoreOwnerLink.click();
	}
	
	public String getThanksMessageText()
	{
		return ThankYouMessage.getText();
	}
	
	public void ClickContinueBtn()
	{
		ContinueBtn.click();
	}
}
