package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_Info_POMPage 
{

@FindBy(xpath = "//span[@class='dvHeaderText']")
private WebElement create_contact_header;

@FindBy(id="dtlview_Last ")
private WebElement verifyLastname;

@FindBy(xpath = "//td[@id='mouseArea_Organization Name']/a")
private WebElement verifyOrgname;

@FindBy(id="dtlview_Support Start Date")
private WebElement verifyStartDate;

@FindBy(id="dtlview_Support End Date")
private WebElement verifyEndDate;

public Contact_Info_POMPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

public WebElement getCreate_contact_header() {
	return create_contact_header;
}

public String getVerifyLastname() {
	return verifyLastname.getText();
}

public String getVerifyOrgname() {
	return verifyOrgname.getText();
}

public String getVerifyStartDate() {
	return verifyStartDate.getText();
}

public String getVerifyEndDate() {
	return verifyEndDate.getText();
}


}
