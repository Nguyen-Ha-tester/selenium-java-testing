package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_StaticWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		driver = new FirefoxDriver();
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().window().maximize();


	}

	@Test
	public void TC_01_Not_Enough_Time() {
	
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();

		// Thiếu thời gian
		sleepInSecond(3);
		
		//Get text & verify
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());

	}

	@Test
	public void TC_02_Enough_Time() {
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// Đủ thời gian
		sleepInSecond(5);

		//Get text & verify
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());


	}

	@Test
	public void TC_03_More_Time() {
		
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();

		// Thừa thời gian
		sleepInSecond(15);

		// Get text & verify
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}
