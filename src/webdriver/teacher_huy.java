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

public class teacher_huy {
	WebDriver driver;
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
	
	public void TestDanTri() {
	
	//Vòng lặp for => tránh 1 kiểu code lặp lại nhiều dòng
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement eachCourseName : courseName) {
			Assert.assertTrue(eachCourseName.getText().contains("Excel"));
		}
		
	//Handle dropdown:
		Select select; //Khai báo
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH"); // khởi tạo kèm thao tác
		
	//

	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}


