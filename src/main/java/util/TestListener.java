package util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Starting " + suite.getName());
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Finished executing " + suite.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("===Executing test===" + "\n" + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Status: Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Status: FAILED");
		String fileName = String.format("Screenshot-%s.jpg", Calendar.getInstance().getTimeInMillis());
		WebDriver driver = (WebDriver)result.getTestContext().getAttribute("WebDriver");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/" + fileName);
		try {
			FileUtils.copyFile(srcFile, destFile);
			System.out.println("Screenshot taken, saved in screenshots folder");
		}catch(IOException e) {
			System.out.println("Failed to take screenshot");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped test: " + result.getName());
	}

}
