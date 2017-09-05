package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pages.MouseDemoDraggable;
import pages.MouseDemoDroppable;

public class MouseActionTests {

	private static WebDriver driver;
	private MouseDemoDraggable draggablePage;
	private MouseDemoDroppable droppablePage;
	private FluentWait<WebDriver> wait;
	private static ExtentTest extentTest;
	private static ExtentReportManager reportManager;

	@BeforeClass
	public static void init() {
		driver = new ChromeDriver();
		String property = System.getProperty("user.dir");
		ReportDetails reportDetails = new ReportDetails(property + "\\testReports\\MouseActionTestsReport",
				"Basic Extent Report", "Basic Report");
		reportDetails.setTheme(Theme.DARK);
		reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
	}

	@Before
	public void setUp() {
		wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(NoSuchElementException.class);
	}

	@Test
	public void testDefaultDrag() throws IOException {
		extentTest = reportManager.setUpTest("testDefaultDrag");
		extentTest.info(
				"This test will move the draggable box on the default tab of the draggable page and check that its location has changed.");
		try {
			driver.navigate().to("http://demoqa.com/draggable/");
			draggablePage = new MouseDemoDraggable(driver);
			WebElement dragBox = draggablePage.getDefaultDragBox();
			wait.until(ExpectedConditions.elementToBeClickable(dragBox));
			Point startLocation = dragBox.getLocation();
			dragAndDrop(100, 100, dragBox);
			assertTrue(!dragBox.getLocation().equals(startLocation));
			extentTest.pass("testDefaultDrag passed");
		} catch (AssertionError e) {
			String details = "testDefaultDrag failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testDefaultDragFail"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testDefaultDrag failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testDefaultDragFail"));
			Assert.fail(details);
		}
	}

	@Test
	public void testVerticalDrag() throws IOException {
		extentTest = reportManager.setUpTest("testVerticalDrag");
		extentTest.info(
				"This test will move the vertically draggable box on the constrain movement tab of the draggable page and check that only its y coordinate has changed.");
		try {
		driver.navigate().to("http://demoqa.com/draggable/");
		draggablePage = new MouseDemoDraggable(driver);
		draggablePage.getConstrainTab().click();
		WebElement verticalDragBox = draggablePage.getVerticalDrag();
		wait.until(ExpectedConditions.elementToBeClickable(verticalDragBox));
		Point startLocation = verticalDragBox.getLocation();
		dragAndDrop(100, 100, verticalDragBox);
		assertTrue(verticalDragBox.getLocation().getX() == startLocation.getX()
				&& verticalDragBox.getLocation().getY() != startLocation.getY());
		extentTest.pass("testVerticalDrag passed");
		} catch (AssertionError e) {
			String details = "testVerticalDrag failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testVerticalDragFail"));
			Assert.fail(details);
		} catch (Exception e) {
			String details = "testVerticalDrag failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testVerticalDragFail"));
			Assert.fail(details);
		}
	}

	@Test
	public void testHorizontalDrag() throws IOException {
		extentTest = reportManager.setUpTest("testHorizontalDrag");
		extentTest.info(
				"This test will move the horizontally draggable box on the constrain movement tab of the draggable page and check that only its x coordinate has changed.");
		try {
		driver.navigate().to("http://demoqa.com/draggable/");
		draggablePage = new MouseDemoDraggable(driver);
		draggablePage.getConstrainTab().click();
		WebElement horizontalDragBox = draggablePage.getHorizontalDrag();
		wait.until(ExpectedConditions.elementToBeClickable(horizontalDragBox));
		Point startLocation = horizontalDragBox.getLocation();
		dragAndDrop(100, 100, horizontalDragBox);
		assertTrue(horizontalDragBox.getLocation().getX() != startLocation.getX()
				&& horizontalDragBox.getLocation().getY() == startLocation.getY());
		extentTest.pass("testHorizontalDrag passed");
		} catch (AssertionError e) {
			String details = "testHorizontalDrag failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testHorizontalDragFail"));
			Assert.fail(details);
		} catch(Exception e) {
			String details = "testHorizontalDrag failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testHorizontalDragFail"));
			Assert.fail(details);
		}
	}

	@Test
	public void testDragAndDrop() throws IOException {
		extentTest = reportManager.setUpTest("testDragAndDrop");
		extentTest.info(
				"This test will move the draggable box on the default tab of the droppable page into the 'drop here' box.");
		try {
		driver.navigate().to("http://demoqa.com/droppable/");
		droppablePage = new MouseDemoDroppable(driver);
		WebElement dragBox = droppablePage.getDefaultDragBox();
		WebElement targetBox = droppablePage.getDefaultTargetBox();
		wait.until(ExpectedConditions.elementToBeClickable(dragBox));
		dragAndDrop(dragBox, targetBox);
		assertEquals(droppablePage.getDefaultTargetBoxText(), "Dropped!");
		extentTest.pass("testDragAndDrop passed");
		} catch (AssertionError e) {
			String details = "testDragAndDrop failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testDragAndDropFail"));
			Assert.fail(details);
		} catch(Exception e) {
			String details = "testDragAndDrop failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testDragAndDropFail"));
			Assert.fail(details);
		}
	}

	@Test
	public void testShoppingCart() throws IOException {
		extentTest = reportManager.setUpTest("testShoppingCart");
		extentTest.info(
				"This test will go to the shopping cart tab on the droppable page and add 'Buckit Shirt', 'Black Leather' and 'iPhone' to the cart.");
		try {
		driver.navigate().to("http://demoqa.com/droppable/");
		droppablePage = new MouseDemoDroppable(driver);

		WebElement shoppingCartTab = droppablePage.getShoppingCartTab();
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCartTab));
		shoppingCartTab.click();
		WebElement bucketShirt = droppablePage.getBucketShirt();
		WebElement shoppingCart = droppablePage.getShoppingCart();
		dragAndDrop(bucketShirt, shoppingCart);

		droppablePage.getBagsTab().click();
		WebElement blackLeather = droppablePage.getBlackLeather();
		wait.until(ExpectedConditions.invisibilityOf(bucketShirt));
		dragAndDrop(blackLeather, shoppingCart);

		droppablePage.getGadgetsTab().click();
		WebElement iPhone = droppablePage.getiPhone();
		wait.until(ExpectedConditions.invisibilityOf(blackLeather));
		dragAndDrop(iPhone, shoppingCart);
		String cartText = shoppingCart.getText();
		assertTrue(
				cartText.contains("Buckit Shirt") && cartText.contains("Black Leather") && cartText.contains("iPhone"));
		extentTest.pass("testShoppingCart passed");
		} catch (AssertionError e) {
			String details = "testShoppingCart failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testShoppingCartFail"));
			Assert.fail(details);
		} catch(Exception e) {
			String details = "testShoppingCart failed: " + e.getMessage();
			extentTest.fail(details);
			extentTest.addScreenCaptureFromPath(ScreenShot.take(driver, "testShoppingCartFail"));
			Assert.fail(details);
		}
	}

	@AfterClass
	public static void tearDown() {
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

	public void dragAndDrop(int xOffset, int yOffset, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.clickAndHold();
		action.moveByOffset(xOffset, yOffset);
		action.release();
		action.build().perform();
	}

	public void dragAndDrop(WebElement elementToDrag, WebElement targetElement) {
		Actions action = new Actions(driver);
		action.dragAndDrop(elementToDrag, targetElement);
		action.build().perform();
	}

}
