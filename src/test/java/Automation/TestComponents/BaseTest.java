package Automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Automation/resources/GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
	
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		//read json to string;
		
		String jsonContent = FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		//string to hashmap-- jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		
	}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	
	
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}

}
