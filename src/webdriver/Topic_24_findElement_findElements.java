package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_findElement_findElements {
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
		
		//Driver đang apply thời gian chờ là 15s cho việc tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_FindElement() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	// Case 1: Tìm thấy duy nhất 1 element/ node
		//Tìm thấy và có thể thao tác trực tiếp lên Element
		driver.findElement(By.xpath("//input[@id='email']")); 
		//=> Vì nó tìm thấy nên k cần phải wait 15s nữa, dừng ngay khi tìm thấy trong 0,5s đầu
		
		
	// Case 2: Tìm thấy nhiều hơn 1 
		//Tìm thấy và có thể thao tác trực tiếp lên Element
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("ha@gmail.com");
		//Nếu tìm thấy hơn 1 element thì nó sẽ thao tác lên element đầu tiên, dừng ngay khi tìm thấy trong 0,5s đầu
		
	// Case 3: Không tìm thấy
		//Nếu không tìm thấy thì cơ chế 0,5s tìm lại 1 lần
		//Nếu trong thời gian tìm lại mà tìm thấy element thì: PASS
		//Nếu cho tới khi hết thời gian TIMEOUT mà vẫn không thấy element thì:
			//Đánh FAIL cả testcase tại dòng findElement này.
			//Throw ra 1 ngoại lệ: "NoSuchElementExeption"
		driver.findElement(By.xpath("abcxyz"));

	}
	@Test
	public void TC_02_FindElements() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

	// Case 1: Tìm thấy duy nhất 1 element/ node
		//Tìm thấy và Lưu nó vào trong 1 biến List<WebElement> để thao tác về sau
		//=> Vì nó tìm thấy nên k cần phải wait 15s nữa, dừng ngay khi tìm thấy trong 0,5s đầu
			List<WebElement> element =	driver.findElements(By.xpath("//input[@id='email']")); 		
			System.out.println("List of elements = " + element.size());
	// Case 2: Tìm thấy nhiều hơn 1 
		//Tìm thấy và Lưu nó vào trong 1 biến List<WebElement> để thao tác về sau
		//=> Vì nó tìm thấy nên k cần phải wait 15s nữa, dừng ngay khi tìm thấy trong 0,5s đầu
			List<WebElement> elements =	driver.findElements(By.xpath("//input[@type='email']")); 		
			System.out.println("List of elements = " + elements.size());
			
	// Case 3: Không tìm thấy
		// Nếu không tìm thấy thì cơ chế 0,5s tìm lại 1 lần
		//Nếu trong thời gian tìm lại mà tìm thấy element thì: PASS
		//Nếu cho tới khi hết thời gian TIMEOUT mà vẫn không thấy element thì:
			// KHÔNG ĐÁNH FAIL cả testcase và vẫn chạy step tiếp theo
			// trả về 1 list rỗng (emty)
			List<WebElement> noPresentElement =	driver.findElements(By.xpath("abcxyz")); 		
			System.out.println("List of elements = " + noPresentElement.size());
	}
	
	@AfterClass
	public void afterClass() {
	  driver.quit();
	
	}
}
