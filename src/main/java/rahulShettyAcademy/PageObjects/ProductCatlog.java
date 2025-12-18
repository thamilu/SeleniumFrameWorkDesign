package rahulShettyAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulShettyAcademy.AbstractComponents.AbstractCompanents;

public class ProductCatlog extends AbstractCompanents {
//	WebDriver driver;
	public ProductCatlog(WebDriver driver) {
		super(driver); // Call the constructor of AbstractComponents
//		this.driver = driver;
		// Initialize all elements in the page using PageFactory
//		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".col-lg-4") List <WebElement> products;
	@FindBy(css = ".ng-animating") WebElement spinner;
	By productBy = By.cssSelector(".col-lg-4");
	By addToCartBy = By.cssSelector(".rounded:last-of-type");
	By toastMessageBy = By.cssSelector("#toast-container");
	
		
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		// This method returns the first product that matches the given product name
		// It uses a stream to filter the product list based on the product name
		// and returns the first matching product or null if no match is found.
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	
	public void addProductToCart(String productName) {
		// This method adds a product to the cart by finding the product by name
		// and clicking on the "Add to Cart" button.
		WebElement product = getProductByName(productName);
			product.findElement(addToCartBy).click();
			waitForElementToAppear(toastMessageBy);
			waitForElementToDisappear(spinner);
	}
//	

}
