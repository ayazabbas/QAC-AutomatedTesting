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
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement registeredEmailInput;
	@FindBy(xpath = "//*[@id=\"passwd\"]")
	WebElement passwordInput;
	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]/span")
	WebElement signInButton;
	
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

	public WebElement getRegisteredEmailInput() {
		return registeredEmailInput;
	}

	public WebElement getPasswordInput() {
		return passwordInput;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}
	
}
