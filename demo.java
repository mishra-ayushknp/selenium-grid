package project;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo {
	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public static String remote_url = "http://localhost:4444/";
	public Capabilities capabilities;
	
	@BeforeMethod
	public void setDriver() throws MalformedURLException {
		// Setting the Browser Capabilities
		capabilities = new ChromeOptions();
 
		driver.set(new RemoteWebDriver(new URL(remote_url), capabilities));
		// Directing to the Testing Website
		driver.get().get("https://ecommerce-playground.lambdatest.io/");
		// Maximizing the Window
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		}
	
	public WebDriver getDriver() {
		return driver.get();
		}
	@Test
	public void validCredentials() {
		getDriver().findElement(By.name("search")).sendKeys("iphone");
		}
	
	
	@AfterMethod
	// To quit the browser
	public void closeBrowser() {
		driver.get().quit();
		driver.remove();
		}
	}
