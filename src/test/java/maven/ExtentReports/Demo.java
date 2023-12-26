package maven.ExtentReports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {

	WebDriver driver;
	ExtentReports extent;

	@BeforeMethod
	public void configutaion() {
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Omoyo test Reporter");
		reporter.config().setDocumentTitle("Omoyo Test Reort Title");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating System", "Windows 10");
		extent.setSystemInfo("Tested By", "Mirza Aqeel Raza");

	}
	
	/* @BeforeMethod
    public void setup() {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extent.attachReporter(sparkReporter);

        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("your_login_page_url");
    } */

	@Test
	public void testOne() {

		ExtentTest eTest = extent.createTest("Test One Created");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		eTest.info("Chrome Browser Launched");
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		eTest.info("Naviagted to Omoayo Landing Page");
		
		/* @Test
		    public void loginTest() {
		        test = extent.createTest("Login Test");

		        // Perform the login using Page Factory
		        LoginPage loginPage = new LoginPage(driver);
		        loginPage.login("your_username", "your_password");

		        // Add a log entry to the report
		        test.log(Status.PASS, "Login successful");
		    }

		/*
		 * String actualText =
		 * driver.findElement(By.xpath("//p[@id=\"pah\"]")).getText();
		 * if(actualText.equals("PracticeAutomationHere")) {
		 * System.out.println("Test Passed"); }else { System.out.println("Test Failed");
		 * }
		 */

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id=\"pah\"]")).getText(), "PracticeAutomationHere");
		eTest.info("Test One Passed");

	}

	@AfterMethod
	public void teardown() {
		driver.close();
		extent.flush();
	}
}
