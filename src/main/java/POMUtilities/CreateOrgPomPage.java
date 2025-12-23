package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPomPage 
{
@FindBy(xpath="//span[text()='Creating New Organization']")
private WebElement org_header;

@FindBy(name="accountname")
private WebElement Org_nameTF;

@FindBy(id="phone")
private WebElement Phone_no;

@FindBy(name="industry")
private WebElement IndustryDD;

@FindBy(name="accounttype")
private WebElement TypeDD;

@FindBy(xpath="//input[@title='Save [Alt+S]']")
private WebElement Save_btn;

public CreateOrgPomPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

public String getOrg_header() {
	return org_header.getText();
}

public void getOrg_nameTF(String orgname) {
	 Org_nameTF.sendKeys(orgname);
}

public void getPhone_no(String phoneno) {
	Phone_no.sendKeys(phoneno);
}

public WebElement getIndustryDD(String industry) {
	return IndustryDD;
}

public WebElement getTypeDD(String type) {
	return TypeDD;
}

public void getSave_btn() {
	Save_btn.click();
}



}
