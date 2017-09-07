package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Function;

import pages.ShoppingCreateAccount;
import pages.ShoppingMyAccount;
import pages.ShoppingSignIn;
import pages.ShoppingTShirts;

public class ShoppingTests {

	private WebDriver driver;
	private ShoppingSignIn signInPage;
	private ShoppingCreateAccount createAccountPage;
	private ShoppingTShirts tShirtPage;
	private ShoppingMyAccount myAccountPage;
	private static ExtentTest extentTest;
	private static ExtentReportManager reportManager;
	private FluentWait<WebDriver> wait;
	private static SpreadSheetReader sheetReader;

	@BeforeClass
	public static void init() {
		String property = System.getProperty("user.dir");
		sheetReader = new SpreadSheetReader(property + "/spreadsheets/shopping.xlsx");
		ReportDetails reportDetails = new ReportDetails(property + "\\testReports\\ShoppingTestsReport",
				"Basic Extent Report", "Basic Report");
		reportDetails.setTheme(Theme.DARK);
		reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
	}

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
	}

	/**
	 * @throws IOException
	 */
	@Test
	public void testCreateAccount() throws IOException {
		extentTest = reportManager.setUpTest("testCreateAccount");
		extentTest.info(
				"This test will create an account with details provided in a spreadsheet and attempt to sign in.");
		try {
			List<String> row = sheetReader.readRow(1, "createAccount");
			System.out.println(row);
			String email = row.get(3);
			String title = row.get(0);
			String fName = row.get(1);
			String lName = row.get(2);
			String password = row.get(4);
			Double d = new Double(Double.parseDouble(row.get(5)));
			String dobDay = String.valueOf(d.intValue());
			String dobMonth = row.get(6);
			d = new Double(Double.parseDouble(row.get(7)));
			String dobYear = String.valueOf(d.intValue());
			String newsletter = row.get(8);
			String partnerOffers = row.get(9);
			String addressFName = row.get(10);
			String addressLName = row.get(11);
			String company = row.get(12);
			String address = row.get(13);
			String address2 = row.get(14);
			String city = row.get(15);
			String state = row.get(16);
			d = new Double(Double.parseDouble(row.get(17)));
			String zip = String.valueOf(d.intValue());
			String additional = row.get(18);
			String homeNum = row.get(19);
			if (homeNum != null) {
				homeNum = homeNum.substring(1);
			}
			String mobileNum = row.get(20).substring(1);
			String addressAlias = row.get(21);

			signInPage = new ShoppingSignIn(driver);
			createAccountPage = new ShoppingCreateAccount(driver);

			driver.navigate().to(ShoppingSignIn.getURL());
			WebElement createEmailInput = signInPage.getCreateEmailInput();
			wait.until(ExpectedConditions.elementToBeClickable(createEmailInput));
			createEmailInput.sendKeys(email);
			signInPage.getCreateButton().click();
			wait.until(ExpectedConditions.elementToBeClickable(createAccountPage.getFirstNameInput()));
			final WebElement firstNameInput = createAccountPage.getFirstNameInput();
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
			WebElement addressAliasInput = createAccountPage.getAddressAliasInput();
			addressAliasInput.clear();
			addressAliasInput.sendKeys(addressAlias);
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

			System.out.println(driver.findElement(By.xpath("//*[@id=\"columns\"]/div[1]/span[2]")).getText());
			assertEquals("My account", driver.findElement(By.xpath("//*[@id=\"columns\"]/div[1]/span[2]")).getText());
			extentTest.pass("testCreateAccount passed");
		} catch (AssertionError e) {
			String details = "testCreateAccountAndSignIn failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testCreateAccountFail"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testCreateAccountAndSignIn failed due to Exception: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testCreateAccountFail"));
			Assert.fail(details);
		}
	}

	@Test
	public void testAddToCart() throws IOException {
		extentTest = reportManager.setUpTest("testAddToCart");
		extentTest.info("This test will add a tshirt to the shopping cart and check if it appears in the cart");
		try {
			tShirtPage = new ShoppingTShirts(driver);
			driver.navigate().to("http://automationpractice.com/index.php");
			driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
			WebElement tShirtImage = tShirtPage.gettShirtImage();
			wait.until(ExpectedConditions.elementToBeClickable(tShirtImage));
			moveMouse(tShirtImage);
			tShirtPage.getAddButton().click();
			WebElement continueShoppingButton = tShirtPage.getContinueShoppingButton();
			wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
			continueShoppingButton.click();
			moveMouse(tShirtPage.getCartDropDown());
			assertEquals("Faded Short Sleeve T-shirts", tShirtPage.gettShirtTitleInCart().getAttribute("title"));
			extentTest.pass("testAddToCart passed");
		} catch (AssertionError e) {
			String details = "testAddToCart failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testAddToCart"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testAddToCart failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testAddToCart"));
			Assert.fail(details);
		}
	}

	@Test
	public void testSearchProduct() throws IOException {
		extentTest = reportManager.setUpTest("testSearchProduct");
		extentTest.info("This test will search for t shirt and check that the matching product is found");
		try {
			driver.navigate().to("http://automationpractice.com/index.php");
			driver.findElement(By.xpath("//*[@id=\"search_query_top\"]")).sendKeys("t shirt");
			driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
			;
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a"))));
			WebElement resultTitle = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a"));
			assertEquals("Faded Short Sleeve T-shirts", resultTitle.getAttribute("title"));
			extentTest.pass("testSearchProduct");
		} catch (AssertionError e) {
			String details = "testSearchProduct failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testSearchProduct"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testSearchProduct failed due to Exception: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testSearchProduct"));
			Assert.fail(details);
		}
	}

	@Test
	public void testSortByHighPrice() throws IOException {
		extentTest = reportManager.setUpTest("testSortByHighPrice");
		extentTest.info(
				"This test will browse dresses, sort by highest price and check that the first dress is the most expensive");
		try {
			driver.navigate().to("http://automationpractice.com/index.php");
			driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();
			WebElement sortSelect = driver.findElement(By.xpath("//*[@id=\"selectProductSort\"]"));
			sortSelect.sendKeys("price: h");
			sortSelect.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions
					.invisibilityOf(driver.findElement(By.xpath("//*[@id=\"layered_ajax_loader\"]/p/img"))));
			WebElement firstProductPrice = driver
					.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[1]/span"));
			assertEquals("$50.99", firstProductPrice.getText());
			extentTest.pass("testSortByPrice passed");
		} catch (AssertionError e) {
			String details = "testSortByPrice failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testSortByPrice"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testSortByPrice failed due to Exception: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testSortByPrice"));
			Assert.fail(details);
		}
	}

	@Test
	public void testAddToWishlist() throws IOException {
		extentTest = reportManager.setUpTest("testSortByHighPrice");
		extentTest.info(
				"This test will sign in, add a t shirt to the wishlist tshirts and check to see that it has been added");
		try {
			String email = "ayaz@ayaz.com";
			String password = "dragon";
			signInPage = new ShoppingSignIn(driver);
			myAccountPage = new ShoppingMyAccount(driver);
			tShirtPage = new ShoppingTShirts(driver);
			driver.navigate().to(ShoppingSignIn.getURL());
			signInPage.getRegisteredEmailInput().sendKeys(email);
			signInPage.getPasswordInput().sendKeys(password);
			signInPage.getSignInButton().click();
			myAccountPage.getTshirtsLink().click();
			moveMouse(tShirtPage.gettShirtImage());
			tShirtPage.getAddToWishlist().click();
			wait.until(ExpectedConditions.elementToBeClickable(tShirtPage.getCloseWishlistAlert()));
			tShirtPage.getCloseWishlistAlert().click();
			wait.until(ExpectedConditions.elementToBeClickable(tShirtPage.getMyAccountLink()));
			tShirtPage.getMyAccountLink().click();
			myAccountPage.getWishlistLink().click();
			myAccountPage.getTshirtsWishlist().click();
			wait.until(ExpectedConditions.elementToBeClickable(myAccountPage.getTshirtImage()));
			assertTrue(myAccountPage.getTshirtImage().isDisplayed());
			extentTest.pass("testAddToWishlist passed");
		} catch (AssertionError e) {
			String details = "testAddToWishlist failed due to AssertionError: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testAddToWishlist"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testAddToWishlist failed due to Exception: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testAddToWishlist"));
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

	private void moveMouse(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e);
		action.build().perform();
	}
}
