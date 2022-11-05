package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Random_Popup {
	WebDriver driver;
	Random rand;
	JavascriptExecutor executor;
	Actions action;
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
		rand = new Random();
		executor = (JavascriptExecutor)driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	public void TC_01_Free_Code_Geeks_In_HTML() {
		//Step 1:
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(15);
		
		WebElement popup = driver.findElement(By.cssSelector
				("div.lepopup-popup-container>div:not([style*='display:none'])")); //Nếu popup có trong DOM thì mới nên khai báo biến popup
		Assert.assertTrue(popup.isDisplayed());

		//Step 2
			//Yêu cầu
			// 1 - Nếu popup có xuất hiện khi ở page thì click để subcribe thao tác trên popup
			// 2- Nếu k xuất hiện thì qua step tiếp theo
		if (popup.isDisplayed()) { // Nếu popup displayed
		String Email = "ha" + rand.nextInt(9999) + "@gmail.com";
		driver.findElement(By.xpath("//input[@name='lepopup-20']")).sendKeys(Email); // thì input email
		sleepInSecond(4);
		driver.findElement(By.xpath("//a[@data-label='Get the Books']")).click(); // sau đó ấn submit button
		sleepInSecond(5);
		}
		Assert.assertFalse(popup.isDisplayed());
		
		//Step 3
		driver.findElement(By.id("s")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("button.search-button")).click();		
		
		//Step 4
		Assert.assertTrue(driver.findElement(By.cssSelector("article.item_1 h2 a")).getText().contains("JavaScript And Selenium"));
	}

	public void TC_02_KMPlayer_In_HTML() {
		driver.get("https://www.kmplayer.com/home");
		sleepInSecond(3);
		
		WebElement popup = driver.findElement(By.cssSelector("img#support-home"));
		Assert.assertTrue(popup.isDisplayed());
		if (popup.isDisplayed()) { 
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//map[@name='support-home']//area[2]")));
		sleepInSecond(3);
		}
		Assert.assertFalse(popup.isDisplayed());
		driver.findElement(By.xpath("//ul[@class='gnb']/li[2]/a")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.kmplayer.com/pc64x");
	}
	
	@Test
	public void TC_03_Dehieu_NOT_In_HTML() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(3);
		
		List<WebElement> popup = driver.findElements(By.cssSelector("section#popup"));
		
		if (popup.size()>0 && popup.get(0).isDisplayed()) { 
		executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.close")));
		sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys("BMS");
		driver.findElement(By.cssSelector("i.fa.fa-search")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://dehieu.vn/courses?keyword=BMS&author_id=");
		}
	//Với trường hợp popup not in dom thì khi popup k xuất hiện, hàm findElement sẽ k tìm thấy element
	//Case này khác với case của Fixed Popup, Fixed popup mk k điều khiển chủ động sự hiện diện của popup hay không, nên phải đo size() theo hàm if,else

	
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
