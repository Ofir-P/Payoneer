package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Main;
import utilites.GetDriver;
import utilites.Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;


public class Sanity {

	// Global variables 
	// Add extent reports
	private ExtentReports extent;
	private ExtentTest myTest;
	private static String reportPaht = System.getProperty("user.dir") + "\\test-output\\payoneer.html";
	private WebDriver driver;
	private String baseUrl;
	
	//pages
	private Main main;
	
	
	

	@BeforeClass
	public void beforeClass() {
		extent = new ExtentReports(reportPaht);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\payoneer-extent-config.xml"));
		baseUrl = "https://www.payoneer.com/";
		driver = GetDriver.getDriver("chrome", baseUrl);

		main = new Main(driver);
		
	}

	
	
	@BeforeMethod
	public void beforeMethod(Method method) throws IOException {
		myTest = extent.startTest(method.getName());
		myTest.log(LogStatus.INFO, "Starting test", "Start test");
	}
	
	
	
	/*  Prerequisite: Getting into "https://www.payoneer.com/"
	/*  	Given: Client is in site main page
	 * 		When: Click on Register button
	 *  	Then: Getting into Registration page.
	 *  Ofir, 09/03/2021
	 */
	
	@Test(priority = 1, enabled = true, description = "Go to Registration page")
	public void go_to_registration() throws InterruptedException, IOException {	
		Assert.assertTrue(main.goRegister());
	}
	
	/*  Prerequisite: Getting into Registration page
	/*  	Given: Client is in Registration page
	 * 		When: Click on Two drop-boxes
	 *  	Then: Be able to continue on Register process.
	 *  Ofir, 09/03/2021
	 */
	
	@Test(priority = 2, enabled = true, description = "Choose account options")
	public void chooseAccount() throws InterruptedException, IOException {	
		Assert.assertTrue(main.find_Right_Account());
	}
	
	/*  Prerequisite: Getting into "https://www.payoneer.com/accounts/"
	/*  	Given: Client is in Accounts page
	 * 		When: Click on Register button
	 *  	Then: Getting into 2nd Registration page.
	 *  Ofir, 09/03/2021
	 */
	
	@Test(priority = 3, enabled = true, description = "Go to 2nd Register screen")
	public void go_to_registration2() throws InterruptedException, IOException {	
		Assert.assertTrue(main.goRegister2());
	}
	
	/*  Prerequisite: Getting into 2nd Registration page
	/*  	Given: Client is in 2nd Registration page
	 * 		When: Filling all the Sign Up fields
	 *  	Then: Being able to fill up sign form
	 *  Ofir, 09/03/2021
	 */
	
	@Test(priority = 4, enabled = true, description = "Filling up sign form")
	public void SignUpProcess() throws InterruptedException, IOException {	
		Assert.assertTrue(main.verify_SignUpProcess());
	}
	
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			myTest.log(LogStatus.FAIL, "Test failed: " + result.getName());
			myTest.log(LogStatus.FAIL, "Test failed reason: " + result.getThrowable());
			myTest.log(LogStatus.FAIL, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));
		}
		else {
			myTest.log(LogStatus.PASS, result.getName(), "Verify successful ");
			myTest.log(LogStatus.PASS, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));

		}

		myTest.log(LogStatus.INFO, "Finish test", "Finish test ");
		extent.endTest(myTest);
	
		//return to base URL 
		//driver.get(baseUrl);
	}

	@AfterClass
	public void afterClass() {
		extent.flush();
		extent.close();
	//	driver.quit();

	}

	
}
