package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_orangehrmlive() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");

		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isPageLoadedSuccess()
	{	ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>(){
		@Override
		public Boolean apply(WebDriver driver) {
			return (Boolean) jsExecutor.executeScript("return (window.jQuery!= null) && (jQuery.active == 0);");
		}
	};
	ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>(){
		@Override
		public Boolean apply(WebDriver driver) {
			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		}
	};
	return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);


	}
}
