package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Window_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
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
		jsExecutor = (JavascriptExecutor) driver; 
		alert = driver.switchTo().alert();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void TC_01_Window_AUTOMATIONFC_ID() {
		//Parent Page - main page:
				driver.get("https://automationfc.github.io/basic-form/index.html");
				
			 //Window/tab nó sẽ có 2 hàm để lấy ra ID của Window/tab đó
				// 1 - Lấy ra ID của window/tab mà nó đang đứng (active)
				String parentPageWindowID = driver.getWindowHandle();
				System.out.println("Parent Page: " + parentPageWindowID);
				
				//Click vào Google button để b ra 1 tab mới
				driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
				sleepInSecond(3);
			
				// 2 - Lấy ra t cả ID của c window/tab
				Set <String> allWindowIDs = driver.getWindowHandles();
				System.out.println("All GUID hiện tại: " + allWindowIDs);
					//Sự khác nhau giữa Set & List:
					// List<WebElement> ... = driver.findElement(By,xpath(""); => Set không cho phép trùng
					// Set <String> .... = driver.getWindowHandles(); => List cho phép trùng/ null
				
				//Sau do dung vong lap for-each de ktra
				// Neu nhu cai ID nao khac ID cua Parent Page thi switch vao
				 for (String id:allWindowIDs) {
					 if (!id.equals(parentPageWindowID)) {
						 driver.switchTo().window(id); //Switch vao ID cua tab Google
						 sleepInSecond(3);
					 }
				 }
				Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
				sleepInSecond(3);
				// Tu tab Google switch quay tro lai parent page 
		 		String googlePageWindowID = driver.getWindowHandle();
		 		 for (String id:allWindowIDs) {
		 			 if (!id.equals(googlePageWindowID)) {
		 				 driver.switchTo().window(id); //Switch vao ID cua tab Google
		 			sleepInSecond(3);
		 			 }
		 		 }
				switchToWindowByID(googlePageWindowID);
			// Chia 2 case:
				// Case 1: chỉ có 2 window hoặc 2 tab
				
				
				// Case 2: Nhieu hơn 2 window/ tab
			
			
			
			
	}	
	//Cách switch by Title có thể áp dụng chỉ 2 ID (window/tab)
	public void TC_02_Window_AUTOMATIONFC_ID_Phien_ban_rut_gon() {


			driver.get("https://automationfc.github.io/basic-form/index.html");
				
			driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
			sleepInSecond(3);
					
			String parentPageWindowID = driver.getWindowHandle();
			switchToWindowByID(parentPageWindowID);
			
			Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
			sleepInSecond(3);

			String googlePageWindowID = driver.getWindowHandle();
			switchToWindowByID(googlePageWindowID);
	}

	//Cách switch by Title có thể áp dụng từ 2 ID (window/tab) trở lên  => có thể cover luôn case có 2 ID (window/tab)
	
	public void TC_03_Window_AUTOMATIONFC_Title() { 
		
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		// Click vào Google để hiện ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		//Switch qua Google
		switchToWindowByTitle("Google");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
		sleepInSecond(3);
		
		//Switch về tab cũ
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		
		//Click vào Facebook để hiện ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
	
		//Switch qua Facebook
		switchToWindowByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
		sleepInSecond(3);
		
		//Switch về tab cũ
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(),"SELENIUM WEBDRIVER API");
		sleepInSecond(3);
	}
	
	@Test
	public void LiveGuru () {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		String parentID = driver.getWindowHandle();
		//Click vào Element
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[3]//a[@class='link-compare']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[3]//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath("//button[@title='Compare']")).click();

		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")),"Compare Products");
		sleepInSecond(3);
		
		closeAllWindowWithoutParent(parentID);
		
		switchToWindowByTitle("Mobile");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		alert.accept();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");


		

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Hàm scroll trên browser

	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
		// Nếu true thì scroll lên mép trên
		// Nếu false thì scroll xuống mép dưới
	}


	public void switchToWindowByID (String WindowID) {
		Set <String> allWindowIDs = driver.getWindowHandles();
		for (String id:allWindowIDs) {
			 if (!id.equals(WindowID)) {
				 driver.switchTo().window(id); //Switch vao ID cua tab Google
				 sleepInSecond(3);
			 }
		 }
	}
	
	public void switchToWindowByTitle (String expectedWindowTitle) {
		Set <String> allWindowIDs = driver.getWindowHandles();
		for (String id:allWindowIDs) {
				 driver.switchTo().window(id); //Switch trước
				 sleepInSecond(3); 
				 String WindowTitle = driver.getTitle();
				 if (WindowTitle.equals(expectedWindowTitle)) { //Lấy ra ID của page và kiểm tra title xem có trùng khớp không
					 break;
			 }
		 }
	}
	
	//Đóng cửa sổ, tránh đóng nhầm
	public void closeAllWindowWithoutParent (String parentID) {
		Set <String> allWindowIDs = driver.getWindowHandles();
		for (String id:allWindowIDs) {
				 if (!id.equals(parentID)) {
					 driver.close();
					 sleepInSecond(3);
					 break;
			 }
		 }
	}
	
	public void sleepInSecond(long timeInSecond) {
			try {
				Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
	
			}
		}
	}
