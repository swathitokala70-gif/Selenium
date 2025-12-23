package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrginfoPomPage 
{
	@FindBy(xpath="//span[contains(text(),'Organization Information')]")
	private WebElement orginfo_header;

	@FindBy(id="mouseArea_Organization Name")
	private WebElement Org_nameTF;

	@FindBy(id="mouseArea_Phone")
	private WebElement Phone_no;

	@FindBy(id="indusdtlview_Industry")
	private WebElement IndustryDD;

	@FindBy(id="dtlview_Type")
	private WebElement TypeDD;

	public OrginfoPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrginfo_header() {
		return orginfo_header;
	}

	public WebElement getOrg_nameTF() {
		return Org_nameTF;
	}

	public WebElement getPhone_no() {
		return Phone_no;
	}

	public WebElement getIndustryDD() {
		return IndustryDD;
	}

	public WebElement getTypeDD() {
		return TypeDD;
	}
	
	
}


