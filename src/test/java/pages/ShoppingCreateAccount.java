package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCreateAccount {

	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"id_gender1\"]")
	WebElement mrRadioButton;
	@FindBy(xpath = "//*[@id=\"id_gender2\"]")
	WebElement mrsRadioButton;
	@FindBy(xpath = "//*[@id=\"customer_firstname\"]")
	WebElement firstNameInput;
	@FindBy(xpath = "//*[@id=\"customer_lastname\"]")
	WebElement lastNameInput;
	@FindBy(xpath = "//*[@id=\"passwd\"]")
	WebElement passwordInput;
	@FindBy(xpath = "//*[@id=\"days\"]")
	WebElement dobDay;
	@FindBy(xpath = "//*[@id=\"months\"]")
	WebElement dobMonth;
	@FindBy(xpath = "//*[@id=\"years\"]")
	WebElement dobYear;
	@FindBy(xpath = "//*[@id=\"uniform-newsletter\"]/span")
	WebElement newsletterCheckbox;
	@FindBy(xpath = "//*[@id=\"uniform-optin\"]/span")
	WebElement partnersCheckbox;
	@FindBy(xpath = "//*[@id=\"firstname\"]")
	WebElement addressFirstNameInput;
	@FindBy(xpath = "//*[@id=\"lastname\"]")
	WebElement addressLastNameInput;
	@FindBy(xpath = "//*[@id=\"company\"]")
	WebElement companyInput;
	@FindBy(xpath = "//*[@id=\"address1\"]")
	WebElement addressInput;
	@FindBy(xpath = "//*[@id=\"address2\"]")
	WebElement address2Input;
	@FindBy(xpath = "//*[@id=\"city\"]")
	WebElement cityInput;
	@FindBy(xpath = "//*[@id=\"id_state\"]")
	WebElement stateSelect;
	@FindBy(xpath = "//*[@id=\"postcode\"]")
	WebElement zipCodeInput;
	@FindBy(xpath="//*[@id=\"id_country\"]")
	WebElement countrySelect;
	@FindBy(xpath = "//*[@id=\"other\"]")
	WebElement additionalInfoInput;
	@FindBy(xpath = "//*[@id=\"phone\"]")
	WebElement homePhoneInput;
	@FindBy(xpath = "//*[@id=\"phone_mobile\"]")
	WebElement mobilePhoneInput;
	@FindBy(xpath = "//*[@id=\"alias\"]")
	WebElement addressAliasInput;
	@FindBy(xpath = "//*[@id=\"submitAccount\"]/span")
	WebElement registerButton;
	@FindBy(xpath = "//*[@id=\"center_column\"]/div")
	WebElement alertBox;

	public ShoppingCreateAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getMrRadioButton() {
		return mrRadioButton;
	}

	public WebElement getMrsRadioButton() {
		return mrsRadioButton;
	}

	public WebElement getFirstNameInput() {
		return firstNameInput;
	}

	public WebElement getLastNameInput() {
		return lastNameInput;
	}

	public WebElement getPasswordInput() {
		return passwordInput;
	}

	public WebElement getDobDay() {
		return dobDay;
	}

	public WebElement getDobMonth() {
		return dobMonth;
	}

	public WebElement getDobYear() {
		return dobYear;
	}

	public WebElement getNewsletterCheckbox() {
		return newsletterCheckbox;
	}

	public WebElement getPartnersCheckbox() {
		return partnersCheckbox;
	}

	public WebElement getAddressFirstNameInput() {
		return addressFirstNameInput;
	}

	public WebElement getAddressLastNameInput() {
		return addressLastNameInput;
	}

	public WebElement getCompanyInput() {
		return companyInput;
	}

	public WebElement getAddressInput() {
		return addressInput;
	}

	public WebElement getAddress2Input() {
		return address2Input;
	}

	public WebElement getCityInput() {
		return cityInput;
	}

	public WebElement getStateSelect() {
		return stateSelect;
	}

	public WebElement getZipCodeInput() {
		return zipCodeInput;
	}

	public WebElement getCountrySelect() {
		return countrySelect;
	}

	public WebElement getAdditionalInfoInput() {
		return additionalInfoInput;
	}

	public WebElement getHomePhoneInput() {
		return homePhoneInput;
	}

	public WebElement getMobilePhoneInput() {
		return mobilePhoneInput;
	}

	public WebElement getAddressAliasInput() {
		return addressAliasInput;
	}

	public WebElement getRegisterButton() {
		return registerButton;
	}

	public WebElement getAlertBox() {
		return alertBox;
	}

}
