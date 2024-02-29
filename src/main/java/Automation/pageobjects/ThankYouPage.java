package Automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;


public class ThankYouPage extends AbstractComponent {

	WebDriver driver;

	public ThankYouPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;


	
//	String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
//	Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
	
	public String confirmationMessage() {
		return confirmationMessage.getText();
	}
	
	
}
