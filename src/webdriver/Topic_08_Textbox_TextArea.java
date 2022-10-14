package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea {
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
	public void TC_01_TextBox_TextArea() {
		//Đăng nhập: nhập login, pass, ấn login button
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(6);
		
		//Mở trang Add Employee)
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
		
		//Nhập fname, lname, getText từ employeeID để tý đối chiếu
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Ha");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Nguyen");
		String EmployeeID = driver.findElement(By.xpath("//div[@class='oxd-input-group__label-wrapper']/following-sibling::div//input[@class='oxd-input oxd-input--active']")).getAttribute("value");
		
		//Click Save để lưu input
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(6);
		
		//Verify xem có đến đúng địa chỉ Personal Detail sau khi ấn Save không
		Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/64");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"),"Ha");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"),"Nguyen");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), EmployeeID);
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@Test
	public void TC_04_() {

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
