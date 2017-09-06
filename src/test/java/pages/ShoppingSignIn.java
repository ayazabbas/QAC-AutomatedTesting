package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingSignIn {

	static String URL = "http://automationpractice.com/index.php?controller=authentication";
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"email_create\"]")
	WebElement createEmailInput;
	@FindBy(xpath = "//*[@id=\"SubmitCreate\"]/span")
	WebElement createButton;
	
	public ShoppingSignIn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateEmailInput() {
		return createEmailInput;
	}

	public WebElement getCreateButton() {
		return createButton;
	}

	public static String getURL() {
		return URL;
	}
	
}
