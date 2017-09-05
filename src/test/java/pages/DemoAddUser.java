package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoAddUser {

	WebDriver driver;
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	WebElement loginLink;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
	WebElement usernameInput;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
	WebElement passwordInput;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")
	WebElement saveButton;
	
	public DemoAddUser(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	public WebElement getLoginLink() {
		return loginLink;
	}
	public WebElement getUsernameInput() {
		return usernameInput;
	}
	public WebElement getPasswordInput() {
		return passwordInput;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void fillAndSubmitDetails(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		saveButton.click();
	}
	
}
