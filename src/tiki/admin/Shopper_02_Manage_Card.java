package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_02_Manage_Card {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeTest (alwaysRun =true) // để mặt định khi run xml file thì luôn run cả before & classtest
	public void initBrowser() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		System.out.println("------------- Open browser and driver------------- ");
		driver = new FirefoxDriver();
	}

	@Test(groups = { "admin", "card" })
	public void card_01_create_visa() {
	}

	@Test(groups = { "admin", "card" })
	public void card_02_view_visa() {
	}

	@Test(groups = { "admin", "card" })
	public void card_03_edit_visa() {
	}

	@Test(groups = { "admin", "card" })
	public void card_04_delete_visa() {
	}

	@AfterTest(alwaysRun =true)
	public void cleanBrowser() {
		System.out.println("------------- Clean browser and driver------------- ");
		driver.quit();
	}

}
