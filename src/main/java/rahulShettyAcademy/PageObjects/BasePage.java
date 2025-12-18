package rahulShettyAcademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	WebDriver driver;

	// Common methods and properties for all pages
	public BasePage(WebDriver driver) {	
		// Constructor for BasePage class
		this.driver=driver;
		// Initialize all elements in the page using PageFactory
		PageFactory.initElements(driver, this);		
	}

}