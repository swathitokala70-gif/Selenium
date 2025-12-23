package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPomPage 
{
	@FindBy(xpath="//span[text()='Creating New Contact']")
	private WebElement CreateCon_header;
	
	@FindBy(name="lastname")
	private WebElement lastnameTF;
	
@FindBy(xpath = "//img[contains(@onclick,'Accounts&action')]")
private WebElement org_plusicon;

@FindBy(name="support_start_date")
private WebElement supportstartdateTF;

@FindBy(name="support_end_date")
private WebElement supportEnddateTF;


@FindBy(id="search_txt")
private WebElement serachTF;


@FindBy(name="search")
private WebElement serachnowBtn;

@FindBy(xpath = "//input[@title='Save [Alt+S]']")
private WebElement saveBtn;



public CreateContactPomPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}


public String getCreateCon_header() {
	return CreateCon_header.getText();
}


public void getLastnameTF(String conname) {
	lastnameTF.sendKeys(conname);;
}


public void getOrg_plusicon() {
	org_plusicon.click();;
}


public void getSupportstartdateTF(String startdate) {
	supportstartdateTF.clear();
	supportstartdateTF.sendKeys(startdate);
}


public void getSupportEnddateTF(String enddate) {
	supportEnddateTF.clear();
	supportEnddateTF.sendKeys(enddate);
}


public void getSerachTF(String name) {
	serachTF.click();
}


public void getSerachnowBtn() {
	serachnowBtn.click();
}


public void getSaveBtn() {
	saveBtn.click();;
}


}



