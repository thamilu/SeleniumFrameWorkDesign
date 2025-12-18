package rahulShettyAcademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractCompanents;

public class LandingPage extends AbstractCompanents{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver); // Call the constructor of AbstractComponents
		this.driver = driver;
		// Initialize all elements in the page using PageFactory
		PageFactory.initElements(driver, this);
		// No need to call PageFactory.initElements here as it is done in BasePage
	}
	@FindBy(css = "#userEmail") WebElement inputEmail;
	@FindBy(id = "userPassword") WebElement inputPassword;
	@FindBy(css = "#login") WebElement btnLogin;
	@FindBy(css = ".ng-trigger") WebElement errorMessage;
	
	public ProductCatlog loginApplication(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnLogin.click();
        ProductCatlog productCatlog = new ProductCatlog(driver);
        return productCatlog;
	}
	public String getErrorMessage()
	{
		waitForWebelementElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	
	
	}


}
