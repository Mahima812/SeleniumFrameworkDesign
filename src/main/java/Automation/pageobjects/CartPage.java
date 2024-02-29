package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Automation.AbstractComponents.AbstractComponent;





public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	By checkoutButtonBy = By.cssSelector(".totalRow button");
	
	
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	//Boolean match = cartProducts.stream().anyMatch(p ->p.getText().equals("productName"));
	public Boolean verifyProductInCart(String productName) {
		Boolean match = cartProducts.stream().anyMatch(p ->p.getText().equals(productName));
		return match;
	}
	
	//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
	//((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	public CheckoutPage clickOnCheckoutButton() {
		waitForElementToBeClickable(checkoutButtonBy);
		jsExecutor(checkoutButton);
		return new CheckoutPage(driver);
		
		
		
	}
	
	
	
	
	
	
	
	
}
