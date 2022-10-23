package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Default_Dropdown {
	WebDriver driver;
	Select select; // Đây là bước 1: Khai báo
	Random rand;
	WebElement element;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		driver = new FirefoxDriver(); // khởi tạo driver 
		rand = new Random();   // Khởi tạo rand
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_DefaultDropdown() {
		// đi đến trang cần test
		driver.get("https://demo.nopcommerce.com/register");

		// Input required field
		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Ha");
		driver.findElement(By.cssSelector("#LastName")).sendKeys("Nguyen");
		
		// Khai báo dropdown:
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))); // Đây là bước 2: Khởi tạo. Với hàm select thì khởi tạo trực tiếp với element chứ k khởi tạo ở block before class được
		// Verify số lượng option trong mỗi dropdown là đúng:
		//Assert.assertEquals(select.getOptions().size(),32);
		// Chọn giá trị trong dropdonw:
		select.selectByVisibleText("10");
		

		//Hàm select
			//select.selectByIndex(0); //=> không nên dùng vì khó xác định được chính xác và nhanh chóng index của option trong dropdown. Nếu add thêm <option> thì index của các <option> còn lại sẽ bị thay đổi hàng loạt và sửa code mất thời gian
			//select.selectByValue(osName); //=> Không nên dùng vì nhiều trường hợp <option> không có attribute value
			//select.selectByVisibleText(osName); // => Nên dùng nhất vì text nhìn được ngay, không lo thêm bớt <option>, không lo thiếu value attribute.		
		//Verify cái đã được chọn thành công
			//Assert.assertEquals(select.getFirstSelectedOption().getText(),"10");
		//Verify 1 dropdown là single hay multiple dropdown
			//Single -> false
			//Multiple -> true
			//Assert.assertFalse(select.isMultiple());
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		//Assert.assertEquals(select.getOptions().size(),13);
		select.selectByVisibleText("May");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		//Assert.assertEquals(select.getOptions().size(),112);
		select.selectByVisibleText("1980");
		// Điền các trường còn lại
		//Luư ý trường email dùng hàm random nên phải cố định email value vào 1 biến để tý còn đối chiếu verify.
		
		//Cách làm 1 (sai)
		//WebElement Email = driver.findElement(By.cssSelector("#Email"));
		//Email.sendKeys("ha" + rand.nextInt(99999) + "@gmail.com");
		// Cách làm 2 (đúng)
		String Email = "ha" + rand.nextInt(99999) + "@gmail.com";
		driver.findElement(By.cssSelector("#Email")).sendKeys(Email);

		//Password:
		driver.findElement(By.cssSelector("#Password")).sendKeys("1234567");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("1234567");

		// Click Register button
		driver.findElement(By.cssSelector("#register-button")).click();
	
		// Check coi có regis thành công chưa
		Assert.assertTrue(driver.findElement(By.cssSelector("div.result")).isDisplayed()); 
		// Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed"); // cách của thầy
		
		// Vô My account để check
		driver.findElement(By.cssSelector("a.ico-account")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName")).getAttribute("value"),"Ha");
		Assert.assertEquals(driver.findElement(By.cssSelector("#LastName")).getAttribute("value"),"Nguyen");
		Assert.assertEquals(driver.findElement(By.cssSelector("#LastName")).getAttribute("value"),"Nguyen");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "10");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");

		Assert.assertEquals(driver.findElement(By.cssSelector("#Email")).getAttribute("value"),Email);

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