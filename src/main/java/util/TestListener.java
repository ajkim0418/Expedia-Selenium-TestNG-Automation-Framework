package util;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {
	
	@Override
	public void onStart(ISuite suite) {
		
	}
	
	@Override
	public void onFinish(ISuite suite) {
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Executing test: " + result.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Finished executing test");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
	
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped test: " + result.getName());
	}

}
