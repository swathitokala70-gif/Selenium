package Contact_Module;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyUtility;
import GenericUtilities.javaUtility;
import GenericUtilities.webdriverUtility;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPOMPage;

public class CreateContactWithOrganizationTest {
@Test
public void createconOrg() throws IOException
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
			String conname = eutil.FetchDataFromExcel("contact", 4, 2)+randomint;
			String orgname = eutil.FetchDataFromExcel("contact", 4, 3)+randomint;
	//String browser = "Chrome";
	//String orgname="job";
	//String conname= "abcd";
	
	//Launch the browser
			webdriverUtility wutil=new webdriverUtility();
			wutil.launchTheBroweser(browser);
	  //Maximize the 
			wutil.MaximizeThebrowser();
	
	//Implicit wait
			wutil.ImplicitWait(timeouts);

	
	//Navigate to an APPlication
			wutil.navigateToApplication(url);
	//wutil.driver.get(url);
	
//	// Identify the username
//	wutil.driver.findElement(By.name("user_name")).sendKeys(username);
//	
//	//Identify the password
//	wutil.driver.findElement(By.name("user_password")).sendKeys(password);
//	
//	//Identify the login button and click on it
//	wutil.driver.findElement(By.id("submitButton")).click();
	
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
	wutil.driver.findElement(By.name("accountname")).sendKeys(orgname);
	
	//Identify save and click on it
	wutil.driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//Validate org name
   WebElement verifyorg = wutil.driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));

	if(verifyorg.getText().contains(orgname)) {
	System.out.println("create org Test pass");
  }else {
	System.out.println("Create org Test fail");
  }
	
	//Identify contact tab and click on it
	wutil.driver.findElement(By.linkText("Contacts")).click();
	
	//Identify plus icon and click on it
	wutil.driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	
	//Identify the last name and pass the text
	wutil.driver.findElement(By.name("lastname")).sendKeys(conname);
	
	//click on org plus icon on contact list
	wutil.driver.findElement(By.xpath("//img[@title='Select']")).click();
	
	//Fetch the parent window id
	//String pwid = wutil.driver.getWindowHandle();
	
	String pwid = wutil.fetchTheParentWindowId();
	
	//Switch the driver control to child window
	
//	Set<String> wids = wutil.driver.getWindowHandles();
//	for(String s:wids) {
//		wutil.driver.switchTo().window(s);
//		if(wutil.driver.getCurrentUrl().contains("Accounts&action")) {

			 wutil.swithtoWindowIdUsing_url("Accounts&action");
			 
			//search for the org name
	wutil.driver.findElement(By.id("search_txt")).sendKeys(orgname);
	wutil.driver.findElement(By.name("search")).click();
	wutil.driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
	
			//switch back to parent window
			//wutil.driver.switchTo().window(pwid);
	wutil.swithbacktoParent(pwid);
			
			//identify save btn and click on it 
			wutil.driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			//validate contact name
			WebElement varify_name = wutil.driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
			if(varify_name.getText().contains(conname)) {
				System.out.println("create contact with org test pass");
			}
			else {
				System.out.println("create contact with org test fail");
			}
			//identify contact tab and click
			wutil.driver.findElement(By.linkText("Contacts")).click();
			
			//identify delete link and click on it
			wutil.driver.findElement(By.xpath("//a[text()='"+conname+"']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']")).click();
			
			//handle alert popup
			wutil.driver.switchTo().alert().accept();
			
			//identify oragnization tab and click
			wutil.driver.findElement(By.linkText("Organizations")).click();
			
			//identify delete and click on it
			wutil.driver.findElement(By.xpath("//a[text()='"+orgname+"' and contains(@href,'Marketing&')]/../../descendant::a[text()='del']")).click();
					
			//handle alert popup
			wutil.driver.switchTo().alert().accept();
			
			//mouse hover
			WebElement admin = wutil.driver.findElement(By.xpath("//img[contains(@src,'user')]"));
			wutil.UsingMousehover(admin);

			//click on sign out button
			wutil.driver.findElement(By.linkText("Sign Out")).click();
			
			//close the browser
			wutil.driver.quit();
			
			soft.assertAll();

}
}