package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{


	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	
//	/List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	//WebElement prod = products.stream().filter(
	//product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	
	public WebElement getProductName(String productName) {
		waitForElementToAppear(productsBy);
		WebElement prod = getProductList().stream().filter(
		product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
	
		WebElement prod = getProductName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(toastContainer);
		
		
		
	}


	
	
	
}
