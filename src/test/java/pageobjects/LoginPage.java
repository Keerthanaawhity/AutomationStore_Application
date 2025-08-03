package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Basepage{

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locators
	@FindBy(xpath="//input[@id='loginFrm_loginname']") WebElement loginbox;
	@FindBy(xpath="//input[@id='loginFrm_password']") WebElement pwdbox;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement loginbtn;
	@FindBy(xpath="//button[normalize-space()='Continue']") WebElement Registerbtn;
	
	
	//Action Methods
	public void SetLoginName(String loginname) {
		loginbox.sendKeys(loginname);
	}
	
	public void Setpwd(String passwd) {
		pwdbox.sendKeys(passwd);
	}
	
	public void clicklogin() {
		loginbtn.click();
	}
	
	public void ClickRegister()
	{
		Registerbtn.click();
	}
}

