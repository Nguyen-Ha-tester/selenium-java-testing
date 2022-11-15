package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class teacher_huy {
	WebDriver driver;
	JavascriptExecutor jsExecutor;s
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_TEMPLATE() {
	
	//Vòng lặp for => tránh 1 kiểu code lặp lại nhiều dòng
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement eachCourseName : courseName) {
			Assert.assertTrue(eachCourseName.getText().contains("Excel"));	}
		
	//Handle dropdown:
		Select select; //Khai báo
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH"); // khởi tạo kèm thao tác
		
	//Iframe/Frame:
		//Switch qua frame chưa login textbox
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		//Switch back to main page
		driver.switchTo().defaultContent();

	
	//Accept alert
	alert.accept();
	}

	private void scrollToElement(String string) {
		// TODO Auto-generated method stub
		
	}

	// Hàm scroll trên browser
	scrollToElement("img.img-dtcp.d-block ");

	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
		// Nếu true thì scroll lên mép trên
		// Nếu false thì scroll xuống mép dưới
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
