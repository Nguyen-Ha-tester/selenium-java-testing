package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Button {
	// Khai báo
	WebDriver driver;
	// Khai báo + Khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		// Khởi tao driver để thao tác trên trình duyệt firefox
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Cái implicitWait nó là thời gian dùng để findElement 
		//Nếu không có thì khi chạy lệnh findElement nó search k ra thì sẽ báo fail luôn
		//Còn nếu có thời gian thì sau 0.5s nó sẽ search lại thêm 1 lần nữa 
		//Nếu máy load chậm do mạng mà k có implicitWait thì khả năng fail sẽ rất cao á

	}

	@Test
	public void TC_01_Fahasa() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(5);
		
		// Đi đến trang đăng nhập
		driver.findElement(By.cssSelector("li.popup-login-tab-item.popup-login-tab-login")).click();
		
		//Verify enable bằng hàm enable: verify button dang disbale (tức là nếu dùng hàm isEnable mà ra False là đúng
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
	
		//Enter value to Email/ password textbox
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("ha@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

		// Verify login button là enable. Hàm isEnable trả về True là đúng
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

		//Verify login button màu đỏ (Red)
		String rgbaColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		
		//rgbaColor to Hexa Color
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		
		//Verify background color
		Assert.assertEquals(hexaColor, "#C92127");
	
	
	}

	@Test
	public void TC_02_() {
		sleepInSecond(5);
	}

	@Test
	public void TC_03_() {
		sleepInSecond(5);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	// Sleep cứng (static wait)
	// Chú ý hàm này phải bỏ ngoài block
	// afterclasshttps://opensource-demo.orangehrmlive.com/
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}