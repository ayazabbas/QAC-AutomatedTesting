package ayaz.autoTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleSelenium {

	WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() {
		driver.navigate().to("http:/www.qa.com");
		assertEquals("IT Training | Project Management Training | Business Skills Training | QA", driver.getTitle());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
