package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	WebDriverWait explicitWait;
	// Đặt file name
	String fileName1 = "1.jpg";
	String fileName2 = "2.png";

	// Đặt file path
	String filePath1 = projectPath + "\\UploadFile\\" + fileName1;
	String filePath2 = projectPath + "\\UploadFile\\" + fileName2;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		driver = new FirefoxDriver();
		//Bất kì cái gì khởi tạo liên quan tới Selenium thì đều khởi tạo sau driver 
		//Apply 15s cho các điều kiện trạng thái cụ thể
		explicitWait = new WebDriverWait(driver, 15);

		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().window().maximize();


	}

	public void TC_01_Not_Enough_Time() {
		explicitWait = new WebDriverWait(driver, 1); //Apply chỉ đợi 1s

		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();

		// Thiếu thời gian
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

		
		//Get text & verify
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());

	}

	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 5); //Apply đợi đúng 5s

		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();

		// Thiếu thời gian
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

		
		//Get text & verify
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());

	}
	
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 10); //Apply đợi tận 10s

		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();

		// Thiếu thời gian
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		//Get text & verify
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

	}

	//LƯU Ý:
	//Ở trên ngoài wait cho element finish hiển thị (visibility):
			//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
	//Còn có thể wait cho element loading icon biến mất (invisibility):
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/uploadFiles");
		
		explicitWait = new WebDriverWait(driver, 100); //Apply đợi tận 10s

		//Đợi cho nút add file được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add files']")));
		
		//Upload 1 lần nhiều file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1 + "\n" + filePath2);
		
		//Wait cho tới khi icon loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.text-center i.fas.fa-spinner.fa-spin")));
		
		//Wait cho upload message thành công được visible, wait cho nó hiển thị rồi mới verify
		//vì hàm dòng explicitWait ở trên trả về 1 webElement nên có thể đưa vào hàm verify được
		//Wait + Verify message hiển thị đúng
		Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout h5"))).getText(), "Your files have been successfully uploaded");
		
		//Wait cho button show file hiển thị
		//vì hàm dòng explicitWait ở trên trả về 1 webElement nên có thể đưa vào hàm click được
		//Wait + click button show file
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();
		
		//Wait cho các file name với button Download và nút Play được hiển thị
		//vì hàm dòng explicitWait ở trên trả về 1 webElement nên có thể đưa vào hàm verify được
		//Wait + Verify 2 nút Download và Play hiển thị
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//span[text()='2.png']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//span[text()='3.png']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//span[text()='2.png']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//span[text()='3.png']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		
	}
	@Test
	public void TC_04_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	
		explicitWait = new WebDriverWait(driver, 50);
		
		//Wait cho Date picker được hiển thị trong 15s (Sử dụng visibility)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		
		//Verify trước ajax found thì dòng no selected dates hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='No Selected Dates to display.']")).isDisplayed());
		
		//Chọn ngày bất kì tương ứng trong tháng/ năm hiện tại. Ở đây chọn ngày 29
		// Trước khi chọn thì wait cho ngày 29 clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='29']")));
		driver.findElement(By.xpath("//a[text()='29']")).click();
		
		//Wait cho đến khi "ajax loading icon" không còn visisble (invisibility)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("body>div>div.raDiv")));
		
		//Wait cho ngày vừa được chọn là ngày được phép click trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a")));
		
		//Verify cho selected dates là ngày đúng
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Tuesday, November 29, 2022']")).isDisplayed());
		
		
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	
	}
}
