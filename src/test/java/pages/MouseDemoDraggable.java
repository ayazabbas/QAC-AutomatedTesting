package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MouseDemoDraggable {
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"draggable\"]")
	WebElement defaultDragBox;
	
	@FindBy(xpath = "//*[@id=\"ui-id-2\"]")
	WebElement constrainTab;
	
	@FindBy(xpath = "//*[@id=\"draggabl\"]/p")
	WebElement verticalDrag;
	
	@FindBy(xpath = "//*[@id=\"draggabl2\"]/p")
	WebElement horizontalDrag;
	
	public MouseDemoDraggable(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getHorizontalDrag() {
		return horizontalDrag;
	}

	public WebElement getConstrainTab() {
		return constrainTab;
	}

	public WebElement getVerticalDrag() {
		return verticalDrag;
	}

	public WebElement getDefaultDragBox() {
		return defaultDragBox;
	}
	
}
