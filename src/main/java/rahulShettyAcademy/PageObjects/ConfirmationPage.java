package rahulShettyAcademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractCompanents;

public class ConfirmationPage extends AbstractCompanents {
	 WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Initialize all elements in the page using PageFactory
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary") WebElement confirmationMessage;
	By orderSummeryBY =By.cssSelector(".order-summary-box");
	public String getConfirmationMessage() {
		// Wait for the order summary to appear
		waitForElementToAppear(orderSummeryBY);
		// Return the text of the confirmation message
		return confirmationMessage.getText();
    }
	
}
