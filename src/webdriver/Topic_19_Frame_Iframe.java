package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Frame_Iframe {
	WebDriver driver;
	Select select;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	public void TC_01_Kyna_Iframe () {
		
		driver.get("https://kyna.vn/");
		//Verify cái facebook iframe hiển thị - vẫn là 1 element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
	//Neu muon thao tac element nao trong iframe/ frame thi can phai switchTo()
		//Switching Frames using WebElement (findElement) and use the switch command
		// Cách khác: 
			//Switch By ID - frame(ID), ví dụ: driver.switchTo().frame("iframeResult") => ít dùng do k phải iframe nào cx có id
			// Switch by Index - frame (index), ví dụ: driver.switchTo().frame(0); => ít dùng do maintain mất công mỗi lần update
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]"))); 
		
		String likeNumber = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(likeNumber,"165K likes");
	//Neu muon thao tac vao frame khac thi phai quay lai main page
		//Can switch ve main page
		driver.switchTo().defaultContent();
		//Tu main page switch qua iframe chat
		driver.switchTo().frame("cs_chat_iframe");
		//Click vao chat de hien len hop chat
		driver.findElement(By.cssSelector("iframe#cs_chat_iframe")).click();
		
		//Switch vao frame chat
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("1234567889");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java Bootcamp");
		driver.findElement(By.cssSelector("input.submit ")).click();
		
	//Quay lại main page từ iframe chat
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement eachCourseName : courseName) {
			Assert.assertTrue(eachCourseName.getText().contains("Excel"));
		}
	}
	
	@Test
	public void TC_02_HDFC_Frame () {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		//Switch qua frame chưa login textbox
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.cssSelector("input.text-muted")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		
	}
	//Frame và Iframe handle giống nhau, chỉ khác cái tên và đặc điểm trên DOM thôi
	
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
