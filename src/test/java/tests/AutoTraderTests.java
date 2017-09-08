package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Function;

import pages.AutoTraderHome;

public class AutoTraderTests {

	private WebDriver driver;
	private static ExtentReportManager reportManager;
	private ExtentTest extentTest;
	private final String autoTraderURL = "http://www.autotrader.co.uk/";
	private AutoTraderHome homePage;
	private Wait<WebDriver> wait;

	@BeforeClass
	public static void init() {
		String property = System.getProperty("user.dir");
		ReportDetails reportDetails = new ReportDetails(property + "\\testReports\\AutoTraderTestReport",
				"Basic Extent Report", "Basic Report");
		reportDetails.setTheme(Theme.DARK);
		reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
	}

	@Before
	public void setUp() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Select browser to run tests with (chrome/firefox): ");
		try {
			String browser = br.readLine();
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid input. Using Chrome.");
				driver = new ChromeDriver();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchForCarsByMake() throws IOException {
		extentTest = reportManager.setUpTest("testSearchForCarsByMake");
		extentTest.info(
				"This test will enter a post code and select Mercedes-Benz under make, click search and then check if the make filter is set to Mercedes-Benz");
		try {
			wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			homePage = new AutoTraderHome(driver);
			driver.navigate().to(autoTraderURL);
			WebElement postcodeInput = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver webDriver) {
					return homePage.getPostcodeInput();
				}
			});
			WebElement makeSelect = homePage.getMakeSelect();
			WebElement searchButton = homePage.getSearchButton();
			postcodeInput.clear();
			postcodeInput.sendKeys("M16 8BQ");
			makeSelect.click();
			makeSelect.sendKeys("mercedes-benz");
			postcodeInput.click();
			searchButton.click();
			WebElement makeFilter = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver webDriver) {
					return webDriver.findElement(
							By.xpath("/html/body/main/section[1]/div[1]/form/ul/li[3]/div/button/span/span[2]"));
				}
			});
			assertEquals("Mercedes-Benz", makeFilter.getText());
			extentTest.pass("testSearchForCarsByMake passed");
		} catch (AssertionError e) {
			String details = "testSearchForCarByMake failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testSearchForCarsByMake"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testSearchForCarByMake failed due to Exception: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testSearchForCarsByMake"));
			Assert.fail(details);
		}
	}

	@Test
	public void testResetSearch() throws IOException {
		extentTest = reportManager.setUpTest("testResetSearch");
		extentTest.info(
				"This test will go to the more options search page, click the aditional ads box, click reset search and check if the box is then unticked.");
		try {
			homePage = new AutoTraderHome(driver);
			wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
			driver.navigate().to(autoTraderURL);
			WebElement moreOptionsLink = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver webDriver) {
					return homePage.getMoreOptionsLink();
				}
			});
			WebElement postcodeInput = homePage.getPostcodeInput();
			postcodeInput.clear();
			postcodeInput.sendKeys("M168BQ");
			moreOptionsLink.click();
			WebElement additionalAdsCheckbox = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver webDriver) {
					return webDriver.findElement(By.xpath("//*[@id=\"js-known-search-advanced\"]/div[3]/div[4]/div[2]/label/span"));
				}
			});
			additionalAdsCheckbox.click();
			wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//*[@id=\"js-known-search-advanced\"]/div[3]/div[4]/div[2]/label/span")), "class", "custom-checkbox homepage-sprite selected"));
			WebElement resetSearch = driver.findElement(By.xpath("//*[@id=\"js-known-search-advanced\"]/div[3]/div[5]/div[2]/span"));
			resetSearch.click();
			assertTrue(wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//*[@id=\"js-known-search-advanced\"]/div[3]/div[4]/div[2]/label/span")), "class", "custom-checkbox homepage-sprite")));
			extentTest.pass("testResetSearch");
		} catch (AssertionError e) {
			String details = "testResetSearch failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testResetSearch"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testResetSearch failed due to Exception: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testResetSearch"));
			Assert.fail(details);
		}
	}

	@Test
	public void testCheckVehicle() {
		extentTest = reportManager.setUpTest("testCheckVehicle");
		extentTest.info(
				"This test will click 'vehicle check' under the 'finance, insurance & more' tab, enter a valid reg number and check that the correct car is found.");
		//TODO
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
