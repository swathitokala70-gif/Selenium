package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.webdriverUtility;

public class HomePomPage 
{
//declare
	@FindBy(partialLinkText = "Home")
	private WebElement homeheader;
	
	@FindBy(linkText = "Organizations")
	private WebElement Org_tab;
	
	@FindBy(linkText = "Contacts")
	private WebElement Contact_tab;
	
	@FindBy(xpath = "//img[contains(@src,'user')]")
	private WebElement admin;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signout;

	public HomePomPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}

	public String getHomeheader() {
		return homeheader.getText();
	}

	public void getOrg_tab() {
		 Org_tab.click();
	}

	public void getContact_tab() {
		Contact_tab.click();
	}

	public WebElement getAdmin() {
		return admin;
	}

	public void getSignout() {
		signout.click();
	}
	
	//business logic for logout
	public void logout(WebDriver driver, webdriverUtility wutil)
	{
		wutil.UsingMousehover(driver, admin);
		signout.click();
		
	}
}
