package Contact_Module;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

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

public class CreateContactWithSupportDateTest {
	@Test
	public void Createconwithsupportdate() throws IOException 
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
		String conname = eutil.FetchDataFromExcel("contact", 7, 2)+randomint;
		
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

		// Identify contact tab and click on it
		wutil.driver.findElement(By.linkText("Contacts")).click();

		// Identify plus icon and click on it
		wutil.driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Identify the last name and pass the text
		wutil.driver.findElement(By.name("lastname")).sendKeys(conname);

		// Generate Todays date
		Date dobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startdate = sim.format(dobj);
		System.out.println(startdate);

		// Identify startdate TF and pass the date
		WebElement startdateTF = wutil.driver.findElement(By.name("support_start_date"));
		startdateTF.clear();
		startdateTF.sendKeys(startdate);

		// Generate date after 30 days
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String enddate = sim.format(cal.getTime());
		System.out.println(enddate);

		// Identify End date TF and pass the date
		WebElement enddateTf = wutil.driver.findElement(By.name("support_end_date"));
		enddateTf.clear();
		enddateTf.sendKeys(enddate);

		// click the save button and click on it
		wutil.driver.findElement(By.xpath("//input[@type='submit']")).click();

		// Validate contact name
		WebElement verify_name = wutil.driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
		if (verify_name.getText().contains(conname)) {
			System.out.println("Test pass");
		} else {
			System.out.println("Test fail");
		}

		// Validate the start date
		WebElement start_date = wutil.driver.findElement(By.id("dtlview_Support Start Date"));
		if (start_date.getText().contains(startdate)) {
			System.out.println("start date test pass");
		} else {
			System.out.println("start date test fail");
		}

		// validate the End date
		WebElement end_date = wutil.driver.findElement(By.id("dtlview_Support End Date"));
		if (end_date.getText().contains(enddate)) {
			System.out.println("end date test pass");
		} else {
			System.out.println("end date test fail");
		}

		// Identify contact tab and click on it
		wutil.driver.findElement(By.linkText("Contacts")).click();

		// Identify delete link and click on it
		wutil.driver.findElement(By.xpath(
				"//a[text()='" + conname + "' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']"))
				.click();

		// handle the alert popup
		wutil.switchto_Alert();

		// mouse over on admin button
		WebElement over = wutil.driver.findElement(By.xpath("//img[contains(@src,'user')]"));
		wutil.UsingMousehover(over);

		// click on sign out
		wutil.driver.findElement(By.linkText("Sign Out")).click();

		// close the browser
		wutil.driver.quit();
		soft.assertAll();

	}

}
