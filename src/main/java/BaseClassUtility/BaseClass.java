package BaseClassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.DataBaseUtility;
import GenericUtilities.PropertyUtility;
import GenericUtilities.webdriverUtility;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPOMPage;

public class BaseClass {
	public DataBaseUtility dutil = new DataBaseUtility();
	public PropertyUtility putil = new PropertyUtility();
	public webdriverUtility wutil = new webdriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver=null;
	

	@BeforeSuite
	public void coonectToDatabase() throws SQLException {
		Reporter.log("get connection with databse", true);
		dutil.connectToDatabase();
	}

	@BeforeTest
	public void configParallelExe() {
		Reporter.log("Configuaration of parallel execution", true);
	}
	@Parameters("browser")
	@BeforeClass
	public void launchThebrowser(String browser) throws IOException {
//		String browser = putil.FetchProertyData("browser");
		Reporter.log("launch browser", true);
		if (browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("FireFox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver=driver;
	}

	@BeforeMethod
	public void login() throws IOException {
		String url = putil.FetchProertyData("url");
		String username = putil.FetchProertyData("username");
		String password = putil.FetchProertyData("password");
		String timeouts = putil.FetchProertyData("timeouts");
		wutil.MaximizeThebrowser(driver);
		wutil.ImplicitWait(driver, timeouts);
		wutil.navigateToApplication(driver, url);
		Reporter.log("lohin to application", true);

		LoginPOMPage l = new LoginPOMPage(driver);
		l.login(username, password);
	}

	@AfterMethod
	public void logout() {
		HomePomPage home = new HomePomPage(driver);
		Reporter.log("log out from application", true);
		wutil.UsingMousehover(driver, home.getAdmin());
		home.getSignout();
	}

	@AfterClass
	public void quitTheBrowser() {
		Reporter.log("quit the browser", true);
		wutil.QuitBrowser(driver);
	}

	@AfterTest
	public void closeParallelExe() {
		Reporter.log("close parallel execution", true);
	}

	@AfterSuite
	public void disconnecTheDatabase() throws SQLException {
		Reporter.log("disconnect with database", true);
		dutil.closeDatabaseConnection();
	}
}
