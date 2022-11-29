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

	@Test
	public void TC_01_Not_Enough_Time() {
		explicitWait = new WebDriverWait(driver, 1); //Apply chỉ đợi 1s

		//Click vào Start button
		driver.findElement(By.cssSelector("div#start button")).click();

		// Thiếu thời gian
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

		
		//Get text & verify
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());

	}

	@Test
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
	

	@Test
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
	public void TC_04_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	
		explicitWait = new WebDriverWait(driver, 15);
		
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
