package pageobjects.Menus;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.Basepage;

public class MakeUpPage extends Basepage{

	public MakeUpPage(WebDriver driver) {
		super(driver);
	}

	public void clickMakeUpSubCategory(String categoryName)
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
