package ayaz.autoTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {
	
	WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testCase() {
		driver.navigate().to("http://www.qa.com");
	}
	
}