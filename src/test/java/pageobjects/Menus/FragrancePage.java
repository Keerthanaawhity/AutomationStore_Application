package pageobjects.Menus;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageobjects.Basepage;

public class FragrancePage extends Basepage {

	public FragrancePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//div[@class='subcategories']//a[contains(text(),'Men')]") WebElement FragMen;
	@FindBy(xpath="//div[@class='subcategories']//a[contains(text(),'Women')]") WebElement FragWomen;
	
	//ActionMethods	
	public void clickFragranceSubCategory(String categoryName)
	{
	    List<WebElement> subCats = driver.findElements(By.xpath("//div[@class='subcategories']//a"));
	    for (WebElement subCat : subCats) {
	        if (subCat.getText().trim().equalsIgnoreCase(categoryName)) {
	            subCat.click();
	            break;
	        }
	    }
	}
	public void ClickFragMen()
	{
		clickFragranceSubCategory("Men");
	}
	
	public void ClickFragWomen()
	{
		clickFragranceSubCategory("Women");
	}
}
