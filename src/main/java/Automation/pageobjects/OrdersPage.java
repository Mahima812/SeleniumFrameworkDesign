package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	public WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productsOnOrdersPage;
	
	public Boolean verifyProductInCart(String productName) {
		Boolean match = productsOnOrdersPage.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
}
