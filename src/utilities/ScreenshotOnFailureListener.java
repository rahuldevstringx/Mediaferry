package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseClasses.Driver;

public class ScreenshotOnFailureListener implements ITestListener {

	WebDriver driver=null;
	@Override
	public void onTestFailure(ITestResult result) {
		String methodName=result.getName().toString().trim();
		takeScreenShot(Driver.webdriver,methodName);
	}

	public void takeScreenShot(WebDriver driver,String name)
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date currDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yy_HH_mm_ss");
		String dateAndTime = dateFormat.format(currDate);

		try { 
			FileUtils.copyFile(scrFile, new File("screenshots/" + name + "_"  + dateAndTime +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onFinish(ITestContext context) {}

	public void onTestStart(ITestResult result) {   }

	public void onTestSuccess(ITestResult result) {   }

	public void onTestSkipped(ITestResult result) {   }

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

	public void onStart(ITestContext context) {   }
}  