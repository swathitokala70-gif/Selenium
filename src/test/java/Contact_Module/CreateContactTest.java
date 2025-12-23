package Contact_Module;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClassUtility.BaseClass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.javaUtility;
import POMUtilities.ContactPomPage;
import POMUtilities.Contact_Info_POMPage;
import POMUtilities.CreateContactPomPage;
import POMUtilities.HomePomPage;

public class CreateContactTest extends BaseClass {
	@Test
	public void CreateConTest() throws InterruptedException, EncryptedDocumentException, IOException {
		// create object for property utility class for accessing data
		javaUtility jutil = new javaUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();

		// get the random no

		int randomint = jutil.generateRandomNo();

		// fetch the data from excel utility

		String conname = eutil.FetchDataFromExcel("contact", 1, 2) + randomint;

		// validate homepage using soft asser

		SoftAssert soft = new SoftAssert();
		HomePomPage home = new HomePomPage(driver);
		String acthomeheader = home.getHomeheader();
		soft.assertEquals(acthomeheader, "Home");

		// click on contacts tab
		// wutil.driver.findElement(By.linkText("Contacts")).click();

		home.getContact_tab();

		ContactPomPage c = new ContactPomPage(driver);

		// Identify plus icon
		// wutil.driver.findElement(By.xpath("//img[@title='Create
		// Contact...']")).click();
		c.getCon_plusicon();

		// identify the last name TF and enter contact name
		// wutil.driver.findElement(By.name("lastname")).sendKeys(conname);

		CreateContactPomPage cretaecon = new CreateContactPomPage(driver);
		cretaecon.getLastnameTF(conname);

		// identify the save btn and click
		// wutil.driver.findElement(By.xpath("//input[@type='submit']")).click();
		cretaecon.getSaveBtn();

		Contact_Info_POMPage coninfo = new Contact_Info_POMPage(driver);

		// validate the contact name
		String validatecon = coninfo.getVerifyLastname();
		if (validatecon.contains(conname)) {
			System.out.println("cct pass");
		} else {
			System.out.println("cct Fail");
		}

		// Identify the contact tab and click on it
		// wutil.driver.findElement(By.linkText("Contacts")).click();

		home.getContact_tab();

		// Identify the delete link and click on it
		driver.findElement(By.xpath("//a[text()='" + conname + "']/../..//a[text()='del']")).click();

		Thread.sleep(3000);

		// handle alert popup
		wutil.switchto_Alert(driver);
	
		soft.assertAll();

	}
}
