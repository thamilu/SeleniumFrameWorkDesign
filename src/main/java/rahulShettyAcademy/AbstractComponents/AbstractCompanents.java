package rahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulShettyAcademy.PageObjects.CartPage;
import rahulShettyAcademy.PageObjects.OrderPage;

public class AbstractCompanents {
	WebDriver driver;

	public AbstractCompanents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']") WebElement btnCart;
	@FindBy(css = "[routerlink*='myorders']") WebElement btnOrders;
//	@FindBy(css = "button[routerlink='/dashboard/cart']") WebElement btnCart;
	By cartBarBy = By.cssSelector("app-dashboard[class='ng-star-inserted'] app-sidebar nav");

	public void waitForElementToAppear(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public void waitForWebelementElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public CartPage goToCart() throws InterruptedException {
		waitForElementToAppear(cartBarBy);
		Thread.sleep(10);
		btnCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrders() throws InterruptedException {
		waitForElementToAppear(cartBarBy);
//		Thread.sleep(10);
		btnOrders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
