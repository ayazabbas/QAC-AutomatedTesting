package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MouseDemoDroppable {

	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"draggableview\"]/p")
	WebElement defaultDragBox;
	@FindBy(xpath = "//*[@id=\"droppableview\"]")
	WebElement defaultTargetBox;
	@FindBy(xpath = "//*[@id=\"droppableview\"]/p")
	WebElement defaultTargetBoxText;
	@FindBy(xpath = "//*[@id=\"ui-id-5\"]")
	WebElement shoppingCartTab;
	@FindBy(xpath = "//*[@id=\"ui-id-7\"]/ul/li[3]")
	WebElement bucketShirt;
	@FindBy(xpath = "//*[@id=\"ui-id-8\"]/a")
	WebElement bagsTab;
	@FindBy(xpath = "//*[@id=\"ui-id-9\"]/ul/li[2]")
	WebElement blackLeather;
	@FindBy(xpath = "//*[@id=\"ui-id-10\"]/a")
	WebElement gadgetsTab;
	@FindBy(xpath = "//*[@id=\"ui-id-11\"]/ul/li[1]")
	WebElement iPhone;
	@FindBy(xpath = "//*[@id=\"cart\"]/div/ol")
	WebElement shoppingCart;

	public MouseDemoDroppable(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getDefaultDragBox() {
		return defaultDragBox;
	}

	public WebElement getDefaultTargetBox() {
		return defaultTargetBox;
	}

	public String getDefaultTargetBoxText() {
		return defaultTargetBoxText.getText();
	}

	public WebElement getShoppingCartTab() {
		return shoppingCartTab;
	}

	public WebElement getBucketShirt() {
		return bucketShirt;
	}

	public WebElement getBagsTab() {
		return bagsTab;
	}

	public WebElement getBlackLeather() {
		return blackLeather;
	}

	public WebElement getGadgetsTab() {
		return gadgetsTab;
	}

	public WebElement getiPhone() {
		return iPhone;
	}

	public WebElement getShoppingCart() {
		return shoppingCart;
	}

}
