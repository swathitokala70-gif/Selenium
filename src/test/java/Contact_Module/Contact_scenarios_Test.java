package Contact_Module;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClassUtility.BaseClass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyUtility;
import GenericUtilities.javaUtility;
import GenericUtilities.webdriverUtility;
import POMUtilities.ContactPomPage;
import POMUtilities.Contact_Info_POMPage;
import POMUtilities.CreateContactPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPOMPage;

//changes for git

@Listeners(GenericUtilities.ListernersUtility.class)
public class Contact_scenarios_Test extends BaseClass {
	@Test(retryAnalyzer = GenericUtilities.RetryAnalyzerUtility.class)
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
		// driver.findElement(By.linkText("Contacts")).click();

		home.getContact_tab();

		ContactPomPage c = new ContactPomPage(driver);

		// Identify plus icon
		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		c.getCon_plusicon();

		// identify the last name TF and enter contact name
		// driver.findElement(By.name("lastname")).sendKeys(conname);

		CreateContactPomPage cretaecon = new CreateContactPomPage(driver);
		cretaecon.getLastnameTF(conname);

		// identify the save btn and click
		// driver.findElement(By.xpath("//input[@type='submit']")).click();
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
		// driver.findElement(By.linkText("Contacts")).click();

		home.getContact_tab();

		// Identify the delete link and click on it
		driver.findElement(By.xpath("//a[text()='" + conname + "']/../..//a[text()='del']")).click();

		Thread.sleep(3000);

		// handle alert popup
		wutil.switchto_Alert(driver);
		// driver.switchTo().alert().accept();

		// mouse hove on admin icon
		// WebElement admin =
		// driver.findElement(By.xpath("//img[contains(@src,'user')]"));

		// wutil.UsingMousehover(admin);
		// Actions act=new Actions(driver);
		// act.moveToElement(admin).perform();

		// click on signout
		// driver.findElement(By.linkText("Sign Out")).click();
		WebElement admin = home.getAdmin();
		home.logout(driver, wutil);

		// close the browser
		driver.quit();

		soft.assertAll();

	}

	@Test(retryAnalyzer = GenericUtilities.RetryAnalyzerUtility.class)
	public void createconOrg() throws IOException {
		// create object for property utility class for accessing data
		PropertyUtility putil = new PropertyUtility();
		String browser = putil.FetchProertyData("browser");
		String url = putil.FetchProertyData("url");
		String username = putil.FetchProertyData("username");
		String password = putil.FetchProertyData("password");
		String timeouts = putil.FetchProertyData("timeouts");

		// get the random no
		javaUtility jutil = new javaUtility();
		int randomint = jutil.generateRandomNo();

		// fetch the data from excel utility
		ExcelFileUtility eutil = new ExcelFileUtility();
		String conname = eutil.FetchDataFromExcel("contact", 4, 2) + randomint;
		String orgname = eutil.FetchDataFromExcel("contact", 4, 3) + randomint;
		// String browser = "Chrome";
		// String orgname="job";
		// String conname= "abcd";

		// Launch the browser
		webdriverUtility wutil = new webdriverUtility();

		LoginPOMPage l = new LoginPOMPage(driver);
		// l.login(username, password);

		// validate homepage using soft asser

		SoftAssert soft = new SoftAssert();
		HomePomPage home = new HomePomPage(driver);
		String acthomeheader = home.getHomeheader();
		soft.assertEquals(acthomeheader, "Home");

		// Identify org tab and click on it
		driver.findElement(By.linkText("Organizations")).click();

		// Identify plus icon and click on it
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Identify org TF and enter orgname
		driver.findElement(By.name("accountname")).sendKeys(orgname);

		// Identify save and click on it
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Validate org name
		WebElement verifyorg = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));

		if (verifyorg.getText().contains(orgname)) {
			System.out.println("create org Test pass");
		} else {
			System.out.println("Create org Test fail");
		}

		// Identify contact tab and click on it

		home.getContact_tab();
		// driver.findElement(By.linkText("Contacts")).click();

		// Identify plus icon and click on it
		ContactPomPage cont = new ContactPomPage(driver);
		cont.getCon_plusicon();

		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Identify the last name and pass the text
		CreateContactPomPage creatcont = new CreateContactPomPage(driver);
		creatcont.getLastnameTF(conname);

		// driver.findElement(By.name("lastname")).sendKeys(conname);

		// click on org plus icon on contact list
		creatcont.getOrg_plusicon();
		// driver.findElement(By.xpath("//img[@title='Select']")).click();

		// Fetch the parent window id
		// String pwid = driver.getWindowHandle();

		String pwid = wutil.fetchTheParentWindowId(driver);

		// Switch the driver control to child window

