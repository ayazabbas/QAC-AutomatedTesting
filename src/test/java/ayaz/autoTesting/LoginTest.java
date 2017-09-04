package ayaz.autoTesting;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LoginTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private static ExtentReportManager reportManager;

	@BeforeClass
	public static void init() {
		String property = System.getProperty("user.dir");
		ReportDetails reportDetails = new ReportDetails(property + "\\testReports\\LoginTestReport", "Basic Extent Report",
				"Basic Report");
		reportDetails.setTheme(Theme.DARK);
		reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
	}

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void test() throws IOException {
		ExtentTest extentTest = reportManager.setUpTest();
		try {
			driver.navigate().to("http://www.thedemosite.co.uk/");
			driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
			wait.until(ExpectedConditions.titleIs("Add a user - FREE PHP code and SQL"));
			driver.findElement(By.name("username")).sendKeys("ayaz");
			driver.findElement(By.name("password")).sendKeys("ayaz");
			driver.findElement(By.name("FormsButton2")).click();
			driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
			wait.until(ExpectedConditions.titleIs("Login example page to test the PHP MySQL online system"));
			driver.findElement(By.name("username")).sendKeys("ayaz");
			driver.findElement(By.name("password")).sendKeys("ayaz");
			driver.findElement(By.name("FormsButton2")).click();
			assertEquals("**Successful Login**", driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testSuccesful"));
			extentTest.pass("Passed");
		} catch (Exception e) {
			String details = "LoginTest failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "exceptionDuringTest"));
			Assert.fail(details);
		} catch (AssertionError e) {
			String details = "LoginTest failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "AssertionError"));
			Assert.fail(details);
		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@AfterClass
	public static void cleanUp() {
		reportManager.clearTests();
	}

}
