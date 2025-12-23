package Organization_Module;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyUtility;
import GenericUtilities.javaUtility;
import GenericUtilities.webdriverUtility;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPOMPage;

public class CreateOrganizationTest 
{
@Test
public void Createorg() throws IOException
{
	
	PropertyUtility putil=new PropertyUtility();
	String browser = putil.FetchProertyData("browser");
	String url = putil.FetchProertyData("url");
	String username = putil.FetchProertyData("username");
	String password = putil.FetchProertyData("password");
	String timeouts = putil.FetchProertyData("timeouts");
	
	//get the random no
	javaUtility jutil=new javaUtility();
	int randomint = jutil.generateRandomNo();
	
	//fetch the data from excel utility 
	ExcelFileUtility eutil=new ExcelFileUtility();
	String Orgname = eutil.FetchDataFromExcel("Organization", 1, 2)+randomint;
	
	webdriverUtility wutil=new webdriverUtility();
	
	//launch the browser
	
//	wutil.launchTheBroweser(browser);
	
	//maximize the window
	//wutil.MaximizeThebrowser();
	
	//implicit wait
	//wutil.ImplicitWait(timeouts);
	
	//naviagte to appln
	//wutil.navigateToApplication(url);
	
	
//	//identify the un tf and pass the text
//	wutil.driver.findElement(By.name("user_name")).sendKeys(username);
//	
//	//identify the pw tf and pass the text
//	wutil.driver.findElement(By.name("user_password")).sendKeys(password);
//	
//	//identify the login and click
//	wutil.driver.findElement(By.id("submitButton")).click();
	
	LoginPOMPage l=new LoginPOMPage(wutil.driver);
	l.login(username, password);
	
	//validate homepage using soft asser
	
	
	SoftAssert soft=new SoftAssert();
	HomePomPage home=new HomePomPage(wutil.driver);
	String acthomeheader=home.getHomeheader();
	soft.assertEquals(acthomeheader, "Home");
	
	//Identify the org and Click on organization tab
	wutil.driver.findElement(By.linkText("Organizations")).click();
	
	//Identify the create org plus icon and click on it
	wutil.driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	
	//Identify the org name TF and enter text
	wutil.driver.findElement(By.name("accountname")).sendKeys(Orgname);
	
	//Identify save button and click on save button
	wutil.driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//validate the org 
	WebElement orgnamevalidate = wutil.driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
//	
//	if(orgnamevalidate.getText().contains(Orgname))
//	{
//		System.out.println("org test pass");	
//	}
//	else
//	{
//		System.out.println("org test fail");
//	}
	
	//using hard assert
	Assert.assertEquals(orgnamevalidate, Orgname, "verified org name fialed");
	
	
	
	//Identify the org and Click on organization tab
		wutil.driver.findElement(By.linkText("Organizations")).click();
		
		//delete the organization
		wutil.driver.findElement(By.xpath("//a[text()='"+Orgname+"' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']")).click();
		
		//handle alert popup
		wutil.driver.switchTo().alert().accept();
		
		//mouse hove on admin icon
		WebElement admin = wutil.driver.findElement(By.xpath("//img[contains(@src,'user')]"));
		
	wutil.UsingMousehover(admin);
		
		//click on signout
		wutil.driver.findElement(By.linkText("Sign Out")).click();
		
		//close the browser
		wutil.QuitBrowser();
		
		eutil.closeExcelFile();
		

	soft.assertAll();
}
}
