package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserOptionsPage extends Basepage{

	public UserOptionsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[contains(@href, 'wishlist')]") WebElement OptionWishlist;
	@FindBy(xpath="//ul[@id='customer_menu_top']//ul//li") WebElement CustomerMenus;

	public void ClickOptionWishlist()
	{
		OptionWishlist.click();
	}
	public void clickUserMenuFromSubCategory(String categoryName)
	{
		List<WebElement> subCats = driver.findElements(By.xpath("//ul[@id='customer_menu_top']//a[contains(normalize-space(),'"+ categoryName +"')]"));
	    for (WebElement subCat : subCats) {
	        if (subCat.getText().trim().equalsIgnoreCase(categoryName)) {
	            subCat.click();
	            break;
	        }
	    }
	}
	

}
