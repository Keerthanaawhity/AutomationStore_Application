package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Basepage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath="//div[@id='maincontainer']//span[@class='maintext']") WebElement MyAccText;
	@FindBy(xpath="//div[@id='maincontainer']//span[@class='subtext']") WebElement AccountHolderName;
	@FindBy(xpath="//a[@title='Edit account details']") WebElement EditAccDetailsBtn;
	@FindBy(xpath="//a[@title='Change password']") WebElement ChangePasswordBtn;
	@FindBy(xpath="//a[@title='Manage Address Book']") WebElement ManageAddressBookBtn;
	@FindBy(xpath="//a[@title='My wish list']") WebElement MyWishlistBtn;
	@FindBy(xpath="//ul[@class='nav-dash']//a[@title='Order history']") WebElement OrderHistoryBtn;
	@FindBy(xpath="//ul[@class='nav-dash']//a[@title='Transaction history']") WebElement TrasactionHistoryBtn;
	@FindBy(xpath="//ul[@class='nav-dash']//a[@title='Downloads']") WebElement DownloadsBtn;
	@FindBy(xpath="//a[@title='Notifications']") WebElement NotificationsBtn;
	@FindBy(xpath="//a[@data-original-title='Manage Address Book']//span") WebElement NoOfAddressBadge;
	@FindBy(xpath="//a[@data-original-title='My wish list']//span") WebElement NoOfWishlistBadge;
	@FindBy(xpath="//a[@data-original-title='Order history']//span") WebElement NoOfOrdersBadge;
	@FindBy(xpath="//a[@data-original-title='Transaction history']//span") WebElement TransHistoryBadge;
	@FindBy(xpath="//a[@data-original-title='Downloads']//span") WebElement DownloadsBadge ;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-ocean clearfix']") WebElement AddressBookTile;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-ocean clearfix']//div[@class='dash-tile-text']") WebElement NoOfAddressinTile;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-flower clearfix']") WebElement OrderHistoryTile;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-flower clearfix']//div[@class='dash-tile-text']") WebElement NoOfOrdersinTile;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-oil clearfix']") WebElement DownloadsTile;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-oil clearfix']//div[@class='dash-tile-text']") WebElement NoofDownloadsinTile;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-balloon clearfix']") WebElement TransactionHistoryTile;
	@FindBy(xpath="//div[@class='dash-tile dash-tile-balloon clearfix']//div[@class='dash-tile-text']") WebElement NoofTransactionsinTile;

	
	public boolean isAccountTextVisible()
	{
		return MyAccText.isDisplayed();		
	}
	
	public String getAccountHolderName()
	{
		return AccountHolderName.getText();		
	}
	
	public void ClickChangePasswordBtn()
	{
		ChangePasswordBtn.click();
	}
	
	public void ClickManageAddressBookBtn()
	{
		ManageAddressBookBtn.click();
	}
	
	public void ClickEditAccountDEtailsBtn()
	{
		EditAccDetailsBtn.click();
	}
	
	public void ClickMyWishlistBtn()
	{
		MyWishlistBtn.click();
	}
	
	public void ClickOrderHistoryBtn()
	{
		OrderHistoryBtn.click();
	}
	
	public void ClickTrasactionHistoryBtn()
	{
		TrasactionHistoryBtn.click();
	}
	
	public void ClickDownloadsBtn()
	{
		DownloadsBtn.click();
	}
	
	public void ClickNotificationsBtn()
	{
		NotificationsBtn.click();
	}
	
	public String getNoOfAddressBadge()
	{
		return NoOfAddressBadge.getText();		
	}
	
	public String getNoOfWishlistBadge()
	{
		return NoOfWishlistBadge.getText();		
	}
	
	public String getNoOfOrdersBadge()
	{
		return NoOfOrdersBadge.getText();		
	}
	
	public String getTransHistoryBadge()
	{
		return TransHistoryBadge.getText();		
	}
	
	public String getDownloadsBadge()
	{
		return DownloadsBadge.getText();		
	}
	
	public boolean isAccountTextClickable()
	{
		return AddressBookTile.isDisplayed() && AddressBookTile.isEnabled();		
	}
	
	public boolean isOrderHistoryTileClickable()
	{
		return OrderHistoryTile.isDisplayed() && OrderHistoryTile.isEnabled();		
	}
	
	public boolean isDownloadsTileClickable()
	{
		return DownloadsTile.isDisplayed() && DownloadsTile.isEnabled();		
	}
	
	public boolean isTransactionHistoryTileClickable()
	{
		return TransactionHistoryTile.isDisplayed() && TransactionHistoryTile.isEnabled();		
	}
	
	public String getNoOfAddressinTile()
	{
		return NoOfAddressinTile.getText();		
	}
	
	public String getNoOfOrdersinTile()
	{
		return NoOfOrdersinTile.getText();		
	}
	
	public String getNoofDownloadsinTile()
	{
		return NoofDownloadsinTile.getText();		
	}
	
	public String getNoofTransactionsinTile()
	{
		return NoofTransactionsinTile.getText();		
	}
}
