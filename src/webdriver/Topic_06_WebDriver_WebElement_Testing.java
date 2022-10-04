package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebDriver_WebElement_Testing {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Verify_URL() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		String URL1 = driver.getCurrentUrl();
		Assert.assertEquals(URL1, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String URL2 = driver.getCurrentUrl();
		Assert.assertEquals(URL2, "http://live.techpanda.org/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		String Text = driver.findElement(By.xpath("//meta[@http-equiv='Content-Type']/following-sibling::title")).getText();
		Assert.assertEquals(Text, "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String Text2 = driver.findElement(By.xpath("//meta[@http-equiv='Content-Type']/following-sibling::title")).getText();
		Assert.assertEquals(Text2, "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate_Function() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String URL3 = driver.getCurrentUrl();
		Assert.assertEquals(URL3, "http://live.techpanda.org/customer/account/create/");
		
		driver.navigate().back();
		
		String URL4 = driver.getCurrentUrl();
		Assert.assertEquals(URL4, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		String Text2 = driver.findElement(By.xpath("//meta[@http-equiv='Content-Type']/following-sibling::title")).getText();
		Assert.assertEquals(Text2, "Create New Customer Account");
		
	}
	@Test
	public void TC_03_Get_Page_Source_Code() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click();
		
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		
	
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}