package GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClassUtility.BaseClass;

public class ListernersUtility implements ISuiteListener, ITestListener
{
	public ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String time = new Date().toString().replace(":", "_").replace("", "_");
		
		report.createTest(testname+time);
		UtilityObjectClass.setStest(test);
		
		
		
		Reporter.log(testname+": Test execution started", true);
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname+": Test execution success", true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname+": Test execution failure - screenshot", true);
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshots/"+ testname +".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			Reporter.log("Failed"+testname+"screenshot not captured", true);
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname+": Test execution skipped", true);
	}

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite execution started -adv report configuartion", true);
		
		//configuration of adv reports
		ExtentSparkReporter spark=new ExtentSparkReporter("./advancedReports/Reports.html");
		spark.config().setDocumentTitle("VtigerContactAndOrgReports");
		spark.config().setReportName("Contact_org");
		spark.config().setTheme(Theme.DARK);
		
		//configuartion test report
		report = new ExtentReports(); 
		report.attachReporter(spark);
		report.setSystemInfo("os-version", "windows-11");
		report.setSystemInfo("chrome version", "chrome-143");
		report.setSystemInfo("edge version", "edge-143");
		report.setSystemInfo("firefox version", "firefox-146");
		
		test = report.createTest("vtiger");
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Suite execution finished -adv report configuartion", true);
	}

}
