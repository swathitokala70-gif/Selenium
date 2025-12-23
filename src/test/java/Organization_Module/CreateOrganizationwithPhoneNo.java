package Organization_Module;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyUtility;
import GenericUtilities.javaUtility;
import GenericUtilities.webdriverUtility;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPOMPage;

public class CreateOrganizationwithPhoneNo
{
	@Test
	public void CreateOrgWithPhoneNum () throws EncryptedDocumentException, IOException
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
		String Orgname = eutil.FetchDataFromExcel("Organization", 7, 2)+randomint;
		String phno = eutil.FetchDataFromExcel("Organization", 7, 3);
		
		//String browser = "Chrome";
		//String Orgname="tyss";
		//String phno= "9676485548";
		
		webdriverUtility wutil= new webdriverUtility();
		//Launch the browser
		
		wutil.launchTheBroweser(browser);
		  //Maximize the window
		wutil.MaximizeThebrowser();
		
		//Implicit wait
				wutil.ImplicitWait(timeouts);
				//Navigate to an APPlication
			wutil.navigateToApplication(url);
				
//				// Identify the username
//				wutil.driver.findElement(By.name("user_name")).sendKeys(username);
//				
//				//Identify the password
//				wutil.driver.findElement(By.name("user_password")).sendKeys(password);
//				
//				//Identify the login button and click on it
//				wutil.driver.findElement(By.id("submitButton")).click();
			
			LoginPOMPage l=new LoginPOMPage(wutil.driver);
			l.login(username, password);
			
//validate homepage using soft asser
			
			
			SoftAssert soft=new SoftAssert();
			HomePomPage home=new HomePomPage(wutil.driver);
			String acthomeheader=home.getHomeheader();
			soft.assertEquals(acthomeheader, "Home");
				
				//Identify org tab and click on it
				wutil.driver.findElement(By.linkText("Organizations")).click();
				
				//Identify plus icon and click on it
				wutil.driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//Identify org TF and enter orgname
				wutil.driver.findElement(By.name("accountname")).sendKeys(Orgname);
				
				//Identify phno TF and pass the phno
				wutil.driver.findElement(By.id("phone")).sendKeys(phno);
				
				//Identify save and click on it
				wutil.driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Validate org name
			   WebElement verifyorg = wutil.driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
			
			if(verifyorg.getText().contains(Orgname)) {
				System.out.println("create org Test pass");
			}else {
				System.out.println("Create org Test fail");
				
			}
			
			
			//Validate phno
		   WebElement verifyphno = wutil.driver.findElement(By.id("dtlview_Phone"));
		   
		   if(verifyphno.getText().contains(phno)) {
			   System.out.println("Test pass");
		   }else {
			   System.out.println("Test fail");
			   
		   }
		   
		    //click on orgtab
			wutil.driver.findElement(By.linkText("Organizations")).click();
			
			//Identify delete link and click on it
			wutil.driver.findElement(By.xpath("//a[text()='"+Orgname+"' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']")).click();
			
			//Handle the alert popup
			wutil.driver.switchTo().alert();
			
			//handle the alert popup
			wutil.driver.switchTo().alert().accept();
			
			//mouse over
			WebElement over = wutil.driver.findElement(By.xpath("//img[contains(@src,'user')]"));
			wutil.UsingMousehover(over);
			
			//click sign out
			wutil.driver.findElement(By.linkText("Sign Out")).click();
			
			//close the browser
			 wutil.driver.quit();
		eutil.closeExcelFile();
	
				soft.assertAll();
				
	}

}
