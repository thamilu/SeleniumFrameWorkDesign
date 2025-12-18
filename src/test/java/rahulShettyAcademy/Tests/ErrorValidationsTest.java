package rahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulShettyAcademy.PageObjects.CartPage;
import rahulShettyAcademy.PageObjects.ProductCatlog;
import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.TestComponents.Retry;

class ErrorValidationsTest extends BaseTest {
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorVlidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("selvaperuvaje@gmail.com", "Thamilu*8884*");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		ProductCatlog productCatlog = landingPage.loginApplication("selvaperuvaje@gmail.com", "Thamilu*884*");
		List<WebElement> products = productCatlog.getProductList();
		productCatlog.addProductToCart(productName);
		CartPage cartPage = productCatlog.goToCart();
		Boolean match = cartPage.checkProductIsDisplayed("ZARA COAT7 33");
		Assert.assertFalse(match);
//		Assert.assertTrue(match);
		
		
	}

}
