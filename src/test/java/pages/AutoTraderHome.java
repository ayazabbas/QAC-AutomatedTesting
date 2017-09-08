package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoTraderHome {

	WebDriver driver;
	
	@FindBy(css = "#searchVehiclesMake")
	WebElement makeSelect;
	@FindBy(xpath = "//*[@id=\"search\"]/span")
	WebElement searchButton;
	@FindBy(css = "#postcode")
	WebElement postcodeInput;
	@FindBy(xpath = "//*[@id=\"searchVehicles\"]/div/div[5]/div/span")
	WebElement moreOptionsLink;
	
	public AutoTraderHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getMakeSelect() {
		return makeSelect;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	public WebElement getPostcodeInput() {
		return postcodeInput;
	}

	public WebElement getMoreOptionsLink() {
		return moreOptionsLink;
	}
	
}
