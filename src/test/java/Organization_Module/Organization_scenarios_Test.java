package Organization_Module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClassUtility.BaseClass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyUtility;
import GenericUtilities.javaUtility;
import GenericUtilities.webdriverUtility;
import POMUtilities.CreateOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPOMPage;
import POMUtilities.OrgPomPage;
import POMUtilities.OrginfoPomPage;

public class Organization_scenarios_Test extends BaseClass {

	@Test
	public void Createorg() throws IOException {

		// get the random no
		javaUtility jutil = new javaUtility();
		int randomint = jutil.generateRandomNo();

		// fetch the data from excel utility
		ExcelFileUtility eutil = new ExcelFileUtility();
		String Orgname = eutil.FetchDataFromExcel("Organization", 1, 2) + randomint;

		webdriverUtility wutil = new webdriverUtility();

		// validate homepage using soft asser

		SoftAssert soft = new SoftAssert();
		HomePomPage home = new HomePomPage(driver);
		String acthomeheader = home.getHomeheader();
		soft.assertEquals(acthomeheader, "Home");

		// Identify the org and Click on organization tab
		home.getOrg_tab();

		// Identify the create org plus icon and click on it
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrg_plusicon();
		CreateOrgPomPage createorg = new CreateOrgPomPage(driver);

		// Identify the org name TF and enter text

		createorg.getOrg_nameTF(Orgname);
		// Identify save button and click on save button
		createorg.getSave_btn();

		// validate the org
		// WebElement orgnamevalidate =
		// driver.findElement(By.xpath("//span[contains(text(),'Organization
		// Information')]"));

		OrginfoPomPage orginfo = new OrginfoPomPage(driver);

		WebElement orgnamevalidate = orginfo.getOrginfo_header();

		//
//		if(orgnamevalidate.getText().contains(Orgname))
//		{
//			System.out.println("org test pass");	
//		}
//		else
//		{
//			System.out.println("org test fail");
//		}

		// using hard assert
		Assert.assertEquals(orgnamevalidate, Orgname, "verified org name fialed");

		// Identify the org and Click on organization tab
		// driver.findElement(By.linkText("Organizations")).click();

		home.getOrg_tab();

		// delete the organization
		driver.findElement(By.xpath(
				"//a[text()='" + Orgname + "' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']"))
				.click();

		// handle alert popup
		driver.switchTo().alert().accept();

		// mouse hove on admin icon
		// WebElement admin =
		// driver.findElement(By.xpath("//img[contains(@src,'user')]"));

//		WebElement admin=home.getAdmin();
//
//		wutil.UsingMousehover(driver, admin);
//
//		// click on signout
//		driver.findElement(By.linkText("Sign Out")).click();

		home.logout(driver, wutil);
		// close the browser

		eutil.closeExcelFile();

		soft.assertAll();
	}

	@Test
	public void CreateOrgWithPhoneNum() throws EncryptedDocumentException, IOException {

		// get the random no
		javaUtility jutil = new javaUtility();
		int randomint = jutil.generateRandomNo();

		// fetch the data from excel utility
		ExcelFileUtility eutil = new ExcelFileUtility();
		String Orgname = eutil.FetchDataFromExcel("Organization", 7, 2) + randomint;
		String phno = eutil.FetchDataFromExcel("Organization", 7, 3);

		// String browser = "Chrome";
		// String Orgname="tyss";
		// String phno= "9676485548";

		webdriverUtility wutil = new webdriverUtility();
		// Launch the browser

//				// Identify the username
//				driver.findElement(By.name("user_name")).sendKeys(username);
//				
//				//Identify the password
//				driver.findElement(By.name("user_password")).sendKeys(password);
//				
//				//Identify the login button and click on it
//				driver.findElement(By.id("submitButton")).click();

//validate homepage using soft asser

		SoftAssert soft = new SoftAssert();
		HomePomPage home = new HomePomPage(driver);
		String acthomeheader = home.getHomeheader();
		soft.assertEquals(acthomeheader, "Home");

		// Identify org tab and click on it
		// driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();

		// Identify plus icon and click on it
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();
		CreateOrgPomPage creteorg = new CreateOrgPomPage(driver);
		OrgPomPage orgpom = new OrgPomPage(driver);

		orgpom.getOrg_plusicon();

		// Identify org TF and enter orgname
		// driver.findElement(By.name("accountname")).sendKeys(Orgname);

		creteorg.getOrg_nameTF(Orgname);
		// Identify phno TF and pass the phno
		// driver.findElement(By.id("phone")).sendKeys(phno);
		creteorg.getPhone_no(phno);

		// Identify save and click on it
		// driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		creteorg.getSave_btn();

		// Validate org name
		// WebElement verifyorg =
		// driver.findElement(By.xpath("//span[contains(text(),'Organization
		// Information')]"));
		OrginfoPomPage orginfo = new OrginfoPomPage(driver);
		WebElement verifyorg = orginfo.getOrginfo_header();

		if (verifyorg.getText().contains(Orgname)) {
			System.out.println("create org Test pass");
		} else {
			System.out.println("Create org Test fail");

		}

		// Validate phno
		WebElement verifyphno = driver.findElement(By.id("dtlview_Phone"));

		if (verifyphno.getText().contains(phno)) {
			System.out.println("Test pass");
		} else {
			System.out.println("Test fail");

		}

		// click on orgtab
		// driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();

		// Identify delete link and click on it
		driver.findElement(By.xpath(
				"//a[text()='" + Orgname + "' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']"))
				.click();

		// Handle the alert popup
		// driver.switchTo().alert();

		// handle the alert popup
		driver.switchTo().alert().accept();

		// mouse over
		// WebElement over =
		// driver.findElement(By.xpath("//img[contains(@src,'user')]"));
		// wutil.UsingMousehover(driver, over);

		// click sign out
		// driver.findElement(By.linkText("Sign Out")).click();

		home.logout(driver, wutil);

		// close the browser
		driver.quit();
		eutil.closeExcelFile();

		soft.assertAll();

	}

