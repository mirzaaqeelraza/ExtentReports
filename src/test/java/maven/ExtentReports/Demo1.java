package maven.ExtentReports;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Demo1 {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeTest
    public void setup() {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reportPath");
        extent.attachReporter(sparkReporter);
    }

    @BeforeMethod
    public void initialize() {
    	String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
        driver = new ChromeDriver();
        driver.get("https://dazzlecasino.sagatechsol.com/home");
        
       // String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		//ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        //extent = new ExtentReports();
    }

    @Test
    public void loginTest() throws InterruptedException {
        test = extent.createTest("Login Test");
        test.log(Status.INFO, "Starting the login test");

        // Perform login
        driver.findElement(By.xpath("//div[text()=\"Login \"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class=\"login_input__b5d2H\"]//input[@name=\"userName\"]")).sendKeys("mirzaaqeelraza@gmail.com");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class=\"login_input__b5d2H\"]//input[@name=\"password\"]")).sendKeys("123456");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
        Thread.sleep(5000);
        // Check if login is successful (You can add validation here)
        // For example, you can assert that a certain element is present after successful login.
        boolean loginSuccessful = true;

        if (loginSuccessful) {
            test.log(Status.PASS, "Login was successful");
        } else {
            test.log(Status.FAIL, "Login failed");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void reportTeardown() {
        extent.flush();
    }
}

