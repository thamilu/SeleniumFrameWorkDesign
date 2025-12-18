package rahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulShettyAcademy.PageObjects.CartPage;
import rahulShettyAcademy.PageObjects.CheckoutPage;
import rahulShettyAcademy.PageObjects.ConfirmationPage;
import rahulShettyAcademy.PageObjects.OrderPage;
import rahulShettyAcademy.PageObjects.ProductCatlog;
import rahulShettyAcademy.TestComponents.BaseTest;

public class GestdatabasicSubmitOrderTest2 extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void ordersubmit(String email, String password, String productName) throws InterruptedException, IOException {
		
		ProductCatlog productCatlog = landingPage.loginApplication(email, password);
		List<WebElement> products = productCatlog.getProductList();
		productCatlog.addProductToCart(productName);
		CartPage cartPage = productCatlog.goToCart();
		Boolean match = cartPage.checkProductIsDisplayed(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"ordersubmit"})
	public void orderHistoryTest() throws InterruptedException {
		ProductCatlog productCatlog = landingPage.loginApplication("selvaperuvaje@gmail.com", "Thamilu*884*");
		OrderPage orderpage =productCatlog.goToOrders();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		
	}                    
	
	@DataProvider(name="getData")
	public Object[][] getData() {
        return new Object[][] {{"selvaperuvaje@gmail.com","Thamilu*884*","ZARA COAT 3"},{"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
        		
}
}