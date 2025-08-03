package pageobjects.Menus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageobjects.Basepage;

public class ApparelsPage extends Basepage {

	public ApparelsPage(WebDriver driver) {
		super(driver);
		
	}

	//Locators
	@FindBy(css = "a[href='https://automationteststore.com/index.php?rt=product/category&path=68_69']") WebElement Shoes;
	@FindBy(css="a[href='https://automationteststore.com/index.php?rt=product/category&path=68_70']") WebElement TShirts;
	
	//ActionMethods
	public void ClickShoes()
	{
		Shoes.click();
	}
	
	public void ClickTShirts()
	{
		TShirts.click();
	}
}
