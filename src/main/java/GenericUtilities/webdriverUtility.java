package GenericUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author SWATHI
 * This class contains all reuable methods from selenium library
 */
public class webdriverUtility 
{

	public void navigateToApplication(WebDriver driver,String url) {
		driver.get(url);
	}

	public String fetchthetittle(WebDriver driver) {

		String title = driver.getTitle();
		return title;
	}

	public String fetchthecurrenturl(WebDriver driver) {
		String url = driver.getCurrentUrl();
		return url;
	}

	public String fetchpagesource(WebDriver driver) {
		String source = driver.getPageSource();
		return source;
	}

	public void MaximizeThebrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void MinimizeThebrowser(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public void windowfullscreen(WebDriver driver) {
		driver.manage().window().fullscreen();
	}

	public Dimension fetchsize(WebDriver driver) {
		Dimension dim = driver.manage().window().getSize();
		return dim;
	}

	public Point fetchtheposition(WebDriver driver) {
		Point position = driver.manage().window().getPosition();
		return position;
	}

	public void setthesize(WebDriver driver,int height, int width) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public void setposition(WebDriver driver,int x, int y) {
		driver.manage().window().setPosition(new Point(x, y));
	}

	public void navigateToPriviousPage(WebDriver driver,String url) {
		driver.navigate().back();
	}

	public void navigatetonextpage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshthepage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void navigateToTheWebPage(WebDriver driver,String url) {
		driver.navigate().to(url);
	}

	public void navigateToAnApplicationUsingToUrl(WebDriver driver,String url) throws MalformedURLException
	{
		driver.navigate().to(new URL(url));
	}

	public void closedBrowser(WebDriver driver) {
		driver.close();
	}

	public void QuitBrowser(WebDriver driver) {
		driver.quit();
	}

	public String fetchTheParentWindowId(WebDriver driver) {
		String pwid = driver.getWindowHandle();
		return pwid;
	}

	public Set<String> fetchtheAllWindoId(WebDriver driver) {
		Set<String> wids = driver.getWindowHandles();
		return wids;
	}

	public void swithtoWindowIdusing_tittle(WebDriver driver,String tittle) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(tittle)) {
				break;
			}
		}

	}

	public void swithtoWindowIdUsing_url(WebDriver driver,String url) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(url)) {
				break;
			}
		}
	}

	public void swithbacktoParent(WebDriver driver,String pwid) {
		driver.switchTo().window(pwid);
	}

	public void switchtoFrame_Index(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}

	public void switchtoFrameUsing_id(WebDriver driver,int id) {
		driver.switchTo().frame(id);
	}

	public void switchtoFrameUsing_element(WebDriver driver,String ele) {
		driver.switchTo().frame(ele);
	}

	public void switchFromFrameToMainWindowPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void switchFromFrametoParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	public void switchto_Alert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchtoalert_usingCancle(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void switchToAlert_fetchthetext(WebDriver driver) {
		driver.switchTo().alert().getText();
	}

	public void switchToAlert_EnterText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void ImplicitWait(WebDriver driver,String time) {
		long timeouts = Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeouts));
	}

	public void waitUntileEle_displayed(WebDriver driver,String time, WebElement ele) {
		long timeouts = Long.parseLong(time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitUntilElement_clickable(WebDriver driver,String time, WebElement ele) {
		long timeouts = Long.parseLong(time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitUntilElement_displayed(WebDriver driver,String time, String tittle) {
		long timeouts = Long.parseLong(time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wait.until(ExpectedConditions.titleContains(tittle));
	}

	public void selectClassUsing_Id(WebElement ddel, int id) {
		Select s = new Select(ddel);
		s.selectByIndex(id);

	}

	public void selectDDByIndex(WebElement ddel, int index) {
		Select s1 = new Select(ddel);
		s1.selectByIndex(index);
	}

	public void selectDDByValue(WebElement ddel, String value) {
		Select s2 = new Select(ddel);
		s2.selectByValue(value);
	}

	public void selectDDByText(WebElement ddel, String text) {
		Select s2 = new Select(ddel);
		s2.selectByValue(text);
	}

	public void DiselectDDByindex(WebElement ddel, int index) {
		Select s = new Select(ddel);
		s.deselectByIndex(index);
	}

	public void DiselectDDByValue(WebElement ddel, String value) {
		Select s = new Select(ddel);
		s.deselectByValue(value);
	}

	public void DiselectDDByText(WebElement ddel, String text) {
		Select s = new Select(ddel);
		s.deselectByVisibleText(text);

	}

	public void DiselectDDAll(WebElement ddel) {
		Select s = new Select(ddel);
		s.deselectAll();
	}

	public WebElement fetchFirstSelectedOption(WebElement ddele) {
		Select s = new Select(ddele);
		WebElement firstselect = s.getFirstSelectedOption();
		return firstselect;
	}

	public List<WebElement> fetchAllSelectOptions(WebElement ddel) {
		Select s = new Select(ddel);
		List<WebElement> allselectOption = s.getAllSelectedOptions();
		return allselectOption;

	}

	public boolean Ismultiple(WebElement ddel) {
		Select s = new Select(ddel);
		boolean multiple = s.isMultiple();
		return multiple;

	}

	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest) {
		Actions act = new Actions(driver);
		act.dragAndDrop(src, dest).perform();
	}

	public void UsingMousehover(WebDriver driver,WebElement hover) {
		Actions act = new Actions(driver);
		act.moveToElement(hover).perform();
	}

	public void UsingRightClick(WebDriver driver,WebElement rigth) {
		Actions act = new Actions(driver);
		act.contextClick(rigth).perform();
	}

	public void ScrollWebpageUsingAxis(WebDriver driver,int x,int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}

	public void ScrollPageUsingElement(WebDriver driver,WebElement ele) {
		Actions act = new Actions(driver);
		act.scrollToElement(ele).perform();

	}

	public void ReleaseAnElement(WebDriver driver,WebElement ele) {
		Actions act = new Actions(driver);
		act.release(ele).perform();
	}
	
	public void UsingClickAndHold(WebDriver driver,WebElement ele) {
		Actions act = new Actions(driver);
		act.clickAndHold(ele).perform();
	}

	public void DragAndDropElement(WebDriver driver, WebElement dragele, WebElement dropele) {
		Actions act=new Actions(driver);
		act.dragAndDrop(dragele,dropele).perform();

	}

	public void DoubleClick(WebDriver driver, WebElement ele)
	{
	 Actions act=new Actions(driver);
	 act.doubleClick(ele).perform();
	}
}
