package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_ImplicitWait {
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
		
		// 1 - implicitwait chỉ ảnh hưởng trực tiếp tới 2 hàm: findElement và findElements => Xem 3 testcase dưới
		// 2 - Trường hợp thêm ngoại lệ
			// ImplicitWait được set ở đâu thì nó sẽ apply từ đó trở đi
			// Nếu bị gán lại thì sẽ dùng cái giá trị mới được gán/ không dùng giá trị cũ

	}

	@Test
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		//Thời gian loading... là 5s nhưng thời gian implicitWait là 2s => khi tìm Hello World thì sẽ không kịp tìm thấy trong 2s
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
		
	
	
	}
	
	@Test
	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		//Thời gian loading... là 5s =>  thời gian implicitWait là 5s => Đủ thời gian tìm Hello World
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
		
	
	}
	
	@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		//Thời gian loading... là 5s =>  thời gian implicitWait là 10s => Đủ thời gian tìm Hello World, không cần dùng hết 10s
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
