package pageobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyOrderHistoryPage extends Basepage{

	public MyOrderHistoryPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[@class='maintext']") WebElement OrderHistoryPageheader;
	@FindBy(xpath="//form[contains(@class,'form-inline')]") WebElement TotalIteminPage;
	//@FindBy(xpath="//select[@id='limit']/following-sibling::text()[normalize-space()]") WebElement TotalIteminPage;
	
	public String getOrderHistoryHeader()
	{
		return OrderHistoryPageheader.getText();
	}
	
	public String getTotalIteminPage()
	{
		String totalOrders = null;
		String fullText =  TotalIteminPage.getText();
		Pattern pattern = Pattern.compile("of\\s+(\\d+)");
		Matcher matcher = pattern.matcher(fullText);
		if (matcher.find()) {
		    totalOrders = matcher.group(1);}
		return totalOrders;
	}
}
