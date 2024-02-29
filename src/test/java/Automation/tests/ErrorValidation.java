package Automation.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import Automation.pageobjects.CartPage;
import Automation.pageobjects.CheckoutPage;
import Automation.pageobjects.LandingPage;
import Automation.pageobjects.ProductCatalogue;
import Automation.pageobjects.ThankYouPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends BaseTest{

	
	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws IOException {
		
		landingPage.loginApplication("mnqwcjdc@gmail.com", "Password14354");
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
		}
	
	
	@Test
	public void cartProductErrorValidation() {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("mncjdc@gmail.com", "Password1");
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartButton();
		Boolean match = cartPage.verifyProductInCart("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}



	
	
}
