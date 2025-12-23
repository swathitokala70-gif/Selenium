package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPomPage {
	
		@FindBy(linkText = "Contacts")
		private WebElement Con_Header;

		@FindBy(xpath = "//img[@title='Create Contact...']")
		private WebElement Con_plusicon;


		public ContactPomPage(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
		}

		public String getCon_Header() {
			return Con_Header.getText();
		}

		public void getCon_plusicon() {
			Con_plusicon.click();
		}
}
