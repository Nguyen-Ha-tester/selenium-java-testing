package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Action_UserInteraction {
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

	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		//Hover vào element
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform(); // cuối phải có perform thì mới chạy được
		//sleepInSecond(3);
		//Bắt tooltip trên window: ctrl \
		
		//Verify tooltip xuất hiện
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
		
		//** Chú ý: Khi đang run test, k đc di chuyển chuột hoặc dùng bàn phím
		
	}
	
	public void TC_02_Hover_2() {
		driver.get("https://www.myntra.com/");
		
		//Trang này k click chuột phải để inspect như bình thường được
		// Chuyển qua bật dev tool lên
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click(); //Hoặc action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/kids-home-bath");
	}

	@Test
	public void TC_03_Hover_03() {
		driver.get("https://www.fahasa.com/");		
		//alert = driver.switchTo().alert();
		//alert.dismiss();
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		// Hàm isDisplayed() sẽ dùng text ở dưới HTML
		// Hàm getText() sẽ get Text mà trên UI nhìn thấy, cho nên ở đây k dùng isDisplayed mà dùng getText()
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']")).getText(),"Kỹ Năng Sống");
		
	}
	
	public void TC_04_Click_Hold_Block() {

		driver.get("https://automationfc.github.io/jquery-selectable/");
		//Chon 12 so
		List<WebElement> allNumber =	driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//Numbe dau tien la so 0 -> 9 để chọn từ 1-10 vi index bat dau tu 0
		//Click va giu chuot vao so bat dau -> di chuột đến số có thứ tự 9 -> nhả chuột -> action
		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(9)).release().perform();
		sleepInSecond(5);
		
		//Kiểm tra đã chọn được 9 số
		List<WebElement> allNumberSelectedList =	driver.findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allNumberSelectedList.size(),6);
		
		/*PHẦN THAM KHẢO*/
		//Kiểm tra text đã chọn của element (verify từng cái text 1)
			//Define ra 1 mảng chứa các text mình cần
			String[] allNumberSelectedStringActual = {"1","2","5","6","9","10"};
			
			//Khai báo ra 1 ArrayList để lưu lại các giá trị được get trong List bên trên
			//Lí do: Do allNumberSelectedString đang ở cấu trúc dữ liệu array, loại dữ liệu String
			//Còn allNumberSelectedList đang ở cấu trúc dữ liệu List, loại dữ liệu WebElement
			//Nên phải khai báo 1 ArrayList mới để kế thừa List, đưa về cùng cấu cấu Array để so sánh với allNumberSelectedString
			ArrayList<String> allNumberSelectedStringExpected = new ArrayList<String>();
			
			//Dùng vòng lặp để duyệt qua list đã liệt kê ở trên
			for (WebElement number :allNumberSelectedList) {
				allNumberSelectedStringExpected.add(number.getText());
			}
			
			//Ép kiểu Array qua List
			Assert.assertEquals(allNumberSelectedStringExpected, Arrays.asList(allNumberSelectedStringActual));
	}
	
	public void TC_05_Click_Hold_Random() {

		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber =	driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		//Nhấn phím Ctr xuống (chưa nhả ra)
		action.keyDown(Keys.CONTROL).perform(); // Nhấn xuống
		//CLick vào số bắt đầu
		//Click vào các số cần chọn
		action.click(allNumber.get(1)).click(allNumber.get(3)).click(allNumber.get(5)).click(allNumber.get(6)).perform();
		//Nhả phím Ctr
		action.keyUp(Keys.CONTROL).perform(); // Thả phím
		sleepInSecond(5);
		
		//Kiểm tra đã chọn được 4 số
		List<WebElement> allNumberSelectedList =	driver.findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allNumberSelectedList.size(),4);
		
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
