package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_I {
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
	public void TC_01_Emty_Data() {
		// Action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		// Verify
		// Verify thi dung ham verify assert equal/true/false => khi verify equal thì 2
		// dữ liệu mong muốn và thực tế phải cùng loại: Text so sánh với text, số so
		// sánh với số
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		// lệnh getText() không lấy được khoảng trắng cho nên là khi getText() cần phải
		// để ý xem vế bên kia có khoảng trắng không thì xoá đi

	}

	@Test
	public void TC_02_Invalid_Email() {
		// Action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Haxinh");
		driver.findElement(By.id("txtEmail")).sendKeys("123@");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");

	}

	@Test
	public void TC_03_Invalid_Confirm_Email() {
		// Action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Haxinh");
		driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_04_Incorrect_Password() {
		// Action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Haxinh");
		driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		// Action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Haxinh");
		driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345679");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC_06_Invalid_Phone_Number() {
		// Action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Haxinh");
		driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("012345678");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		// Verify 1
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// Action 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("012345678910111213");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		// Verify 2

		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// Action 3
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("11111111111");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		// Verify 3

		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}