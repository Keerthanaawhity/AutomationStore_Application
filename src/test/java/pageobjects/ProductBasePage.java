package pageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductBasePage extends Basepage{

	public ProductBasePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[@class='wishlist_add btn btn-large']") WebElement Wishlist;
	@FindBy(xpath="//a[@class='cart']") WebElement AddtoCartItemBtn;
	@FindBy(xpath="//li[span[@class='productinfoleft' and contains(text(),'Model:')]]") WebElement ProdModelName;
	
	
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

}