//		Set<String> wids = driver.getWindowHandles();
//		for(String s:wids) {
//			driver.switchTo().window(s);
//			if(driver.getCurrentUrl().contains("Accounts&action")) {

		wutil.swithtoWindowIdUsing_url(driver, "Accounts&action");

		// search for the org name
		creatcont.getSerachTF(orgname);
		creatcont.getSerachnowBtn();

		// driver.findElement(By.id("search_txt")).sendKeys(orgname);
		// driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		// switch back to parent window
		// driver.switchTo().window(pwid);
		wutil.swithbacktoParent(driver, pwid);

		// identify save btn and click on it

		creatcont.getSaveBtn();
		// driver.findElement(By.xpath("//input[@type='submit']")).click();

		// validate contact name
		// WebElement varify_name =
		// driver.findElement(By.xpath("//span[contains(text(),'Contact
		// Information')]"));
		Contact_Info_POMPage continfo = new Contact_Info_POMPage(driver);
		WebElement varify_name = continfo.getCreate_contact_header();
		if (varify_name.getText().contains(conname)) {
			System.out.println("create contact with org test pass");
		} else {
			System.out.println("create contact with org test fail");
		}
		// identify contact tab and click
		home.getContact_tab();
		// driver.findElement(By.linkText("Contacts")).click();

		// identify delete link and click on it
		driver.findElement(
				By.xpath("//a[text()='" + conname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		// handle alert popup
		driver.switchTo().alert().accept();

		// identify oragnization tab and click
		home.getOrg_tab();
		// driver.findElement(By.linkText("Organizations")).click();

		// identify delete and click on it
		driver.findElement(By.xpath(
				"//a[text()='" + orgname + "' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']"))
				.click();

		// handle alert popup
		driver.switchTo().alert().accept();

		// click on sign out button

		home.logout(driver, wutil);

//				//mouse hover
//				WebElement admin = driver.findElement(By.xpath("//img[contains(@src,'user')]"));
//				wutil.UsingMousehover(driver, admin);
//
//				//click on sign out button
//				driver.findElement(By.linkText("Sign Out")).click();
//				
//				//close the browser
//				driver.quit();

		soft.assertAll();

	}

	@Test(retryAnalyzer = GenericUtilities.RetryAnalyzerUtility.class)
	public void Createconwithsupportdate() throws IOException {
		// create object for property utility class for accessing data

		// get the random no
		javaUtility jutil = new javaUtility();
		int randomint = jutil.generateRandomNo();

		// fetch the data from excel utility
		ExcelFileUtility eutil = new ExcelFileUtility();
		String conname = eutil.FetchDataFromExcel("contact", 7, 2) + randomint;

		webdriverUtility wutil = new webdriverUtility();

		SoftAssert soft = new SoftAssert();
		HomePomPage home = new HomePomPage(driver);
		String acthomeheader = home.getHomeheader();
		soft.assertEquals(acthomeheader, "Home");

		// Identify contact tab and click on it
		home.getContact_tab();
		// driver.findElement(By.linkText("Contacts")).click();

		// Identify plus icon and click on it
		CreateContactPomPage crtcont = new CreateContactPomPage(driver);

		Contact_Info_POMPage coninfo = new Contact_Info_POMPage(driver);

		ContactPomPage cont = new ContactPomPage(driver);

		cont.getCon_plusicon();
		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Identify the last name and pass the text
		crtcont.getLastnameTF(conname);
		// driver.findElement(By.name("lastname")).sendKeys(conname);

		// Generate Todays date
		Date dobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startdate = sim.format(dobj);
		System.out.println(startdate);

		// Identify startdate TF and pass the date
		WebElement startdateTF = driver.findElement(By.name("support_start_date"));
		startdateTF.clear();
		startdateTF.sendKeys(startdate);

		// Generate date after 30 days
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String enddate = sim.format(cal.getTime());
		System.out.println(enddate);

		// Identify End date TF and pass the date
		WebElement enddateTf = driver.findElement(By.name("support_end_date"));
		enddateTf.clear();
		enddateTf.sendKeys(enddate);

		// click the save button and click on it
		crtcont.getSaveBtn();
		// driver.findElement(By.xpath("//input[@type='submit']")).click();

		// Validate contact name

		// WebElement verify_name =
		// driver.findElement(By.xpath("//span[contains(text(),'Contact
		// Information')]"));
		WebElement verify_name = coninfo.getCreate_contact_header();
		if (verify_name.getText().contains(conname)) {
			System.out.println("Test pass");
		} else {
			System.out.println("Test fail");
		}

		// Validate the start date
		WebElement start_date = driver.findElement(By.id("dtlview_Support Start Date"));
		if (start_date.getText().contains(startdate)) {
			System.out.println("start date test pass");
		} else {
			System.out.println("start date test fail");
		}

		// validate the End date
		WebElement end_date = driver.findElement(By.id("dtlview_Support End Date"));
		if (end_date.getText().contains(enddate)) {
			System.out.println("end date test pass");
		} else {
			System.out.println("end date test fail");
		}

		// Identify contact tab and click on it
		home.getContact_tab();
		// driver.findElement(By.linkText("Contacts")).click();

		// Identify delete link and click on it
		driver.findElement(By.xpath(
				"//a[text()='" + conname + "' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']"))
				.click();

		// handle the alert popup
		wutil.switchto_Alert(driver);

		// click on sign out

		home.logout(driver, wutil);
//		
//		// mouse over on admin button
//		WebElement over = driver.findElement(By.xpath("//img[contains(@src,'user')]"));
//		wutil.UsingMousehover(driver, over);
//
//		// click on sign out
//		driver.findElement(By.linkText("Sign Out")).click();
//
//		// close the browser
//		driver.quit();
		soft.assertAll();

	}

}
