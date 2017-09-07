package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingTShirts {

	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img")
	WebElement tShirtImage;
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span")
	WebElement addButton;
	@FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span/span")
	WebElement continueShoppingButton;
	@FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/span[2]")
	WebElement cartDropDown;
	@FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt/div/div[1]/a")
	WebElement tShirtTitleInCart;
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[3]/div[1]/a")
	WebElement addToWishlist;
	@FindBy(xpath = "//*[@id=\"category\"]/div[2]/div/div/a")
	WebElement closeWishlistAlert;
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")
	WebElement myAccountLink;

	public ShoppingTShirts(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement gettShirtImage() {
		return tShirtImage;
	}

	public WebElement getAddButton() {
		return addButton;
	}

	public WebElement getContinueShoppingButton() {
		return continueShoppingButton;
	}

	public WebElement getCartDropDown() {
		return cartDropDown;
	}

	public WebElement gettShirtTitleInCart() {
		return tShirtTitleInCart;
	}

	public WebElement getAddToWishlist() {
		return addToWishlist;
	}

	public WebElement getCloseWishlistAlert() {
		return closeWishlistAlert;
	}

	public WebElement getMyAccountLink() {
		return myAccountLink;
	}

}
