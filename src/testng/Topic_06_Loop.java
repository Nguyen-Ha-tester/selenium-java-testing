package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_06_Loop {
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 5) // test case này sẽ chạy đi chạy lại 5 lần liên tiếp
	public void TC_01_LoginToSystem(){
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("FC");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("automation" + getRandomNumber() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		System.out.println("1 lần chạy");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}