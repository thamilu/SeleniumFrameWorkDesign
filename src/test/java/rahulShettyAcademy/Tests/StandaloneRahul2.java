package rahulShettyAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulShettyAcademy.PageObjects.LandingPage;

public class StandaloneRahul2 {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";	
		System.setProperty("webdriver.chrome.driver", "D:\\data_Path\\Browser_driver\\chrome_driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.loginApplication("selvaperuvaje@gmail.com", "Thamilu*884*");
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List <WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".rounded:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List <WebElement> cartProd = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProd.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productName));
		cartProd.forEach(cartprod->System.out.println(cartprod.getText()));
//		Assert.assertTrue(match);
		Thread.sleep(2000); // Added sleep to ensure the cart is updated before proceeding
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		Thread.sleep(2000); // Added sleep to ensure the confirmation page is loaded
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
}}
