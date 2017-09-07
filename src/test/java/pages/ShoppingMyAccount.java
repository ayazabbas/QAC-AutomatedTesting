package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingMyAccount {
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[3]/a")
	WebElement tshirtsLink;
	@FindBy(xpath = "//*[@id=\"columns\"]/div[1]/span[2]")
	WebElement navigatorLabel;
	@FindBy(xpath = "//*[@id=\"center_column\"]/div/div[2]/ul/li/a/span")
	WebElement wishlistLink;
	@FindBy(xpath = "//*[@id=\"wishlist_1781\"]/td[1]/a")
	WebElement tshirtsWishlist;
	@FindBy(xpath = "//*[@id=\"wlp_1_0\"]/div/div[1]/div/a/img")
	WebElement tshirtImage;
	
	public ShoppingMyAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getTshirtsLink() {
		return tshirtsLink;
	}

	public WebElement getNavigatorLabel() {
		return navigatorLabel;
	}

	public WebElement getWishlistLink() {
		return wishlistLink;
	}

	public WebElement getTshirtsWishlist() {
		return tshirtsWishlist;
	}

	public WebElement getTshirtImage() {
		return tshirtImage;
	}
	
}
