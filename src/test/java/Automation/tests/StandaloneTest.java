package Automation.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import Automation.pageobjects.CartPage;
import Automation.pageobjects.CheckoutPage;
import Automation.pageobjects.LandingPage;
import Automation.pageobjects.OrdersPage;
import Automation.pageobjects.ProductCatalogue;
import Automation.pageobjects.ThankYouPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest extends BaseTest{

	
	String countryName = "India";
	String expectedMessage = "THANKYOU FOR THE ORDER.";
	String productName = "ZARA COAT 3";
	
	
	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartButton();
		Boolean match = cartPage.verifyProductInCart(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.clickOnCheckoutButton();
		checkoutPage.selectCountry(countryName);
		ThankYouPage thankYouPage = checkoutPage.submitOrder();
		String actualMessage = thankYouPage.confirmationMessage();
		Assert.assertEquals(actualMessage, expectedMessage );
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistoryPage() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("email", "password");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductInCart(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "mncjdc@gmail.com");
//		map.put("password", "Password1");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map }, {map1}};
		
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Automation\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	public String getScreenshot(String testcasename) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testcasename + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty(("user.dir")+"//reports//" + testcasename + ".png");
		
		
		
	}

}
