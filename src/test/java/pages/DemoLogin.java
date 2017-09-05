package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoLogin {
	
	WebDriver driver;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")
	WebElement loginButton;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
	WebElement usernameInput;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
	WebElement passwordInput;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	WebElement loginInfoText;
	
	public DemoLogin(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	public WebElement getLoginButton() {
		return loginButton;
	}
	public WebElement getUsernameInput() {
		return usernameInput;
	}
	public WebElement getPasswordInput() {
		return passwordInput;
	}
	public String getLoginInfoText() {
		return loginInfoText.getText();
	}
	
	public void fillAndSubmitDetails(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
	}
}
