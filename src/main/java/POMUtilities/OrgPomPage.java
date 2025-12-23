package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPomPage 
{
	@FindBy(linkText = "Organizations")
	private WebElement Org_Header;
	
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement Org_plusicon;

	public OrgPomPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public String getOrg_Header() {
		return Org_Header.getText();
	}

	public void getOrg_plusicon() {
		 Org_plusicon.click();
	}
	
	
	
	
}
