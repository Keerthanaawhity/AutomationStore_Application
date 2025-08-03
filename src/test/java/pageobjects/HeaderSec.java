package pageobjects;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.Menus.FragrancePage;

public class HeaderSec extends Basepage{
	
	//constructor
	public HeaderSec(WebDriver driver)
	{
		//Invoking parent class constructor
		super(driver);
	}
	
	//Locator
	@FindBy(xpath="//div[contains(text(),'Welcome back')]") WebElement UserOptions;
	@FindBy(xpath= "//ul[@id='main_menu_top']//span[contains(@class,'menu_text')][normalize-space()='Account']")WebElement Account;
	@FindBy(xpath= "//a[normalize-space()='Login or register']")WebElement LorR;
	@FindBy(xpath="//div[contains(@class,'header_block')]//a[contains(@title,'Facebook')][normalize-space()='Facebook']") WebElement facebook;
	@FindBy(xpath="//div[contains(@class,'header_block')]//a[contains(@title,'Twitter')][normalize-space()='Twitter']") WebElement twitter;
	@FindBy(xpath="//div[contains(@class,'header_block')]//a[contains(@title,'Linkedin')][normalize-space()='Linkedin']") WebElement linkedIn;
	@FindBy(xpath="//li[@class='dropdown']//span[@class='menu_text'][normalize-space()='Specials']")WebElement SpecialOffers;
	@FindBy(xpath="//ul[@id='main_menu_top']//li[@data-id='menu_cart']")WebElement Shopcart;
	@FindBy(xpath="//li[@class='dropdown']//span[@class='menu_text'][normalize-space()='Checkout']")WebElement Checkout;
	@FindBy(xpath="//input[@id='filter_keyword']")WebElement Searchbar;
	@FindBy(xpath="//ul[@class='nav language pull-left']//a[@class='dropdown-toggle']")WebElement CurrencyDrp;
	@FindBy(xpath="//ul[@class='nav topcart pull-left']//a[@class='dropdown-toggle']")WebElement CartDrp;
	@FindBy(xpath="//a[@class='active menu_home']")WebElement HomeIcon;
	@FindBy(xpath="//a[normalize-space()='Apparel & accessories']")WebElement ApparelsAccessSec;
	@FindBy(xpath="//a[normalize-space()='Makeup']")WebElement MakeUpSec;
	@FindBy(xpath="//a[normalize-space()='Skincare']")WebElement SkincareSec;
	@FindBy(xpath="//a[contains(@href,'path=49')]")WebElement FragnanceSec;
	@FindBy(xpath="//a[normalize-space()='Men']")WebElement MenSec;
	@FindBy(xpath="//a[normalize-space()='Hair Care']")WebElement HaircareSec;
	@FindBy(xpath="//a[normalize-space()='Books']")WebElement BooksSec;
	@FindBy(xpath="//img[@title='Automation Test Store']")WebElement ApplicationIcon;

	
	//Action methods
	public void ClickAccount()
	{
		Account.click();
	}
	public void ClickLorR()
	{
		LorR.click();
	}
	public void ClickFacebook()
	{
		facebook.click();
	}
	public void ClickTwitter()
	{
		twitter.click();
	}
	public void ClickLinkedIn()
	{
		linkedIn.click();
	}
	public void ClickHome()
	{
		HomeIcon.click();
	}
	
	public void ClickShoppingcart()
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Shopcart);
		Shopcart.click();
	}

	
	//Hover
	public FragrancePage hoverOnFragrance() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(FragnanceSec));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(FragnanceSec).perform();

	    return new FragrancePage(driver);
	}
	
	public UserOptionsPage HoverUserOptions()
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(UserOptions));
		Actions actions = new Actions(driver);
	    actions.moveToElement(UserOptions).perform();
		return new UserOptionsPage(driver);
	}

}
