package pageobjects.Menus.Fragrance;

import java.util.ArrayList;
import java.util.Collections;
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
    @FindBy(xpath="//div[@class='oneprice' or @class='pricenew']") List<WebElement> MenFragPriceList;
    
    
    
    public void selectSortBy(String option) {
        new Select(MensortDropdown).selectByVisibleText(option);
    }
    
    
    public void addFirstProductToCart() {
        driver.findElement(By.xpath("(//a[@title='Add to Cart'])[1]")).click();
    }
    
    public String getProductName() {
    	return ProdCK.getText().trim();
    }
    
    public List<Double> getSortedPrices(WebDriver driver) {
        List<Double> prices = new ArrayList<>();
        for (WebElement e : MenFragPriceList) {
            String priceText = e.getText().replace("$", "").trim(); // remove $ sign
            
            if (priceText.isEmpty()) {
                continue; // skip if empty
            }

            try {
                prices.add(Double.parseDouble(priceText));
            } catch (NumberFormatException ex) {
                System.out.println("Skipping invalid price: " + priceText);
            }
        }
        
        // make a copy and sort
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        
        return sortedPrices; 
    }
    
    public List<Double> getReverseSortedPrices(WebDriver driver) {
        List<Double> prices = new ArrayList<>();
        for (WebElement e : MenFragPriceList) {
            String priceText = e.getText().replace("$", "").trim(); // remove $ sign
            
            if (priceText.isEmpty()) {
                continue; // skip if empty
            }

            try {
                prices.add(Double.parseDouble(priceText));
            } catch (NumberFormatException ex) {
                System.out.println("Skipping invalid price: " + priceText);
            }
        }
        
        // make a copy and sort
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());;
        
        return sortedPrices; 
    }
    
    public List<Double> getAllElementPricelist(WebDriver driver) {
        List<Double> prices = new ArrayList<>();
        for (WebElement e : MenFragPriceList) {
            String priceText = e.getText().replace("$", "").trim(); // remove $ sign
            if (priceText.isEmpty()) {
                continue; // skip if empty
            }

            try {
                prices.add(Double.parseDouble(priceText));
            } catch (NumberFormatException ex) {
                System.out.println("Skipping invalid price: " + priceText);
            }
        }
        
        return prices; 
    }
    
    
}
