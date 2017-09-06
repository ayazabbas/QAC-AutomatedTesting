package tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Function;

import pages.MyAccount;
import pages.ShoppingCreateAccount;
import pages.ShoppingSignIn;

public class ShoppingTests {

	private static WebDriver driver;
	private ShoppingSignIn signInPage;
	private ShoppingCreateAccount createAccountPage;
	private MyAccount myAccountPage;
	private static ExtentTest extentTest;
	private static ExtentReportManager reportManager;
	private static FluentWait<WebDriver> wait;

	@BeforeClass
	public static void init() {
		driver = new ChromeDriver();
		String property = System.getProperty("user.dir");
		ReportDetails reportDetails = new ReportDetails(property + "\\testReports\\ShoppingTestsReport",
				"Basic Extent Report", "Basic Report");
		reportDetails.setTheme(Theme.DARK);
		reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
		wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(500,
				TimeUnit.MILLISECONDS);
	}

	@Test
	public void testCreateAccount() throws IOException {
		extentTest = reportManager.setUpTest("testCreateAccount");
		extentTest.info(
				"This test will create an account with details provided in a spreadsheet and attempt to sign in.");
		try {
			SpreadSheetReader sheetReader = new SpreadSheetReader(
					System.getProperty("user.dir") + "/spreadsheets/shopping.xlsx");
			List<String> row = sheetReader.readRow(1, "createAccount");
			System.out.println(row);
			String email = row.get(3);
			String title = row.get(0);
			String fName = row.get(1);
			String lName = row.get(2);
			String password = row.get(4);
			String dobDay = row.get(5);
			String dobMonth = row.get(6);
			String dobYear = row.get(7);
			String newsletter = row.get(8);
			String partnerOffers = row.get(9);
			String addressFName = row.get(10);
			String addressLName = row.get(11);
			String company = row.get(12);
			String address = row.get(13);
			String address2 = row.get(14);
			String city = row.get(15);
			String state = row.get(16);
			String zip = row.get(17);
			String additional = row.get(18);
			String homeNum = row.get(19);
			String mobileNum = row.get(20);
			String addressAlias = row.get(21);

			signInPage = new ShoppingSignIn(driver);
			driver.navigate().to(ShoppingSignIn.getURL());
			WebElement createEmailInput = signInPage.getCreateEmailInput();
			wait.until(ExpectedConditions.elementToBeClickable(createEmailInput));
			createEmailInput.sendKeys(email);
			signInPage.getCreateButton().click();
			final WebElement firstNameInput = createAccountPage.getFirstNameInput();
			wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
			firstNameInput.sendKeys(fName);

			switch (title) {
			case "mr":
				createAccountPage.getMrRadioButton().click();
				break;
			case "mrs":
				createAccountPage.getMrsRadioButton().click();
				break;
			}

			createAccountPage.getLastNameInput().sendKeys(lName);
			createAccountPage.getPasswordInput().sendKeys(password);
			WebElement dobDaySelect = createAccountPage.getDobDay();
			WebElement dobMonthSelect = createAccountPage.getDobMonth();
			WebElement dobYearSelect = createAccountPage.getDobYear();
			dobDaySelect.sendKeys(dobDay);
			dobDaySelect.click();
			dobMonthSelect.sendKeys(dobMonth);
			dobMonthSelect.click();
			dobYearSelect.sendKeys(dobYear);
			dobYearSelect.click();

			if (newsletter == "y") {
				final WebElement newsletterCheckBox = createAccountPage.getNewsletterCheckbox();
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver webDriver) {
						if (newsletterCheckBox.getAttribute("class") == "checked")
							return true;
						newsletterCheckBox.click();
						return false;
					}
				});
			}
			if (partnerOffers == "y") {
				final WebElement partnersCheckBox = createAccountPage.getPartnersCheckbox();
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver webDriver) {
						if (partnersCheckBox.getAttribute("class") == "checked")
							return true;
						partnersCheckBox.click();
						return false;
					}
				});
			}

			if (addressFName != fName) {
				WebElement addressFNameInput = createAccountPage.getAddressFirstNameInput();
				addressFNameInput.clear();
				addressFNameInput.sendKeys(addressFName);
			}
			if (addressLName != lName) {
				WebElement addressLNameInput = createAccountPage.getAddressLastNameInput();
				addressLNameInput.clear();
				addressLNameInput.sendKeys(addressFName);
			}

			createAccountPage.getCompanyInput().sendKeys(company);
			createAccountPage.getAddressInput().sendKeys(address);
			if (address2 != null)
				createAccountPage.getAddress2Input().sendKeys(address2);
			createAccountPage.getCityInput().sendKeys(city);

			WebElement stateSelect = createAccountPage.getStateSelect();
			stateSelect.sendKeys(state);
			stateSelect.click();
			createAccountPage.getZipCodeInput().sendKeys(zip);

			if (homeNum != null)
				createAccountPage.getHomePhoneInput().sendKeys(homeNum);
			createAccountPage.getMobilePhoneInput().sendKeys(mobileNum);
			if (additional != null)
				createAccountPage.getAdditionalInfoInput().sendKeys(additional);
			createAccountPage.getAddressAliasInput().sendKeys(addressAlias);
			createAccountPage.getRegisterButton().click();

			final WebElement alertBox = createAccountPage.getAlertBox();
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver webDriver) {
					if (!ExpectedConditions.invisibilityOf(alertBox).apply(webDriver))
						return true;
					if (ExpectedConditions.invisibilityOf(firstNameInput).apply(webDriver))
						return true;
					return false;
				}
			});

			myAccountPage = new MyAccount(driver);
			assertTrue(myAccountPage.getNavigatorLabel().getText() == "My Account");
			extentTest.pass("testCreateAccount passed");
		} catch (AssertionError e) {
			String details = "testCreateAccountAndSignIn failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testCreateAccountFail"));
			Assert.fail(details);
		} /*
			 * catch (Exception e) { String details =
			 * "testCreateAccountAndSignIn failed due to Exception: " + e.getMessage();
			 * extentTest.fail(details);
			 * extentTest.addScreenCaptureFromPath(ScreenShot.take(driver,
			 * "testCreateAccountFail")); Assert.fail(details); }
			 */
	}

	@AfterClass
	public static void cleanUp() {
		try {
			driver.quit();
		} catch (WebDriverException e) {
			String details = "LoginTest encountered exception at tearDown: " + e.getMessage();
			extentTest.warning(details);
		}
		reportManager.clearTests();
	}

}
