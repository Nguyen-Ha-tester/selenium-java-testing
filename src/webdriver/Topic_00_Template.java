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
	public void TC_01_JotForm() {
		
		driver. get ("https://automationfc.github.io/multiple-fields/");
		//Chọn checkbox: Cancer và Fainting Spells
		isElementSelected("//label[text()=' Cancer ']/preceding-sibling::input");
		isElementSelected("//label[text()=' Fainting Spells ']/preceding-sibling::input");

		// Verify đc chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' Cancer ']/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' Fainting Spells ']/preceding-sibling::input")).isSelected());

		
		// Chọn Radio: 5+ days và 1-2 glasses/day
		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
		driver.findElement(By.xpath("//input[@value='1-2 glasses/day']")).click();

		// Verify đc chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1-2 glasses/day']")).isSelected());

		// Bỏ chọn checkbox: click tiếp one time nữa để bỏ chọn
		driver.findElement(By.xpath("//label[text()=' Cancer ']/preceding-sibling::input")).click(); 
		driver.findElement(By.xpath("//label[text()=' Fainting Spells ']/preceding-sibling::input")).click();
		
		// Verify bỏ  chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='1-2 glasses/day']")).isSelected());
		
		// Bỏ Chọn Radio: 5+ days và 1-2 days: thì phải chọn 1 radio khác nó sẽ bỏ chọn radio đang chọn đi
		driver.findElement(By.xpath("//input[@value='3-4 days']")).click();
		driver.findElement(By.xpath("//input[@value='3-4 glasses/day']']")).click();

		// Verify bỏ  chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='1-2 glasses/day']")).isSelected());

		
		
	}

	@Test
	public void TC_02_() {
		sleepInSecond(5);
	}

	@Test
	public void TC_03_() {
		sleepInSecond(5);
	}
	
	// Để đảm bảo là trạng thái ban đầu trước khi click radio/checkbox
	public void isElementSelected(String xpathLocator) {
		// ktra trc radio/checkbox đã đc chọn hay chưa
		//Nếu chọn rồi thì k cần click nữa
		// Nếu chưa ch thì click vào -> đc chọn
		
		if (!driver.findElement(By.xpath(xpathLocator)).isSelected()) { //Nếu If trả về true thì k vào click nữa
			driver.findElement(By.xpath(xpathLocator)).click (); } // Nếu ktra if trả về false thì click 
	
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
