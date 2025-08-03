package pageobjects.Menus.Fragrance;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pageobjects.Basepage;

public class FragranceWomenPage extends Basepage{

	public FragranceWomenPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "select[name='sort']") WebElement WomensortDropdown;
	@FindBy(xpath="//div[@class='thumbnails grid row list-inline']//div//a//img") List<WebElement> WomenProductList;
	@FindBy(linkText="Armani Code Pour Femme") WebElement ProdArmani;
	
    public void selectSortBy(String option) {
        new Select(WomensortDropdown).selectByVisibleText(option);
    }
    
    public String getProductName() {
    	return ProdArmani.getText().trim();
    }
    
    
}