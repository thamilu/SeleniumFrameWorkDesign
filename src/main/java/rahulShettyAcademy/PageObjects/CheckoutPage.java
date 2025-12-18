package rahulShettyAcademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractCompanents;

public class CheckoutPage extends AbstractCompanents {
WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Initialize all elements in the page using PageFactory
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "[placeholder='Select Country']") WebElement countryInput;
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]") WebElement countrySelectionButton;
	@FindBy(css =".action__submit") WebElement submitButton;
	By countryResults = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException {		
		Actions a = new Actions(driver);
		Thread.sleep(5000);
		a.sendKeys(countryInput, "india").build().perform();
        waitForElementToAppear(countryResults);
        countrySelectionButton .click();
			}

			public ConfirmationPage submitOrder() {
				submitButton.click();
				return new ConfirmationPage(driver);
			}

}
