package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Custom_Radio_Checkbox {
	// Khai báo
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		// Khởi tạo jsExecutor. Selenium có support việc khởi tạo gián tiếp. Vì webdriver và javascriptexecutor cùng loại là interface (I) nên hỗ trợ khởi tạo jsExecutor bằng cách add kiểu driver này qua JavascriptExecutor
		jsExecutor = (JavascriptExecutor) driver; 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Cái implicitWait nó là thời gian dùng để findElement 
		//Nếu không có thì khi chạy lệnh findElement nó search k ra thì sẽ báo fail luôn
		//Còn nếu có thời gian thì sau 0.5s nó sẽ search lại thêm 1 lần nữa 
		//Nếu máy load chậm do mạng mà k có implicitWait thì khả năng fail sẽ rất cao á

	}

	
	public void TC_01_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		sleepInSecond(5);
//		//Case1:
//			//Thẻ input: K click được. Bị ẩn, các thẻ khác đè lên, nên k thao tác lên được nó nên k click được (toạ độ 1x1, gần như ==0)
//			//Thẻ input: Verify được. Work với hàm verify assert. Lệnh isSelected chỉ dùng cho thẻ <input> (ví dụ checkbox, dropdown, radio)
//		
//			driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).click(); // => K click đc vì là thẻ input
//			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected()); // Thẻ input verify được
//		
//		//Case 2:
//			//Thẻ span: click được.
//			//Thẻ span: K Verify được. Vì bản chất thẻ này khi verify thì nó luôn trả về bằng false
//		
//			driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click(); // => K click đc vì là thẻ input
//			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).isSelected()); // Thẻ input verify được
//		
//		// Case 3: 
//			//Thẻ span: click được.
//			//Thẻ input: Verify được.
//		
//			driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click(); // => K click đc vì là thẻ input
//			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected()); // Thẻ input verify được
//			//Tuy nhiên trong 1 dự án, 1 element cần dùng tới 2 locator (span, input) để code thì dễ gây hiểu nhầm (confuse) cho người mới, khi maintainace mất nhiều thời gian
//			
		// Case 4: Work around
			//Thẻ input: click + verify luôn
			//Vì hàm click của webelement không click vào element bị ẩn được
			// Nên dùng hàm click của JavascriptExecutor để click. Vì hàm này k qtam element có ẩn hay k, vẫn thao tác bình thường
			
			By Checkbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(Checkbox));
			sleepInSecond(5);
			Assert.assertTrue(driver.findElement(Checkbox).isSelected()); 
			
		
	}

	
	public void TC_02_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(5);
		
		By Radio = By.xpath("//span[text()=' Spring ']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(Radio));
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(Radio).isSelected()); 
		
	}
	
	@Test
	public void TC_03_Custom_Radio_Google() {
		//Không có thẻ input nào của radio/checkbox, vì Google tự design => k click đc, k verify được
		// Cách làm:
		// Xem trước khi click và sau khi click thì khác nhau cái gì
		//Ktra so sánh html khác nhau ở trang: https://www.diffchecker.com/
		//Nhận thất trước khi chọn và sau khi chọn có sự khác nhau ở value của 1 attrubute => dùng assertequal để verify (cũng có thể dùng asserttrue.isDisplayed để checkx)
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(5);
		
		By RadioGoogle = By.xpath("//div[@aria-label='Cần Thơ']");
		
		//Verify TRƯỚC khi click thì value của attribute aria-checked trả về bằng false
		Assert.assertEquals(driver.findElement(RadioGoogle).getAttribute("aria-checked"),"false");
		
		//Click
		driver.findElement(RadioGoogle).click(); //CLick thông thường thôi vì thằng element này k bị ẩn
		sleepInSecond(5);
		
		//Verify SAU khi click thì value của attribute aria-checked trả về bằng true
		Assert.assertEquals(driver.findElement(RadioGoogle).getAttribute("aria-checked"),"true");
		
	}
	@Test
	public void TC_04_Custom_Checkbox_Google() {
		//Với checkbox thì luôn phải ktra xem là đã chọn trước hay chưa rồi mới làm tiếp. Xem hàm check checkToCheckbox và uncheckToCheckbox.  
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(5);
		By CheckboxGoogle = By.xpath("//div[@aria-label='Mì Quảng']");

		//CHọn checkbox & verify đã chọn
		checkToCheckbox("//div[@aria-label='Mì Quảng']");
		Assert.assertEquals(driver.findElement(CheckboxGoogle).getAttribute("aria-checked"),"true");
		
		//Bỏ chọn checkbox & verify đã bỏ chọn
		uncheckToCheckbox("//div[@aria-label='Mì Quảng']");
		Assert.assertEquals(driver.findElement(CheckboxGoogle).getAttribute("aria-checked"),"false");
	}

	public void checkToCheckbox (String xpathLocator) {
	
	if(driver.findElement(By.xpath(xpathLocator)).getAttribute("aria-checked").equals("false")) {
		driver.findElement(By.xpath(xpathLocator)).click();
	}
	
}
	public void uncheckToCheckbox (String xpathLocator) {
		
		if(driver.findElement(By.xpath(xpathLocator)).getAttribute("aria-checked").equals("true")) {
			driver.findElement(By.xpath(xpathLocator)).click();
		}
		
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
