package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Action_UserInteraction_II {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	
	
	public void TC_01_Right_Click() { 	//Click chuột phải, hay còn gọi là Context Menu, vì khi click thì sẽ hiện lên 1 menu có những tính năng (Edit, Copy, Rename, Paste)

	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	
	//Click chuột phải dùng hàm contextClick()
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	sleepInSecond(3);
	
	//Hover chuột vào context menu mình muốn chọn
	action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
	sleepInSecond(3);
	
	//Verify Element paste đã được hover
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-visible.context-menu-hover")).isDisplayed());
	
	//Click vào Paste
	driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-visible.context-menu-hover")).click();
	sleepInSecond(3);

	//Chấp nhận alert
	driver.switchTo().alert().accept();
	
	//Verify Element paste đã k còn đc hover
	Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());


	
	}
	
	public void TC_02_Drag_Drop_HTML_4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		 WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		 WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		 
		 //Drag & drop
		 action.dragAndDrop(smallCircle, bigCircle).perform();
		 sleepInSecond(3);
		 
		 //Verify text cua bigcircle change
		 Assert.assertEquals(bigCircle.getText(),"You did great!");
		 
		 //Verify mau cua bigcircle chuyen sang mau xanh
			String rgbaColor = bigCircle.getCssValue("background-color");
			
			//rgbaColor to Hexa Color
			String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
			
			//Verify background color
			Assert.assertEquals(hexaColor, "#03A9F4");
	}

	
	@Test
	public void TC_03_Drag_Drop_HTML_5() {
		//Tham khảo tren github cua a Dam
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
