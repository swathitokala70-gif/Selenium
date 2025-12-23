package Organization_Module;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyUtility;
import GenericUtilities.javaUtility;
import GenericUtilities.webdriverUtility;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPOMPage;

public class CreateOrgaizationwithIndustryandType {
	@Test
	public void createorgwithind() throws IOException
	{
		//create object for property utility class for accessing data
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
				String Orgname = eutil.FetchDataFromExcel("Organization", 4, 2)+randomint;
				String industry = eutil.FetchDataFromExcel("Organization", 4, 3);
				String type = eutil.FetchDataFromExcel("Organization", 4, 4);
				System.out.println(Orgname);
		
		//String browser = "Chrome";
		//String Orgname = "xyz";
				
				webdriverUtility wutil=new webdriverUtility();

		// Launch the browser
		wutil.launchTheBroweser(browser);
		
		// Maximize the window
		wutil.MaximizeThebrowser();

		// Implicit wait
		wutil.ImplicitWait(timeouts);

		// Navigate to an APPlication
		wutil.navigateToApplication(url);

//		// Identify the username
//		wutil.driver.findElement(By.name("user_name")).sendKeys(username);
//
//		// Identify the password
//		wutil.driver.findElement(By.name("user_password")).sendKeys(password);
//
//		// Identify the login button and click on it
//		wutil.driver.findElement(By.id("submitButton")).click();
		
		LoginPOMPage l=new LoginPOMPage(wutil.driver);
		l.login(username, password);
		
		//validate homepage using soft asser
		
		
		SoftAssert soft=new SoftAssert();
		HomePomPage home=new HomePomPage(wutil.driver);
		String acthomeheader=home.getHomeheader();
		soft.assertEquals(acthomeheader, "Home");

		// Identify org tab and click on it
		wutil.driver.findElement(By.linkText("Organizations")).click();

		// Identify plus icon and click on it
		wutil.driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Identify org TF and enter orgname
		wutil.driver.findElement(By.name("accountname")).sendKeys(Orgname);

		// select the indusrty dropdown
		WebElement industrydd = wutil.driver.findElement(By.name("industry"));

		// create select class
		//Select s = new Select(industrydd);

		// select by option using index
		//s.selectByValue(industry);
		
		wutil.selectDDByValue(industrydd, industry);
		
		// select type dropdown
		WebElement typedd = wutil.driver.findElement(By.name("accounttype"));

		// create object for select
//		Select s2 = new Select(typedd);
//		s2.selectByValue(type);
 
		wutil.selectDDByValue(typedd, type);
		
		// identify save button and click
		wutil.driver.findElement(By.xpath("//input[@type='button']")).click();
		
		//validating org name
		WebElement varifyname = wutil.driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		
		if (varifyname.getText().contains(Orgname)) {
			System.out.println("test pass");
		} else {
			System.out.println("test fail");
		}
		//validating industry 
		if (varifyname.getText().contains(Orgname)) {
			System.out.println("test pass");
		} else {
			System.out.println("test fail");
		}
		// identify oragnization tab and click
		wutil.driver.findElement(By.linkText("Organizations")).click();

		// identify delete and click on it
		wutil.driver.findElement(By.xpath("//a[text()='"+Orgname+"' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']")).click();

		// handle the alert popup
		wutil.switchto_Alert();

		// mouse over
		WebElement over = wutil.driver.findElement(By.xpath("//img[contains(@src,'user')]"));
		wutil.UsingMousehover(over);

		// click sign out
		wutil.driver.findElement(By.linkText("Sign Out")).click();

		// close the browser
		wutil.QuitBrowser();
		
		eutil.closeExcelFile();
		
		soft.assertAll();

	}
}
