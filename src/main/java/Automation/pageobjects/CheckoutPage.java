package Automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]/button[2]")
	WebElement countryNameFromDropdown;
	
	By countryNameFromDropdownBy = By.xpath("//section[contains(@class,'ta-results')]/button[2]");
	
	@FindBy(css="a[class*='action__submit']")
	WebElement submit;
	
	By submitBy = By.cssSelector("a[class*='action__submit']");
	
	//Actions a = new Actions(driver);
	//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
//	driver.findElement(By.xpath("//section[contains(@class,'ta-results')]/button[2]")).click();
//	WebElement placeOrder = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class*='action__submit']")));
//	((JavascriptExecutor)driver).executeScript("arguments[0].click();", placeOrder);

	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToBeClickable(countryNameFromDropdownBy);
		jsExecutor(countryNameFromDropdown);
		//countryNameFromDropdown.click();
		
		
	}

	public ThankYouPage submitOrder() {
		waitForElementToBeClickable(submitBy);
		jsExecutor(submit);
		return new ThankYouPage(driver);
		
	}

}
