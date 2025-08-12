package pageobjects.Menus;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageobjects.Basepage;

public class ApparelsPage extends Basepage {

	public ApparelsPage(WebDriver driver) {
		super(driver);
		
	}

	public void clickApparelsSubCategory(String categoryName)
	{
	    List<WebElement> subCats = driver.findElements(By.xpath("//div[@class='subcategories']//a"));
	    for (WebElement subCat : subCats) {
	        if (subCat.getText().trim().equalsIgnoreCase(categoryName)) {
	            subCat.click();
	            break;
	        }
	    }
	}
}
