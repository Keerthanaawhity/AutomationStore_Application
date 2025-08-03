package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends Basepage{
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='AccountFrm_firstname']") WebElement Firstname;
	@FindBy(xpath="//input[@id='AccountFrm_lastname']") WebElement Lastname;
	@FindBy(xpath="//input[@id='AccountFrm_email']") WebElement Email;
	@FindBy(xpath="//input[@id='AccountFrm_telephone']") WebElement Telephone;
	@FindBy(xpath="//input[@id='AccountFrm_fax']") WebElement Fax;
	@FindBy(xpath="//input[@id='AccountFrm_company']") WebElement company;
	@FindBy(xpath="//input[@id='AccountFrm_address_1']") WebElement addr1;
	@FindBy(xpath="//input[@id='AccountFrm_address_2']") WebElement addr2;
	@FindBy(xpath="//input[@id='AccountFrm_city']") WebElement city;
	@FindBy(xpath="//select[@id='AccountFrm_zone_id']") WebElement zoneid;
	@FindBy(xpath="//input[@id='AccountFrm_postcode']") WebElement postcode;
	@FindBy(xpath="//select[@id='AccountFrm_country_id']") WebElement countryid;
	@FindBy(xpath="//input[@id='AccountFrm_loginname']") WebElement loginname;
	@FindBy(xpath="//input[@id='AccountFrm_password']") WebElement Password;
	@FindBy(xpath="//input[@id='AccountFrm_confirm']") WebElement ConfPwd;
	@FindBy(xpath="//input[@id='AccountFrm_agree']") WebElement Agree;
	@FindBy(xpath="//button[normalize-space()='Continue']") WebElement Contbtn;
	@FindBy(xpath="//span[@class='maintext']") WebElement msgconf;
	@FindBy(xpath="//span[@class='subtext']") WebElement accountname;
	@FindBy(xpath="////span[contains(text(),'Login name must be alphanumeric only and between 5')]") WebElement loginerrmsg;
	@FindBy(xpath="//span[normalize-space()='Password must be between 4 and 20 characters!']") WebElement Pwderrmsg;
	@FindBy(xpath="//span[normalize-space()='First Name must be between 1 and 32 characters!']") WebElement Firstnameerrmsg;
	@FindBy(xpath="//span[normalize-space()='Last Name must be between 1 and 32 characters!']") WebElement Lastnameerrmsg;
	@FindBy(xpath="//span[normalize-space()='Email Address does not appear to be valid!']") WebElement Emailerrmsg;
	@FindBy(xpath="//span[normalize-space()='Address 1 must be between 3 and 128 characters!']") WebElement Addr1errmsg;
	@FindBy(xpath="//span[normalize-space()='City must be between 3 and 128 characters!']") WebElement Cityerrmsg;
	@FindBy(xpath="//span[normalize-space()='Please select a region / state!']") WebElement Zoneerrmsg;
	@FindBy(xpath="//span[contains(text(),'Zip/postal code must be between 3 and 10 character')]") WebElement Zipcodeerrmsg;
	@FindBy(xpath="//div[@class='alert alert-error alert-danger']") WebElement Privacyerrmsg;
	
	
	
	
	public void setFirstName(String fname)
	{
		Firstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		Lastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		Email.sendKeys(email);
	}
	
	public void setTelephone(String phone)
	{
		Telephone.sendKeys(phone);
	}
	
	public void setFax(String fax)
	{
		Fax.sendKeys(fax);
	}
	
	public void setCompany(String cmpny)
	{
		company.sendKeys(cmpny);
	}
	
	public void setAddress1(String address1)
	{
		addr1.sendKeys(address1);
	}
	
	public void setAddress2(String address2)
	{
		addr2.sendKeys(address2);
	}
	
	public void setCity(String City)
	{
		city.sendKeys(City);
	}
	
	public void setZoneid(String zone)
	{
		zoneid.click();
		WebElement statelist = driver.findElement(By.name("zone_id"));
		Select statedrp = new Select(statelist);
		statedrp.selectByContainsVisibleText(zone);
	}
	
	public void setPostcode(String post)
	{
		postcode.sendKeys(post);
	}
	
	public void setCountry(String country)
	{
		countryid.click();
		WebElement countrylist = driver.findElement(By.name("country_id"));
		Select countrydrp = new Select(countrylist);
		countrydrp.selectByContainsVisibleText(country);
	}
	
	public void setLoginName(String loginName)
	{
		loginname.sendKeys(loginName);
	}
    public boolean isValidLoginName(String loginName) {
        if (loginName == null || loginName.isEmpty()) {
            return false;
        }
        String regex = "^[a-zA-Z0-9]{5,64}$";
        return loginName.matches(regex);
    }
	
	public void setPassword(String pwd)
	{
		Password.sendKeys(pwd);
	}
	
	public void setConfirmpwd(String cpwd)
	{
		ConfPwd.sendKeys(cpwd);
	}
	
	public void setAgree()
	{
		Agree.click();
	}
	
	public void setContinue()
	{
		Contbtn.click();
	}

	public String getConfmsg()
	{
	try {
		return (msgconf.getText());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return(e.getMessage());
		}
	}
	
	public String getacctname()
	{
	try {
		return (accountname.getText());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return(e.getMessage());
		}
	}

	public String getLoginNameErrorMessage() {
		try {
			return(loginerrmsg.getText());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return(e.getMessage());
			}
	}
	
	public String getFirstNameError() {
	    return Firstnameerrmsg.getText();
	}
	
	public String getLastNameError() {
	    return Lastnameerrmsg.getText();
	}

	public String getEmailError() {
	    return Emailerrmsg.getText();
	}
	
	public String getpasswordError() {
	    return Pwderrmsg.getText();
	}
	
	public String getAddr1Error() {
	    return Addr1errmsg.getText();
	}
	
	public String getCityError() {
	    return Cityerrmsg.getText();
	}
	
	public String getZoneError() {
	    return Zoneerrmsg.getText();
	}
	
	public String getPrivacyError() {
	    return Privacyerrmsg.getText();
	}
	
	public String getZipcodeError() {
	    return Zipcodeerrmsg.getText();
	}
	
}
