package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends Basepage{

		public WishListPage(WebDriver driver) {
			super(driver);
		}
		
		@FindBy(xpath="	//td[@class='align_left']//a") WebElement CartProdName;
		@FindBy(xpath="//span[@class='maintext']") WebElement MyWishListText;
		@FindBy(xpath="//tbody//tr[2]//td[3]") WebElement WishListProdModel;
		@FindBy(xpath="//tbody//tr[2]//td[4]") WebElement WishListProdUnitPrice;
		@FindBy(xpath="//tbody//tr[2]//td[5]") WebElement WishListAddedDate;
		@FindBy(xpath="//tbody//tr[2]//td[last()]//a[1]") WebElement WishListAddToCart;
		@FindBy(xpath="//tbody//tr[2]//td[last()]//a[2]") WebElement WishListProdDelete;
		@FindBy(xpath="//div[contains(@class,'wishlist product-list')]//div//a[1]") WebElement ContShoppingBtn;
		@FindBy(xpath="//div[contains(@class,'wishlist product-list')]//div//a[2]") WebElement ViewCartBtn;
		//@FindBy(xpath="//tr[starts-with(@class, 'wishlist_')]") WebElement TotalProdInWishlistPage;
		
		public String getCartProdname()
		{
			return CartProdName.getText().trim();
		}
		
		public String getWishListHeader()
		{
			return MyWishListText.getText();
		}
		
		public String getWishListProductPrice()
		{
			return WishListProdUnitPrice.getText();
		}
		public String getWishListAddedDate()
		{
			return WishListAddedDate.getText();
		}

		public void ClickWishlistAddtoCart()
		{
			WishListAddToCart.click();
		}
		
		public void ClickWishlistProdDelete()
		{
			WishListProdDelete.click();
		}
		
		public void ClickContinueShoppingBtn()
		{
			ContShoppingBtn.click();
		}
		
		public void ClickViewCartBtn()
		{
			ViewCartBtn.click();
		}
		
		public int getTotalProductsinWishlistPage()
		{
			List ProductRowSize =  driver.findElements(By.xpath("//tr[starts-with(@class, 'wishlist_')]"));
			return ProductRowSize.size();
		}
		
		public void clickAddToCartByProductNameinWishList(String productName) {
		    String xpath = String.format("//a[text()='%s']/parent::td/following-sibling::td[last()]//a[@class='btn btn-sm btn-default btn-primary']", productName);
		    driver.findElement(By.xpath(xpath)).click();
		}

		public void clickDeleteByProductNameinWishList(String productName) {
		    String xpath = String.format("//a[text()='%s']/parent::td/following-sibling::td[last()]//a[@class='btn btn-sm btn-default btn-remove']", productName);
		    driver.findElement(By.xpath(xpath)).click();
		}
		
		public boolean isProductPresentInWishlist(String productName) {
		    List<WebElement> elements = driver.findElements(By.xpath("//a[normalize-space(text())='" + productName + "']"));
		    return !elements.isEmpty(); 
		}

}

