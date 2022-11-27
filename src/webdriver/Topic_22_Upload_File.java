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

public class Topic_22_Upload_File {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	//Đặt file name
	String fileName1 = "1.jpg";
	String fileName2 = "2.png";
	
	//Đặt file path
	String filePath1 = projectPath + "\\UploadFile\\" + fileName1;
	String filePath2 = projectPath + "\\UploadFile\\" + fileName2;
		//Vì projectPath = D:\CoursethayDam\02 -Selenium Webdriver
		//Và filePath1 = D:\CoursethayDam\02 -Selenium Webdriver\UploadFile\1.jpg
			//thành công thức lấy đường dẫn (path) tương đối. Note: 1 xiệc hay 2 xiệc nhưu nhau
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_Upload_1_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// 1- Load 1 lần 1 file
		//BẮT BUỘC: CHỈ SENDKEYS UPLOAD VÀO LOCATOR: //input[@type='file']
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1);
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath2);
		sleepInSecond(3);
		
		// 2- Verify load file thành công
		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']/tr[1]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']/tr[2]")).isDisplayed());

		// 3- UPload file
			//Chạy vòng lặp click vào từng cái để tránh phải code nhiều
		List <WebElement> uploadButton = driver.findElements(By.xpath("//tbody[@class='files']/tr//button[contains(@class,'start')]"));
		for (WebElement button :uploadButton) {
			button.click();
			sleepInSecond(3);
		}
		// 4- Verify upload file thành công (Link)
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + fileName1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + fileName2 + "']")).isDisplayed());
		
		// 5- Verify upload file thành công (Phần Hình)
		Assert.assertTrue(isImageLoaded("//table//img[contains(@src,'" + fileName1 + "')]"));
		Assert.assertTrue(isImageLoaded("//table//img[contains(@src,'" + fileName2 + "')]"));

	}
	@Test
	public void TC_02_Load_Nhieu_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// 1- Load 1 lần nhieu file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1 + "\n" + filePath2);
		sleepInSecond(3);
		
		// 2- Verify load file thành công
		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']/tr[1]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']/tr[2]")).isDisplayed());

		// 3- UPload file
			//Chạy vòng lặp click vào từng cái để tránh phải code nhiều
		List <WebElement> uploadButton = driver.findElements(By.xpath("//tbody[@class='files']/tr//button[contains(@class,'start')]"));
		for (WebElement button :uploadButton) {
			button.click();
			sleepInSecond(5);
		}
		// 4- Verify upload file thành công (Link)
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + fileName1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + fileName2 + "']")).isDisplayed());
		sleepInSecond(3);
		
		// 5- Verify upload file thành công (Phần Hình)
		Assert.assertTrue(isImageLoaded("//table//img[contains(@src,'" + fileName1 + "')]"));
		Assert.assertTrue(isImageLoaded("//table//img[contains(@src,'" + fileName2 + "')]"));

	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}
