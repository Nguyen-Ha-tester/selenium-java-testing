package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_WAIT_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		// Điều kiện bắt buộc là: Có trên UI
		// Điều kiện k bắt buộc là: Có trong HTML
		
		//Hàm Wait đợi cho đến khi element email được hiển thị trên màn hình trong vòng 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		//Đợi       cho đến khi       hiển thị element           có locator là email trong vòng 10s
	}
	
	public void TC_02_Invisible_UnDisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");
		//Điều kiện bắt buộc là: Không trên UI
		// Điều kiện k bắt buộc là: Có trong HTML
	
		//Hàm Wait đợi cho đến khi element email Không hiển thị trên màn hình trong vòng 10s
	
		driver.findElement(By.xpath("//a[contains(text(),'Create New Account')]")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@aria-label='Re-enter email address']")));
		//==> Chạy nhanh
	}
	public void TC_02_Invisible_UnDisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		//Điều kiện bắt buộc là: Không trên UI
		// Điều kiện k bắt buộc là: KHÔNG trong HTML
		
		//Hàm Wait đợi cho đến khi element email Không hiển thị trên màn hình trong vòng 10s
	
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@aria-label='Re-enter email address']")));
		}
		//=> Chạy lâu: vì nó đang đi tìm element trong HTML -> tìm cho đến khi nào có trong HTML thì thôi 
		//=> Trong case này cần áp implicitWait sleep cứng cho nó dừng trong thời gian cứng là 10s cho dù có tìm thấy hay không (Dòng 32_
		
	
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");
		// Điều kiện k bắt buộc là: Có trên UI
		// Điều kiện bắt buộc là: CÓ trong HTML

		// Hàm Wait đợi cho đến khi element email hiển thị TRONG HTML trong vòng 10s

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	
	@Test
	public void TC_04_Staleness() { //Case này ít dùng
		driver.get("https://www.facebook.com/");
		// Điều kiện bắt buộc là: KHÔNG có trên UI
		// Điều kiện bắt buộc là: KHÔNG có trong HTML

		
		driver.findElement(By.xpath("//a[contains(text(),'Create New Account')]")).click();
		
		//Phase 1: Element có trong HTML
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.xpath("//input[@aria-label='Re-enter email address']"));
		
		//Thao tác với element khác để cho reEnterEmailAddressTextbox biến mất đi
		driver.findElement(By.xpath("//img[@class='_8idr img']")).click(); //close popup đi
		//=> sau đó reEnterEmailAddressTextbox sẽ k có trên UI lẫn cây HTML => wait verify
		
		// Hàm Wait đợi cho đến khi element reEnterEmailAddressTextbox KHÔNG hiển thị TRONG HTML trong vòng 10s
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
		
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
