package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Shoppingcart extends Basepage{

	public Shoppingcart(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//td[@class='align_left']//a") WebElement CartProdName;
	@FindBy(xpath="//td[@class='align_left'][2]") WebElement CartModel;
	@FindBy(xpath="//td[@class='align_right'][1]") WebElement CartUnitprice;
	@FindBy(xpath="//div//input[contains(@id,'cart_quantity')]") WebElement CartQuantity;
	@FindBy(xpath="//td[@class='align_right'][2]") WebElement ProdTotalPrice;
	@FindBy(xpath="//a[@class='btn btn-sm btn-default']") WebElement ButtonDeleteProd;
	@FindBy(xpath="//button[@id='cart_update']") WebElement ButtonUpdate;
	@FindBy(xpath="//a[@id='cart_checkout2']") WebElement ButtonCheckout2;
	@FindBy(xpath="//select[@id='estimate_country']") WebElement ShippingCountry;
	@FindBy(xpath="//select[@id='estimate_country_zones']") WebElement ShippingState;
	@FindBy(xpath="//table[@id='totals_table']//tr[1]//td[2]//span") WebElement Subtotal;
	@FindBy(xpath="//table[@id='totals_table']//tr[2]//td[2]//span") WebElement ShippingCharge;
	@FindBy(xpath="//table[@id='totals_table']//tr[3]//td[2]//span") WebElement TotalCharge;
	@FindBy(xpath="//a[normalize-space()='Continue Shopping']") WebElement ContShoppingbtn;
	@FindBy(xpath="//div[@class='alert alert-error alert-danger']//strong") WebElement OutosAlert;
	@FindBy(xpath="//div[@class='alert alert-error alert-danger']//strong") WebElement MaxLimitAlert;
	
	
	public String getCartProdname()
	{
		return CartProdName.getText().trim();
	}
	public String getCartModel()
	{
		return CartModel.getText().trim();
	}
	public String getCartUnitprice()
	{
		return CartUnitprice.getText();
	}
	public String getCartQuantity()
	{
		return CartQuantity.getAttribute("value");
	}
	public String getCartTotalPrice()
	{
		return ProdTotalPrice.getText();
	}

	public void ClickCheckout()
	{
		ButtonCheckout2.click();
	}
	
	public void ClickContShoppingbtn()
	{
		ContShoppingbtn.click();
	}
	
	public void SelectShippingCountry(String ShipCountry)
	{
		ShippingCountry.click();
		Select shipctry = new Select(ShippingCountry);
		shipctry.selectByVisibleText(ShipCountry);
	}
	
	public void SelectShippingState(String ShipState)
	{
		ShippingState.click();
		Select shipctry = new Select(ShippingState);
		shipctry.selectByVisibleText(ShipState);
	}
	
	public String getSubtotalCharge()
	{
		return Subtotal.getText();
	}
	
	public String getShippingCharge()
	{
		return ShippingCharge.getText();
	}
	
	public String getTotalCartValue()
	{
		return TotalCharge.getText();
	}	
	
	public boolean verifycartwithship()
	{
	    String SubTotalText = getSubtotalCharge();  
	    String ShippingChargeText = getShippingCharge();   
	    String totalCartText = getTotalCartValue(); 

	    // Clean and convert
	    double SubTotal = Double.parseDouble(SubTotalText.replace("$", "").trim());
	    double ShippingCharges = Double.parseDouble(ShippingChargeText.replace("$", "").trim());
	    double totalcartvalue = Double.parseDouble(totalCartText.replace("$", "").trim());
	    
	    double expectedtotalcartvalue = SubTotal + ShippingCharges; 
	    
		return Math.abs(expectedtotalcartvalue - totalcartvalue) < 0.01;
	}
	
	public boolean verifyCartPriceCalculation() {
	    // Get the values
	    String unitPriceText = getCartUnitprice();  // e.g. "$14.00"
	    String quantityText = getCartQuantity();    // e.g. "1"
	    String totalPriceText = getCartTotalPrice(); // e.g. "$14.00"

	    // Clean and convert
	    double unitPrice = Double.parseDouble(unitPriceText.replace("$", "").trim());
	    int quantity = Integer.parseInt(quantityText.trim());
	    double totalPrice = Double.parseDouble(totalPriceText.replace("$", "").trim());

	    // Calculate expected total
	    double expectedTotal = unitPrice * quantity;

	    // Allow small rounding difference (optional)
	    return  Math.abs(expectedTotal - totalPrice) < 0.01;
	}

	public boolean isAsteriskPresentForProduct(String productName) {
	    String dynamicXPath = String.format("//td[@class='align_left'][.//a[contains(text(),'%s')] and .//span[text()='***']]", productName);
	    
	    try {
	        WebElement productWithAsterisk = driver.findElement(By.xpath(dynamicXPath));
	        return productWithAsterisk.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	public boolean isOosAlertDisplayed()
	{
		return OutosAlert.isDisplayed();
		
	}
	
	public String getAlertforMaxLimit()
	{
		return MaxLimitAlert.getText();
		
	}
	//////////////////Add Test case for checkout and page object for payment page and confirmation page
}
