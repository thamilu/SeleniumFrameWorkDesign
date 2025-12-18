package rahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.HashMap;
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

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void ordersubmit(HashMap <String, String> input) throws InterruptedException, IOException {
		
		ProductCatlog productCatlog = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatlog.getProductList();
		productCatlog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatlog.goToCart();
		Boolean match = cartPage.checkProductIsDisplayed(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
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
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\rahulShettyAcademy\\data\\PurchaseOrder.json");
		
        return new Object[][] {{data.get(0)},{data.get(1)}};
        		
}
}