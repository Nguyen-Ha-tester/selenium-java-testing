package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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

		// Mở trang sign in của rakuna ra
		driver.get("https://secure.rakuna.co/");
	}

	// <form class="new_recruiter" id="signin-form"
	// recruiter="{&quot;email&quot;:&quot;&quot;}" action="/recruiter/sign_in"
	// accept-charset="UTF-8" method="post">

	//===> Code HTML bên trên là của trang Sign in gồm:
	// • Tên thẻ của element (Tagname): form
	// • Tên của thuộc tính (Atribute name): class="MuiTypography-root
	// MuiTypography-h4 css-130mlvd"
	// • Giá trị của thuộc tính (value): Sign In
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("signin-form")).sendKeys("haxinhgai");
	}

	@Test
	public void TC_02_Class() {

	}

	@Test
	public void TC_03_Name() {

	}
	@Test
	public void TC_04_LinkText() {

	}
	@Test
	public void TC_05_PartialLinkText() {

	}
	@Test
	public void TC_06_Tagname() {

	}
	@Test
	public void TC_07_Css() {

	}
	@Test
	public void TC_08_Xpath() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}