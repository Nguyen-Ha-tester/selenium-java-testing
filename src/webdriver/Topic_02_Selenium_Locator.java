package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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

		// Mở trang sign in của rakuna ra bằng hàm driver.get ("")
		driver.get("https://demo.nopcommerce.com/register");
	}

	// <input type="text" data-val="true" data-val-required="First name is
	// required." id="FirstName" name="FirstName">

	// ===> Code HTML bên trên là của trang Sign in gồm:
	// • Tên thẻ của element (Tagname): input
	// • Tên của thuộc tính (Atribute name): type value name class id placeholder
	// • Giá trị của thuộc tính (value): text, true, First name is required,...

	@Test
	public void TC_01_ID() {
		// Muốn thao tác trên element nào thì đầu tiên phải tìm locator của nó bằng hàm driver.findElement
		// Find theo cái gì? Theo các locator như: id/ class/ name/ css/ xpath...
		// Find tìm thấy element nào rồi thì action lên element đó bằng .click, .sendKey....
		driver.findElement(By.id("FirstName")).sendKeys("Nguyen Ha");

	}

	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-text")).sendKeys("Haxinhgai");
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("advs")).click();
	}

	@Test
	public void TC_04_LinkText() {
		// Mình click vào đường link addresses
		driver.findElement(By.linkText("Addresses")).click();

	}

	@Test
	public void TC_05_PartialLinkText() {
		driver.findElement(By.partialLinkText("vendor account")).click();

	}

	@Test
	public void TC_06_Tagname() {
		// Tìm ra bao nhiêu thẻ input trên màn hình điện thoại
		// Tagname thường dùng để lấy ra
	System.out.println(driver.findElements(By.tagName("input")).size());
	
	}

	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register");
		// CÓ 3 cách viết của cùng 1 câu lệnh css
		// 1: viết tắt
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Nguyen Ha"); // dấu # là viết tắt cho id, dấu . là viết tắt cho classname....
		//2: viết css đầy đủ
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Nguyen Ha 2");
		//3: kết hợp nhiều locator khác nhau
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("NguyenHa@rakuna.co"); // Kết hợp id và name, kết hợp 2 locator
		
	}

	@Test
	public void TC_08_Xpath() {
		// Dài hơn CSS
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Nguyen Ha 2");
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("NguyenHa@rakuna.co");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}