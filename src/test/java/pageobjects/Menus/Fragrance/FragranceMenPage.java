package pageobjects.Menus.Fragrance;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pageobjects.Basepage;

public class FragranceMenPage extends Basepage{

	public FragranceMenPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[@class='maintext']") WebElement MenpageHeader;
    @FindBy(css = "select[name='sort']") WebElement MensortDropdown;
    @FindBy(xpath="//div[@class='thumbnails grid row list-inline']//div//a//img") List<WebElement> MenProductList;
    @FindBy(linkText = "ck one shock for him Deodorant") WebElement ProdCK;
    
    
    
    public void selectSortBy(String option) {
        new Select(MensortDropdown).selectByVisibleText(option);
    }
    
    
    public void addFirstProductToCart() {
        driver.findElement(By.xpath("(//a[@title='Add to Cart'])[1]")).click();
    }
    
    public String getProductName() {
    	return ProdCK.getText().trim();
    }
    
}
