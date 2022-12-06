package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_29_FluentWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	FluentWait <WebDriver> fluentDriver;
	FluentWait <WebElement> fluentElement; 

	 long allTime = 15; // 15 giây
	 long pollingTime = 100; // 100 milisecond
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
	}

	public void TC_01_() {
		
		//ImplicitWait:
		// Chỉ cần 1 dòng code
		// Tần suất: Cứ 0,5s sẽ findElement lại 1 lần, nếu k tìm thấy thì đánh fail testcase
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//ExplicitWait:
		// Chỉ cần 1 dòng code sau khi khai báo và khởi tạo
		// Tần suất: Cứ 0,5s sẽ findElement lại 1 lần, nếu k tìm thấy thì không đánh fail testcase và tiếp tục chuyển sang step tiếp theo
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("haxinhgai")));
		
		//ExplicitWait custom:
		// Có thể custome thời gian giữa các poll
		explicitWait = new WebDriverWait(driver, 15, 1000); //Cứ 1000 miliseconds (1s) sẽ thao tác lại 1 lần
}
	
	public void TC_02_Fluent_Đầy_Đủ_WebDriver() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
	
	// Fluent Wait cho tim thay roi moi click Start button, cứ 500milisecond sẽ tìm
		// lại 1 lần, hết 15s sẽ faile và throw TimeoutException: Timed out after 15 seconds waiting for....
		//Khai bao
		FluentWait <WebDriver> fluentDriver; //Cũng có thể khai báo FluentWait với input là WebElement - xem TC bên dưới
		//Khoi tao
		fluentDriver = new FluentWait<WebDriver>(driver);
		//Chaining action (chuỗi các thao tác liên tiếp), bao gồm:
			//Set tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(15))
			// cứ 1/3 giây lại check lại 1 lần
		.pollingEvery(Duration.ofMillis(500)) //Đây chính là điểm khác biệt của fluent wait, có thể thay đổi tần suất
		.ignoring(NoSuchElementException.class); // Phải lựa chọn NoSuchElementException của org.openqa.selenium.
		
		//Apply điều kiện (có thể gộp luôn vào chaining actions ở trên, nhưng tách ra cho dễ hiểu
		WebElement startbutton = fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("div#start>buttn"));
			}
		});
		startbutton.click();
	}

	@Test
	public void TC_02_Fluent_Dùng_Hàm() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElement("//div[@id='start']/button").click();
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
	}
	
	
	// Để tiết kiệm công sức thì có thể viết hàm mới cho fluentwait
	// public WebElement -> return 1 element chứ k phải public void -> return none
	public WebElement findElement(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class); 
			// return 1 giá trị WebElement
			return fluentDriver.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(xpathLocator));
					}
				});
	}
	
	@Test
	public void TC_02_Fluent_Đầy_Đủ_WebElement() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countElement = findElement("//div[@id='javascript_countdown_time']");
		fluentElement = new FluentWait<WebElement>(countElement);
		fluentElement.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		fluentElement.until(new Function<WebElement, Boolean>() { //Kết quả trả về là đúng/sai, nên R sẽ là boolean
			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.print(text);
				return element.getText().endsWith("00"); // Cứ [pollingTime] sẽ trả kết quả lại 1 lần
			}
		});
	}
		
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

