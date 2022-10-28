package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
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
	public void TC_01_() {
		
	}

	@Test
	public void TC_02_() {
	}

	@Test
	public void TC_03_() {
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
