package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOMPage 
{
//declare
	@FindBy(linkText = "vtiger")
	private WebElement Vtiger_header;
	@FindBy(name = "user_name")
	private WebElement usernameTF;
	@FindBy(name = "user_password")
	private WebElement passwordTF;
	@FindBy(id="submitButton")
	private WebElement Login_btn;
	
	
	
	//initailize
	
	public LoginPOMPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//utilize



	public String getVtiger_header() {
		return Vtiger_header.getText();
	}



	public void getUsernameTF(String user) {
		usernameTF.sendKeys(user);
	}



	public void getPasswordTF(String pass) {
		passwordTF.sendKeys(pass);
	}
	public WebElement getUsernameTF() {
		return usernameTF;
	}
	public WebElement getPasswordTF() {
		return passwordTF;
	}
	public WebElement getLogin_btn() {
		return Login_btn;
	}
	
	//businesslogic
	
	public void login(String user,String pass)
	{
		usernameTF.sendKeys(user);
		passwordTF.sendKeys(pass);
		Login_btn.click();
	}
}
