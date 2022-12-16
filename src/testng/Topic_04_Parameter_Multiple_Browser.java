package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_04_Parameter_Multiple_Browser {
	WebDriver driver;
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters ("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		//set up chạy cho cả 3 trình duyệt firefox - chrome - edge
//		// If-else
//		if (browserName.equals("firefox")) {
//			if (osName.contains("Mac OS")) {
//				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//				
//			} else {
//				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			}
//			driver = new FirefoxDriver();
//		} else  if (browserName.equals("chrome")) {
//			if (osName.contains("Mac OS")) {
//				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//				
//			} else {
//				System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			}
//			driver = new ChromeDriver();
//			
//		} else if (browserName.equals("edge")) {
//			if (osName.contains("Mac OS")) {
//				System.setProperty("webdriver.msedge.driver", projectPath + "/browserDrivers/msedgedriver");
//				
//			} else {
//				System.setProperty("webdriver.msedge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//			}
//			driver = new EdgeDriver();
//
//		} else {
//			throw new RuntimeException ("Please input with correct browser name."); //RuntimeException có nghĩa là chạy lỗi phát là throw ngay
//		}
		
		// Switch -case (tương tự if-else nhưng switchcase k cho phép lặp case và trông code có vẻ gọn hơn)
		switch (browserName) {
		case "firefox":
			if (osName.contains("Mac OS")) {
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
				
			} else {
				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			}
			driver = new FirefoxDriver();	
			System.out.println("done testing on Firefox");

			break;
		case "chrome":
			if (osName.contains("Mac OS")) {
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
				
			} else {
				System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			}
			driver = new ChromeDriver();
			System.out.println("done testing on Chrome");
			break;
			
		case "edge":
			if (osName.contains("Mac OS")) {
				System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
				
			} else {
				System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			}
			driver = new EdgeDriver();
			System.out.println("done testing on Edge");
			break;
		

		default:
			throw new RuntimeException ("Please input with correct browser name."); //RuntimeException có nghĩa là chạy lỗi phát là throw ngay
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_LoginToSystem() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