	@Test
	public void createorgwithind() throws IOException {

		// get the random no
		javaUtility jutil = new javaUtility();
		int randomint = jutil.generateRandomNo();

		// fetch the data from excel utility
		ExcelFileUtility eutil = new ExcelFileUtility();
		String Orgname = eutil.FetchDataFromExcel("Organization", 4, 2) + randomint;
		String industry = eutil.FetchDataFromExcel("Organization", 4, 3);
		String type = eutil.FetchDataFromExcel("Organization", 4, 4);
		System.out.println(Orgname);

		// String browser = "Chrome";
		// String Orgname = "xyz";

		webdriverUtility wutil = new webdriverUtility();

//		// Identify the username
//		driver.findElement(By.name("user_name")).sendKeys(username);
//
//		// Identify the password
//		driver.findElement(By.name("user_password")).sendKeys(password);
//
//		// Identify the login button and click on it
//		driver.findElement(By.id("submitButton")).click();

		//LoginPOMPage l = new LoginPOMPage(driver);
		//l.login(username, password);

		// validate homepage using soft asser

		SoftAssert soft = new SoftAssert();
		HomePomPage home = new HomePomPage(driver);
		String acthomeheader = home.getHomeheader();
		soft.assertEquals(acthomeheader, "Home");

		// Identify org tab and click on it
		home.getOrg_tab();

		// driver.findElement(By.linkText("Organizations")).click();

		// Identify plus icon and click on it
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrg_plusicon();
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();

		// Identify org TF and enter orgname
		CreateOrgPomPage createorg = new CreateOrgPomPage(driver);
		createorg.getOrg_nameTF(Orgname);

		// driver.findElement(By.name("accountname")).sendKeys(Orgname);

		// select the indusrty dropdown
		WebElement industrydd = createorg.getIndustryDD(industry);
		// WebElement industrydd = driver.findElement(By.name("industry"));

		// create select class
		// Select s = new Select(industrydd);

		// select by option using index
		// s.selectByValue(industry);

		wutil.selectDDByValue(industrydd, industry);

		// select type dropdown
		WebElement typedd = createorg.getTypeDD(type);
		// WebElement typedd = driver.findElement(By.name("accounttype"));

		// create object for select
//		Select s2 = new Select(typedd);
//		s2.selectByValue(type);

		wutil.selectDDByValue(typedd, type);

		// identify save button and click
		createorg.getSave_btn();
		// driver.findElement(By.xpath("//input[@type='button']")).click();

		// validating org name
		// WebElement varifyname =
		// driver.findElement(By.xpath("//span[contains(text(),'Organization
		// Information')]"));

		OrginfoPomPage orginfo = new OrginfoPomPage(driver);

		WebElement varifyname = orginfo.getOrginfo_header();
		if (varifyname.getText().contains(Orgname)) {
			System.out.println("orgname test pass");
		} else {
			System.out.println("test fail");
		}
		// validating industry
		WebElement varifIndustry = orginfo.getIndustryDD();

		if (varifIndustry.getText().contains(industry)) {
			System.out.println("Insustry test pass");
		} else {
			System.out.println("test fail");
		}

		// validating Type
		WebElement varifType = orginfo.getTypeDD();

		if (varifType.getText().contains(type)) {
			System.out.println("Insustry test pass");
		} else {
			System.out.println("test fail");
		}

		// identify oragnization tab and click
		home.getOrg_tab();
		// driver.findElement(By.linkText("Organizations")).click();

		// identify delete and click on it
		driver.findElement(By.xpath(
				"//a[text()='" + Orgname + "' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']"))
				.click();

		// handle the alert popup
		wutil.switchto_Alert(driver);

		// click sign out
		home.logout(driver, wutil);

//		// mouse over
//		WebElement over = driver.findElement(By.xpath("//img[contains(@src,'user')]"));
//		wutil.UsingMousehover(driver, over);
//
//		// click sign out
//		driver.findElement(By.linkText("Sign Out")).click();

		// close the browser

		eutil.closeExcelFile();

		soft.assertAll();

	}
}
