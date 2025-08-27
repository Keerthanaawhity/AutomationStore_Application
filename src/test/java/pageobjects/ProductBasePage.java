package pageobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductBasePage extends Basepage{

	public ProductBasePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[@class='wishlist_add btn btn-large']") WebElement Wishlist;
	@FindBy(xpath="//a[@class='cart']") WebElement AddtoCartItemBtn;
	@FindBy(xpath="//li[span[@class='productinfoleft' and contains(text(),'Model:')]]") WebElement ProdModelName;
	@FindBy(xpath="//span[@class='nostock']") WebElement OutOfStockButton;
	@FindBy(xpath="//div[@class='thumbnails grid row list-inline']//div//div[@class='thumbnail']//div[@class='pricetag jumbotron']//span//ancestor::div[@class='pricetag jumbotron']//ancestor::div[@class='thumbnail']") WebElement OutOfStockProduct;
	@FindBy(id="option350") WebElement TshirtColordrpdwn;
	@FindBy(id="option351") WebElement TshirtSizedrp;
	@FindBy(xpath="//input[@id='product_quantity']") WebElement ProdQuant;
	@FindBy(xpath="//span[@class='total-price']") WebElement TotalPrice;
	@FindBy(xpath="//div[contains(text(),'limit')]") WebElement MaxqtyLimit;
	@FindBy(xpath="//a[@class='productprint btn btn-large']") WebElement ProductPrintBtn;
	@FindBy(xpath="//select[@id='sort']") WebElement SortList;
	
	
    public void addToCartByProductName(String productName) {
        String xpath = String.format("//a[contains(text(),'%s')]/ancestor::div[contains(@class,'fixed')]//a[@title='Add to Cart']", productName);
        driver.findElement(By.xpath(xpath)).click();
    } 
    
    public void openProductPage(String productName) {
        String xpath = String.format("//a[contains(text(),'%s')]/ancestor::div[contains(@class,'fixed_wrapper')]/following-sibling::div[@class='thumbnail']//img", productName);
        driver.findElement(By.xpath(xpath)).click();
    }
    
    public void WishlistItem()
    {
    	Wishlist.click();
    }
    
    public void AddToCartItem()
    {
    	AddtoCartItemBtn.click();
    }
    
    public String getProdModelName()
    {
    	return ProdModelName.getText().replace("Model:", "").trim();
    }
	
    public void addMultipleProductsToWishlist(Map<String, String> productCategoryMap, HeaderSec hs) {
        for (Map.Entry<String, String> entry : productCategoryMap.entrySet()) {
            String productName = entry.getKey();
            String category = entry.getValue();

            hs.hoverOnFragrance().clickFragranceSubCategory(category);            
            openProductPage(productName);
            WishlistItem();
        }
    }
    
    public String getOutOfStockText()
    {
    	return OutOfStockButton.getText().trim();
    }
    
	public void ClickOutOfStockProduct()
	{
		OutOfStockProduct.click();
	}
	
	public  void SelectShirtColor(String TshirtColor)
	{
		Select color = new Select(TshirtColordrpdwn);
		color.selectByContainsVisibleText(TshirtColor);
	}
	
	public  void SelectShirtSize(String TshirtSize)
	{
		Select size = new Select(TshirtSizedrp);
		size.selectByContainsVisibleText(TshirtSize);
	}
	
	public void SelectQuantity(String quant)
	{
	    ProdQuant.clear();
	    ProdQuant.sendKeys(quant);
	}
	
	public String getProdQuantity()
	{
		return ProdQuant.getText();		
	}
	
	public String getUnitPrice()
	{
		return TotalPrice.getText();		
	}
	
	private BigDecimal parsePrice(String priceText) {
	    String cleaned = priceText.replaceAll("[^0-9.]", ""); // removes $, spaces, etc.
	    return new BigDecimal(cleaned).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal[] getUpdatedandExpectedTotalPrice(String qty) throws InterruptedException {
	    // 1 - Get product unit price as BigDecimal
	    BigDecimal unitPrice = parsePrice(TotalPrice.getText());
	    System.out.println("Unit Price: " + unitPrice);
	    
	    BigDecimal expectedPrice = unitPrice
	            .multiply(new BigDecimal(qty))
	            .setScale(2, RoundingMode.HALF_UP);
	        System.out.println("Expected Price: " + expectedPrice);

	    // 2 - Update quantity
	    ProdQuant.clear();
	    ProdQuant.sendKeys(qty);

	    // 3 - Wait until total price changes
	    new WebDriverWait(driver, Duration.ofSeconds(5))
	        .until(ExpectedConditions.not(
	            ExpectedConditions.textToBePresentInElement(
	                TotalPrice,
	                unitPrice.toPlainString() // avoid old value
	            )
	        ));

	    // 4 - Get updated price as BigDecimal
	    BigDecimal updatedPrice = parsePrice(TotalPrice.getText());
	    System.out.println("Updated Price: " + updatedPrice);

	    return new BigDecimal[] {expectedPrice, updatedPrice} ;
	}

	public String getMaxProdQuantity()
	{
		String limitText = MaxqtyLimit.getText().replaceAll("[^0-9]","");
		return limitText;
	}
	
    public void ClickProdPrintButton()
    {
    	ProductPrintBtn.click();
    }
    
    public void ClickSortMenu()
    {
    	SortList.click();
    }
    
    public void selectSortOptionByText(WebDriver driver, String visibleText) {
        Select sortSelect = new Select(SortList);
        sortSelect.selectByVisibleText(visibleText);
    }
	
}
