package rahulShettyAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulShettyAcademy.AbstractComponents.AbstractCompanents;

public class CartPage extends AbstractCompanents {
WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	@FindBy(css = ".cartSection h3") List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button") WebElement checkoutElement;
	By cartProductBy = By.cssSelector(".cartWrap");

	public Boolean checkProductIsDisplayed(String productName) throws InterruptedException {
		waitForElementToAppear(cartProductBy);
//		 Thread.sleep(2000);
		Boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase("ZARA COAT 3"));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutElement.click();
//		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return new CheckoutPage(driver);
	}
//	private By product = By.cssSelector(".cartSection h3");
//	private By checkout = By.cssSelector(".totalRow button");
//	private By country = By.cssSelector("[placeholder='Select Country']");
//	private By countryResults = By.xpath("(//button[contains(@class, 'ta-item')])[2]");
//
//	public List<WebElement> getCartProducts() {
//		return findAll(product);
//	}
//
//	public void goToCheckout() {
//		find(checkout).click();
//	}
//
//	public void selectCountry(String countryName) {
//		type(country, countryName);
//		waitForElementToAppear(countryResults);
//		find(countryResults).click();
//	}

}
