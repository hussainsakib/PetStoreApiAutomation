package utilities;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	
	
	public void onStart(ITestContext testContext) {
		
		
		String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";

		 sparkReporter = new ExtentSparkReporter(reportPath);
		
		
	
		
		sparkReporter.config().setDocumentTitle("RestAssuredApiAutomation");
		sparkReporter.config().setReportName("PetStoreApiTesting");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "PetStore");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() +" got successfully executed");
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+ " got failed");
		test.log(Status.FAIL, result.getThrowable());
		
	
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " got skipped");
		if (result.getThrowable() != null) {
		    test.log(Status.INFO, result.getThrowable());
		}
	}
	@Override
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
	}
	
}
