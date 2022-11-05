package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Fixed_Popup {
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

	public void TC_01_Fixed_Popup_In_HTML() {
		driver.get("https://ngoaingu24h.vn/");
		
		//Đặt biến cho Popup Đăng nhập
		By loginPopup = By.xpath("(//div[@id='modal-login-v1'])[1]");
		
		//Mới vào page thì verify popup này chưa hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		//Hoặc
		//	Assert.assertEquals(driver.findElement(loginPopup).size(),0);

		
		//Click vào button Đăng nhập:
		driver.findElement(By.cssSelector("button.login_")).click();
		
		//Lúc này thì verify popup Login hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		//Hoặc
		//	Assert.assertEquals(driver.findElement(loginPopup).size(),1);

		//Điền tên và password đăng nhập
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@id='password-input']")).sendKeys("automationfc");
		
		//Click Đăng nhập button
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//button[text()='Đăng nhập']")).click();
		
		//Verify error message tồn tại
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
	}

	@Test
	public void TC_02_Fixed_Popup_Not_In_HTML() {
		driver.get("https://tiki.vn/");
		
		//Đây là trường hợp locator của pop up không xuất hiện khi popuo bị closed/ chưa xuất hiện. Sẽ phải Assert nếu popup k xuất hiện, tức locator size()=0 thì sẽ click nút ĐĂng nhập. Nhớ là phải dùng 
		//findElements => số nhiều để get size()
		Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']")).size(),0);
		
		//Click nút đăng nhập
		driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
		
		//Lúc này thì verify popup Login hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed());
		
		//Cick nút Tiếp tục
		driver.findElement(By.xpath("//button[text()='Tiếp Tục']")).click();
		
		//Verify error message tồn tại
		Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess")).getText(), "Số điện thoại không được để trống");
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
