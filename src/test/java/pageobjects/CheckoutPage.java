package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends Basepage{

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath=("//span[@class='maintext']")) WebElement ConfirmationPageText;
	@FindBy(xpath="//p[contains(text(),'By clicking Confirm Order, you accept and agree to')]") WebElement ReturnpolicyText;
	@FindBy(xpath="//b[normalize-space()='Return Policy']") WebElement ReturnpolicyLink;
	@FindBy(xpath="//div[@class='modal-content']//button[normalize-space()='Close']") WebElement ReturnPolicyClosebtn;
	@FindBy(xpath="//a[normalize-space()='Edit Shipping']") WebElement EditShippingbtn;
	@FindBy(xpath="//a[normalize-space()='Edit Payment']") WebElement EditPaymentBtn;
	@FindBy(xpath="//a[normalize-space()='Edit Cart']") WebElement EditCartBtn;
	@FindBy(xpath="//table[@class='table confirm_products']//td[2]//a") WebElement CartProductName;
	@FindBy(xpath="//button[@id='checkout_btn']") WebElement ConfirmOrderBtn;

	public String getCheckoutPageHeader()
	{
		return ConfirmationPageText.getText();
	}
	
	public String getReturnPolicyText()
	{
		return ReturnpolicyText.getText();
	}
	
	public void ClickReturnpolicy()
	{
		ReturnpolicyLink.click();
	}
	
	public void ClickReturnpolicyClosebtn()
	{
		ReturnPolicyClosebtn.click();
	}
	
	public void ClickEditShippingbtn()
	{
		EditShippingbtn.click();
	}
	
	public void ClickEditPaymentbtn()
	{
		EditPaymentBtn.click();
	}
	
	public void ClickEditCartbtn()
	{
		EditCartBtn.click();
	}
	
	public String getCartProductName() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(CartProductName));
	    return CartProductName.getText().trim(); // trim to avoid hidden whitespaces
	}
	
	public void ClickconfirmOrderBtn()
	{
		ConfirmOrderBtn.click();
	}
}