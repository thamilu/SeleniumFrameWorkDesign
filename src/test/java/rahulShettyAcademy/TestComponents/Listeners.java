package rahulShettyAcademy.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulShettyAcademy.resources.ExtendreporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	// You can override methods here to add custom behavior on test events
	ExtentReports extent = ExtendreporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // Unique thread id (ThreadLocal)
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
//		test.log(Status.FAIL, "Test Failed");
		
		extentTest.get().fail(result.getThrowable());
		
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
				.get(result.getInstance());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String filepath = getScreenshot(result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	    
		// Screenshot
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
