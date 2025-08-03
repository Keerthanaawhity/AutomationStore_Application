package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailsPage extends Basepage{

	public OrderDetailsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//span[@class='maintext']") WebElement OrderDetailsText;
	@FindBy(xpath = "//td[b[text()='Order ID']]")WebElement orderSummaryBlock;
	@FindBy(xpath="//td/b[text()='Order ID']/following-sibling::text()[1]") WebElement OrderIDValue;
	@FindBy(xpath="//td/b[text()='Status']/following-sibling::text()[1]") WebElement OrderStatus;
	@FindBy(xpath="//b[normalize-space()='E-Mail']") WebElement OrderEmailText;
	//td//b[contains(text(),'Status')]/following-sibling::text()[1]
	@FindBy(xpath="//b[normalize-space()='Shipping Method']") WebElement OrderShippingMethodText;
	@FindBy(xpath="//b[normalize-space()='Telephone']") WebElement OrderTelephoneText;
	@FindBy(xpath="//b[normalize-space()='Payment Method']") WebElement OrderPaymentMethodText;
	@FindBy(xpath="//b[normalize-space()='Shipping Address']") WebElement OrderShippingAddressText;
	@FindBy(xpath="//b[normalize-space()='Payment Address']") WebElement OrderPaymentAddressText;
	@FindBy(xpath="(//a[normalize-space()='Continue'])[1]") WebElement OrderContinueButton;
	@FindBy(xpath="//table[@class='invoice_products table table-striped table-bordered']//tbody//tr[2]//td[2]//small") WebElement ProductSmallTitle;
	@FindBy(xpath="//table[@class='invoice_products table table-striped table-bordered']//tbody//tr[2]//td[2]//a") WebElement InvoiceProdName;
	@FindBy(xpath="//table[@class='invoice_products table table-striped table-bordered']//tbody//tr[2]//td[4]") WebElement InvoiceQuantity;
	@FindBy(xpath="//table[@class='invoice_products table table-striped table-bordered']//tbody//tr[2]//td[5]") WebElement InvoiceUnitPrice;
	@FindBy(xpath="//table[@class='invoice_products table table-striped table-bordered']//tbody//tr[2]//td[6]") WebElement InvoiceTotal;
	@FindBy(xpath="//div[contains(@class,'col-md-4 col-sm-6 col-xs-8 pull-right')]//table[contains(@class,'table table-striped table-bordered')]//tbody//tr[1]//td[2]") WebElement InvoiceSubtotal;
	@FindBy(xpath="//div[contains(@class,'col-md-4 col-sm-6 col-xs-8 pull-right')]//table[contains(@class,'table table-striped table-bordered')]//tbody//tr[2]//td[2]") WebElement InvoiceShippingCharge;
	@FindBy(xpath="//div[contains(@class,'col-md-4 col-sm-6 col-xs-8 pull-right')]//table[contains(@class,'table table-striped table-bordered')]//tbody//tr[3]//td[2]") WebElement InvoiceTotalPrice;
	@FindBy(xpath="//div[@class='form-group']//a[@title='Print']") WebElement PrintButton;
	
	
	private boolean isElementPresent(WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	/*public String getOrderIDDetails()
	{
		String OrderDetailsID = OrderIDValue.getText();
		String OrderIDDetails = OrderDetailsID.replaceAll("[^0-9]", "").trim();
		return OrderIDDetails;
	}*/
	
	public String getInvoiceOrderId() {
        String[] lines = orderSummaryBlock.getText().split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().equals("Order ID") && i + 1 < lines.length) {
                return lines[i + 1].replaceAll("[^0-9]", "").trim();
            }
        }
        return "";
    }
	
	/*public String getOrderStatus()
	{
		return OrderStatus.getText();
	}*/
	
	 public String getInvoiceStatus() {
	        String[] lines = orderSummaryBlock.getText().split("\n");
	        for (int i = 0; i < lines.length; i++) {
	            if (lines[i].trim().equals("Status") && i + 1 < lines.length) {
	                return lines[i + 1].trim();
	            }
	        }
	        return "";
	    }
	
	public boolean isTelephoneTextPresent() {
	    return isElementPresent(OrderTelephoneText);
	}
	
	public boolean isShippingMethodTextPresent() {
	    return isElementPresent(OrderShippingMethodText);
	}
	
	public boolean isPaymentTextPresent() {
	    return isElementPresent(OrderPaymentMethodText);
	}
	
	public boolean isShippingAddressTextPresent() {
	    return isElementPresent(OrderShippingAddressText);
	}
	
	public boolean isPaymentAddressTextPresent() {
	    return isElementPresent(OrderPaymentAddressText);
	}
	
	public String ProductSmallTextSize()
	{
		String ProductSmallTitleText = ProductSmallTitle.getText();
		String ProductSize = ProductSmallTitleText.replaceAll("[^0-9]", "").trim();
		return ProductSize;
	}
	
	public String getInvoiceProdName()
	{
		return InvoiceProdName.getText();
	}
	
	public String getInvoiceQuantity()
	{
		return InvoiceQuantity.getText();
	}
	
	public String getInvoicUnitPrice()
	{
		return InvoiceUnitPrice.getText();
	}
	
	public String getInvoiceTotal()
	{
		return InvoiceTotal.getText();
	}
	
	public void ClickContinueButton()
	{
		OrderContinueButton.click();;
	}
	
	public String getInvoiceSubTotal()
	{
		return InvoiceSubtotal.getText();
	}
	
	public String getInvoiceShippingCharge()
	{
		return InvoiceShippingCharge.getText();
	}
	
	public String getInvoiceTotalPrice()
	{
		return InvoiceTotalPrice.getText();
	}
	public boolean verifyInvoicePriceCalculation() {
	    // Get the values
	    String unitPriceInvoice = getInvoicUnitPrice();  // e.g. "$14.00"
	    String quantityInvoice = getInvoiceQuantity();    // e.g. "1"
	    String totalPriceInvoice = getInvoiceTotal(); // e.g. "$14.00"

	    // Clean and convert
	    double unitPrice = Double.parseDouble(unitPriceInvoice.replace("$", "").trim());
	    int quantity = Integer.parseInt(quantityInvoice.trim());
	    double totalPrice = Double.parseDouble(totalPriceInvoice.replace("$", "").trim());

	    // Calculate expected total
	    double expectedTotal = unitPrice * quantity;

	    // Allow small rounding difference (optional)
	    return  Math.abs(expectedTotal - totalPrice) < 0.01;
	}
	
	public boolean verifyInvoiceshipCharges()
	{
	    String SubTotalText = getInvoiceSubTotal();  
	    String ShippingChargeText = getInvoiceShippingCharge();   
	    String totalCartText = getInvoiceTotalPrice(); 

	    // Clean and convert
	    double SubTotal = Double.parseDouble(SubTotalText.replace("$", "").trim());
	    double ShippingCharges = Double.parseDouble(ShippingChargeText.replace("$", "").trim());
	    double totalcartvalue = Double.parseDouble(totalCartText.replace("$", "").trim());
	    
	    double expectedtotalcartvalue = SubTotal + ShippingCharges; 
	    
		return Math.abs(expectedtotalcartvalue - totalcartvalue) < 0.01;
	}
	
	public boolean isPrintButtonPresent() {
	    return isElementPresent(PrintButton);
	}
	
	public void ClickPrintButton()
	{
		PrintButton.click();
	}
}
