package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// Topic này kiểm tra 1 điều kiện là đúng hay sai
// Các bước của việc kiểm tra:
//B1: Khai báo biến
//B2: Kiểm tra biến với if-else
// Nếu đúng: -> True và dừng lại
// Nếu sai: -> False và nhảy qua else
public class Topic_07_WebDriver_WebElement_Testing2 {
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
	public void TC_01_isDisplayedL() {
		// isDisplayed: tức là element đó nhìn thấy được, có kích thước, có thể thao tác
		// được, trong html có màu
		// is Displayed -> true
		// is not displayed -> false
		// Phạm vi áp dụng: Tất cả các loại element (textbox, option, field, button,
		// link, radio....)
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Email
		WebElement EmailField = driver.findElement(By.cssSelector("input#mail"));
		if (EmailField.isDisplayed()) {
			EmailField.sendKeys("Haxinhdeptuyetvoi@gmail.com");
			System.out.println("Email Field is displayed");
		} else {
			System.out.println("Email Field is NOT displayed");
		}

		// Age under 18
		WebElement Under18 = driver.findElement(By.xpath("//label[@for='under_18']"));
		if (Under18.isDisplayed()) {
			Under18.click();
			System.out.println("Under 18 checkbox is displayed");
		} else {
			System.out.println("Under 18 checkbox is NOT displayed");
		}

		// Education
		WebElement Edu = driver.findElement(By.cssSelector("#edu"));
		if (Edu.isDisplayed()) {
			Edu.sendKeys("HaUniversity");
			System.out.println("Edu Field is displayed");
		} else {
			System.out.println("Edu Field is NOT displayed");
		}

		// Name: User5
		WebElement User5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (User5.isDisplayed()) {
			System.out.println("User5 is displayed");
		} else {
			System.out.println("User5 is NOT displayed");
		}
	}

	@Test
	public void TC_02_isenabled() {
		// Phân biêt:
		// Gọi 1 element là is enabled khi có thể tương tác được lên nó và ngược lại với
		// is disabled
		// Gọi 1 element là is displayed khi có thể nhìn thấy nó và ngược lại với is not
		// displayed
		// is enabled -> true
		// is disabled -> false
		// Phạm vi áp dụng: Tất cả các loại element (textbox, option, field, button,
		// link, radio....)
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Email
		WebElement EmailField = driver.findElement(By.cssSelector("input#mail"));
		if (EmailField.isEnabled()) {
			System.out.println("Email Field is enabled");
		} else {
			System.out.println("Email Field is disabled");
		}

		// Age under 18
		WebElement Under18 = driver.findElement(By.xpath("//label[@for='under_18']"));
		if (Under18.isEnabled()) {
			System.out.println("Under 18 checkbox is enabled");
		} else {
			System.out.println("Under 18 checkbox is disabled");
		}

		// Education
		WebElement Edu = driver.findElement(By.cssSelector("#edu"));
		if (Edu.isEnabled()) {
			System.out.println("Edu Field is enabled");
		} else {
			System.out.println("Edu Field is disabled");
		}

		// Job Role 1
		WebElement JobRole1 = driver.findElement(By.cssSelector("#job1"));
		if (JobRole1.isEnabled()) {
			System.out.println("Job Role 1 is enabled");
		} else {
			System.out.println("Job Role 1 is disabled");
		}
		// Job Role 2
		WebElement JobRole2 = driver.findElement(By.cssSelector("#job2"));
		if (JobRole2.isEnabled()) {
			System.out.println("Job Role 2 is enabled");
		} else {
			System.out.println("Job Role 2 is disabled");
		}
		// Interest - Development
		WebElement InterestDevelopment = driver.findElement(By.xpath("//label[@for='development']"));
		if (InterestDevelopment.isEnabled()) {
			System.out.println("Interest - Development is enabled");
		} else {
			System.out.println("Interest - Development is disabled");
		}
		// Slide 01
		WebElement Slide01 = driver.findElement(By.cssSelector("#slider-1"));
		if (Slide01.isEnabled()) {
			System.out.println("Slide 01 is enabled");
		} else {
			System.out.println("Slide 01 is disabled");
		}

		// Password
		WebElement Password = driver.findElement(By.cssSelector("#disable_password"));
		if (Password.isEnabled()) {
			System.out.println("Password is enabled");
		} else {
			System.out.println("Password is disabled");
		}
		// Age - Radio button is disabled
		WebElement RadioButton = driver.findElement(By.xpath("//label[@for='radio-disabled']"));
		if (RadioButton.isEnabled()) {
			System.out.println(" Age - Radio button is disabled is enabled");
		} else {
			System.out.println(" Age - Radio button is disabled is disabled");
		}

		// Biography
		WebElement Biography = driver.findElement(By.cssSelector("#bio"));
		if (Biography.isEnabled()) {
			System.out.println("Biography is enabled");
		} else {
			System.out.println("Biography is disabled");
		}

		// Job Role 03
		WebElement JobRole03 = driver.findElement(By.cssSelector("#job3"));
		if (JobRole03.isEnabled()) {
			System.out.println("Job Role 03 is enabled");
		} else {
			System.out.println("Job Role 03 is disabled");
		}

		// Interest - Chechbox is disabled
		WebElement InterestChechboxisdisabled = driver.findElement(By.xpath("//label[@for='check-disbaled']"));
		if (InterestChechboxisdisabled.isEnabled()) {
			System.out.println("Interest - Chechbox is disabled is enabled");
		} else {
			System.out.println("Interest - Chechbox is disabled is disabled");
		}
		// Slide02
		WebElement Slide02 = driver.findElement(By.cssSelector("#slider-2"));
		if (Slide02.isEnabled()) {
			System.out.println("Slide02 is enabled");
		} else {
			System.out.println("Slide02 is disabled");
		}
	}

	@Test
	public void TC_03_isSelected() {
		// ktra 1 element is selected or not? là kiểm tra xem đã được chọn hay chưa
		// is selected -> true
		// Deselected -> false
		// Phạm vi áp dụng: radio button, checkbox, dropdown (dropdown dạng default)
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Age under 18
		WebElement Under18 = driver.findElement(By.xpath("//label[@for='under_18']"));
		Under18.click();

		if (Under18.isSelected()) {
			System.out.println("Under 18 checkbox is selected");
		} else {
			System.out.println("Under 18 checkbox is de-selected");
		}
		// Languagues:Java" checkbox
		WebElement JavaCheckbox = driver.findElement(By.cssSelector("#java"));
		JavaCheckbox.click();
		if (JavaCheckbox.isSelected()) {
			System.out.println("Languagues:Java\" checkbox is selected");
		} else {
			System.out.println("Languagues:Java\" checkbox is de-selected");
		}

	}

	@Test
	public void TC_04_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		// Email
		driver.findElement(By.cssSelector("#email")).sendKeys("Haxinhdep@xinhdep.xinhdep");
		sleepInSecond(3);
		// Password-nhập số
		WebElement Password = driver.findElement(By.cssSelector("#new_password"));
		Password.sendKeys("1");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Password-nhập chữ thường
		Password.clear();
		Password.sendKeys("a");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Password-nhập chữ in hoa
		Password.clear();
		Password.sendKeys("A");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		// Password-nhập kí tự đặc biệt
		Password.clear();
		Password.sendKeys("$");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Password-nhập tối thiểu 8 kí tự
		Password.clear();
		Password.sendKeys("12345678");
		sleepInSecond(3);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
		//Sleep cứng (static wait)
	public void sleepInSecond (long timeInSecond) {
			try {
				Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
}
}
}