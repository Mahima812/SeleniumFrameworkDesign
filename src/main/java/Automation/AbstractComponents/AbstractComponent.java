package Automation.AbstractComponents;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='/dashboard/myorders']")
	WebElement ordersHeader;

	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	public void waitForElementToDisappear(By findBy){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
	public void waitForElementToBeClickable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	//((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	public void jsExecutor(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	public CartPage goToCartButton() {
			cartButton.click();
			CartPage cartPage = new CartPage(driver);
			return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		ordersHeader.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
}
	
}
