package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Radio_Checkbox {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver; //Khởi tạo Scroll
		//Cái implicitWait nó là thời gian dùng để findElement 
		//Nếu không có thì khi chạy lệnh findElement nó search k ra thì sẽ báo fail luôn
		//Còn nếu có thời gian thì sau 0.5s nó sẽ search lại thêm 1 lần nữa 
		//Nếu máy load chậm do mạng mà k có implicitWait thì khả năng fail sẽ rất cao á

	}

	
	public void TC_01_JotForm() {
		
		driver. get ("https://automationfc.github.io/multiple-fields/");
		
		//Chọn checkbox: Cancer và Fainting Spells
		isElementSelected("//label[text()=' Cancer ']/preceding-sibling::input");
		isElementSelected("//label[text()=' Fainting Spells ']/preceding-sibling::input");

		// Verify checkbox đc chọn thành công
			//Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' Cancer ']/preceding-sibling::input")).isSelected());
			//Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' Fainting Spells ']/preceding-sibling::input")).isSelected());
		Assert.assertTrue(VerifyisElementSelected("//label[text()=' Cancer ']/preceding-sibling::input"));
		Assert.assertTrue(VerifyisElementSelected("//label[text()=' Fainting Spells ']/preceding-sibling::input"));

		
		// Chọn Radio: 5+ days và 1-2 glasses/day
		isElementSelected("//input[@value='5+ days']");
		isElementSelected("//input[@value='1-2 glasses/day']");
		
		
		// Verify Radio đc chọn thành công
			//Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
			//Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1-2 glasses/day']")).isSelected());
		Assert.assertTrue(VerifyisElementSelected("//input[@value='5+ days']"));
		Assert.assertTrue(VerifyisElementSelected("//input[@value='1-2 glasses/day']"));
		
		
		// Bỏ chọn checkbox: click tiếp one time nữa để bỏ chọn
			//driver.findElement(By.xpath("//label[text()=' Cancer ']/preceding-sibling::input")).click(); 
			//driver.findElement(By.xpath("//label[text()=' Fainting Spells ']/preceding-sibling::input")).click();
		unCheckCheckBox("//label[text()=' Cancer ']/preceding-sibling::input");
		unCheckCheckBox("//label[text()=' Fainting Spells ']/preceding-sibling::input");
		
		// Verify bỏ chọn checkbox thành công
			//Assert.assertFalse(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
			//Assert.assertFalse(driver.findElement(By.xpath("//input[@value='1-2 glasses/day']")).isSelected());
		Assert.assertTrue(VerifyisElementSelected("//input[@value='5+ days']"));
		Assert.assertTrue(VerifyisElementSelected("//input[@value='1-2 glasses/day']"));
		
		// Bỏ Chọn Radio: 5+ days và 1-2 days: thì phải chọn 1 radio khác nó sẽ bỏ chọn radio đang chọn đi
			//driver.findElement(By.xpath("//input[@value='3-4 days']")).click();
			//driver.findElement(By.xpath("//input[@value='3-4 glasses/day']']")).click();
		isElementSelected ("//input[@value='3-4 days']");
		isElementSelected ("//input[@value='3-4 glasses/day']");
		
		
		// Verify bỏ chọn radio thành công
			//Assert.assertFalse(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
			//Assert.assertFalse(driver.findElement(By.xpath("//input[@value='1-2 glasses/day']")).isSelected());
		Assert.assertFalse(VerifyisElementSelected("//input[@value='5+ days']"));
		Assert.assertFalse(VerifyisElementSelected("//input[@value='1-2 glasses/day']"));
		
		
		
	}
	
	public void TC_02_JotForm_Select_All_Checkbox() {
		driver. get ("https://automationfc.github.io/multiple-fields/");
		
		//Scroll cho ngầu
		scrollToElement("label#label_52");
		
		// Liệt kê tất cả checkbox
		List<WebElement> allCheckbox = driver.findElements(By.cssSelector("input.form-checkbox"));

		// Dùng vòng lặp foreach để duyệt các checkbox và click checkbox
		for (WebElement eachCheckbox : allCheckbox) {
			if (!eachCheckbox.isSelected() && eachCheckbox.isEnabled() ) {
				eachCheckbox.click();
			}
		}
		
		// Dùng vòng lặp foreach để duyệt qua và verify đã chọn được all checkboxes
		for (WebElement eachCheckbox : allCheckbox) {
			Assert.assertTrue(eachCheckbox.isSelected());
		}
		
		//Dùng vòng lặp foreach để duyệt qua và de-select các checkboxes
		for (WebElement eachCheckbox : allCheckbox) {
			if (eachCheckbox.isSelected() && eachCheckbox.isEnabled()) {
				eachCheckbox.click();
			}
		}
		
		// Dùng vòng lặp foreach để duyệt qua và verify đã BỎ CHỌN được all checkboxes
		for (WebElement eachCheckbox : allCheckbox) {
			Assert.assertFalse(eachCheckbox.isSelected());
				} 
	}
	
	@Test
	public void TC_03_Select_All() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		scrollToElement("div#demo-runner");
		isElementSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		Assert.assertTrue(VerifyisElementSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		unCheckCheckBox("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		Assert.assertFalse(VerifyisElementSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
;
	}
	// Hàm function: Kiểm tra xem trước khi thao tác vào Radio/Checkbox thì trạng thái của nó là ntn, đã click trước đó hay chưa
	public void isElementSelected(String xpathLocator) {
		// ktra trc radio/checkbox đã đc chọn hay chưa và đặc biệt là radio/checkbox đó có enable không 
		//Nếu chọn rồi thì k cần click nữa
		// Nếu chưa chọn thì click vào -> đc chọn
		
		if (!driver.findElement(By.xpath(xpathLocator)).isSelected() && driver.findElement(By.xpath(xpathLocator)).isEnabled()) { //Nếu If trả về true thì tức là chưa đc select ->  vào click
			driver.findElement(By.xpath(xpathLocator)).click (); } // Nếu ktra if trả về false thì  không vào click
		//Nếu element này chưa được chọn thì click
		// Dấu ! là để phủ định, cách viết khác là driver.findElement(By.xpath(xpathLocator)).isSelected() == false
	
	}
	
	//Hàm function: bỏ chọn checkbox
	public void unCheckCheckBox (String xpathLocator) {
		if(driver.findElement(By.xpath(xpathLocator)).isSelected()&& driver.findElement(By.xpath(xpathLocator)).isEnabled()) { 
			driver.findElement(By.xpath(xpathLocator)).click();    // Nếu element này được select rồi thì click để bỏ chọn 
		}
	}
	
	public boolean VerifyisElementSelected (String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator)).isSelected();
	}
	
	// Hàm scroll
	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector(cssLocator)));
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
