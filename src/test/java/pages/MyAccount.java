package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount {

	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"columns\"]/div[1]/span[2]")
	WebElement navigatorLabel;
	
	public MyAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getNavigatorLabel() {
		return navigatorLabel;
	}
	
}
