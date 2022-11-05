package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_18_Random_Popup {
	WebDriver driver;
	Random rand;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_Free_Code_Geeks() {
		//Step 1:
		driver.get("https://www.javacodegeeks.com/");
		//Yêu cầu
		// 1 - Nếu popup có xuất hiện khi ở page thì click để subcribe thao tác trên popup
		// 2- Nếu k xuất hiện thì qua step tiếp theo
		
		WebElement popup = driver.findElement(By.xpath("/html/body/div[10]/div[1]")); //Nếu popup có trong DOM thì mới nên khai báo biến popup
		
		//Step 2
		if (popup.isDisplayed()) { // Nếu popup displayed
		String Email = "ha" + rand.nextInt(9999) + "@gmail.com";
		driver.findElement(By.className("lepopup-20")).sendKeys(Email); // thì input email
		sleepInSecond(4);
		driver.findElement(By.xpath("//a[@data-label='Get the Books']")).click(); // sau đó ấn submit button
		}
	
		if (!popup.isDisplayed())
			
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
