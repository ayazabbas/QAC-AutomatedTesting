package tests;

import pages.DemoAddUser;
import pages.DemoHome;
import pages.DemoLogin;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Function;

public class LoginTest {

	private WebDriver driver;
	private WebDriverWait explicitWait;
	private static ExtentReportManager reportManager;
	private ExtentTest extentTest;
	private DemoHome homePage;
	private DemoAddUser addUserPage;
	private DemoLogin loginPage;

	@BeforeClass
	public static void init() {
		String property = System.getProperty("user.dir");
		ReportDetails reportDetails = new ReportDetails(property + "\\testReports\\LoginTestReport",
				"Basic Extent Report", "Basic Report");
		reportDetails.setTheme(Theme.DARK);
		reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
	}

	@Before
	public void setUp() {
		extentTest = reportManager.setUpTest("LoginTest");
		extentTest.info(
				"This test will navigate to thedemosite.co.uk and attempt to create a user and then log in with that user.");
		SpreadSheetReader sheetReader = new SpreadSheetReader(
				System.getProperty("user.dir") + "/spreadsheets/loginTestInput.xlsx");
		List<String> row = sheetReader.readRow(0, "inputData");
		switch (row.get(0)) {
		case ("chrome"):
			driver = new ChromeDriver();
			break;
		case ("firefox"):
			driver = new FirefoxDriver();
		}
		explicitWait = new WebDriverWait(driver, 3);
	}

	@Test
	public void test() throws IOException {
		SpreadSheetReader sheetReader = new SpreadSheetReader(
				System.getProperty("user.dir") + "/spreadsheets/loginTestInput.xlsx");
		int[] rowNumbers = { 1, 2 };
		String username = "";
		String password = "";
		try {
			for (int i : rowNumbers) {
				List<String> row = sheetReader.readRow(i, "inputData");
				username = row.get(0);
				password = row.get(1);

				homePage = new DemoHome(driver);
				addUserPage = new DemoAddUser(driver);
				loginPage = new DemoLogin(driver);

				driver.navigate().to("http://www.thedemosite.co.uk/");
				homePage.getUserLink().click();
				explicitWait.until(ExpectedConditions.titleIs("Add a user - FREE PHP code and SQL"));
				addUserPage.fillAndSubmitDetails(username, password);
				addUserPage.getLoginLink().click();
				explicitWait
						.until(ExpectedConditions.titleIs("Login example page to test the PHP MySQL online system"));
				loginPage.fillAndSubmitDetails(username, password);

				new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
						.pollingEvery(500, TimeUnit.MILLISECONDS).until(new Function<WebDriver, Boolean>() {
							@Override
							public Boolean apply(WebDriver webDriver) {
								String labelText = loginPage.getLoginInfoText();
								if (labelText.equals("**No login attempted**"))
									return false;
								else
									return true;
							}
						});
				assertEquals("**Successful Login**", loginPage.getLoginInfoText());
				extentTest.pass("Passed for username and password: " + username + ", " + password);
			}
		} catch (Exception e) {
			String details = "LoginTest failed for username and password: " + username + ", " + password + ": "
					+ e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "exceptionDuringTest"));
			Assert.fail(details);
		} catch (AssertionError e) {
			String details = "LoginTest failed for username and password: " + username + ", " + password + ": "
					+ e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "assertionError"));
			Assert.fail(details);
		}
	}

	@After
	public void tearDown() {
		try {
			driver.quit();
		} catch (WebDriverException e) {
			String details = "LoginTest encountered exception at tearDown: " + e.getMessage();
			extentTest.warning(details);
		}
	}

	@AfterClass
	public static void cleanUp() {
		reportManager.clearTests();
	}

}
