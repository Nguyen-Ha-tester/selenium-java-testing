package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Mix_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
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
	}

	public void TC_01_Element_Found() {
		
		//Trường hợp 1: Element có xuất hiện mà không cần chờ hết TimOut/
		//Dù có set cả 2 loại wait thì cũng không ảnh hưởng gì cả
		
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Implicit Wait chỉ apply cho việc findElement thôi
		// Explicit Wait apply cho các điều kiện của element
		//=> Luôn luôn tìm element trước rồi apply điều kiện sau   
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("Thoi gian bat dau cua explicit = " + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("Thoi gian ket thuc cua explicit = " + getTimeStamp());
		
		//Implicit
		System.out.println("Thoi gian bat dau cua implicit = " + getTimeStamp());
		driver.findElement(By.id("email"));
		System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());
		
	}
	
	@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");
		// Implicit
		System.out.println("Thoi gian bat dau cua implicit = " + getTimeStamp());
		try {
			driver.findElement(By.id("haxinhgai"));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());

		}
		
		List<WebElement> notFoundElement = driver.findElements(By.id("haxinhgai"));
		System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());
		//output khi k tìm thấy
		// Có cơ chế tìm lại sau mỗi 500 milisecond (0.5s)
		// Hết timeout sẽ đánh fail testcase này
		// Và throw ra 1 exception: NoSuchElement
		// Mà nếu bị fail thì k đến bước tiếp theo được
		// => Nên phải dùng hàm try, catch. Khi try fail thì sẽ throw ra exception. Sau đó catch sẽ bắt cái exception lại
		// và tiếp tục
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


	//Hàm show ra time-stamp mốc thời gian tại thời điểm gọi hàm này
	// time-stamp = ngày - giờ - phút - giây
	public String getTimeStamp() {
	
		Date date = new Date();
		return date.toString();
	}
}