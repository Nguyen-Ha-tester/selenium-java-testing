package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Command {
	WebDriver driver; // Khai báo driver
	WebElement element; // Khai báo biến element
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		driver = new FirefoxDriver(); // Khởi tạo driver. Ở đây browser mình dùng là firefox, web driver của firefox
										// (FireFoxDriver) là Gecko
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Tuong_Tac_Voi_Browser() {
		// các method tương tác với browser sẽ được đặt biến, để tránh mỗi lần verify thì lại gọi lại. 
		//Các biến này gọi là biến driver. Ví dụ driver.getURL, driver.quit()...
		//I. Test behavior hay dùng những hàm sau:
		driver.close(); //Đóng tab/window đang test
		driver.quit(); // Đóng tất cả tab/ window trên browser đang test
		driver.findElement(By.ByXPath.id("")); // Tìm ra MỘT element
		driver.findElements(By.className(osName)); // Tìm ra NHIỀU elemtent
		driver.get("URL"); //đi tới 1 URL
		driver.getCurrentUrl(); // Trả về URL của page hiện tại
		driver.getWindowHandle(); // Lấy về ID của tab/window đang đứng
		driver.getWindowHandles();// Lấy về ID của TẤT CẢ tab/window đang đứng
		driver.manage().getCookies(); //Cookies (Framework)
		driver.manage().window().fullscreen(); //Phóng screen full màn hình
		driver.manage().window().maximize(); // Phóng screen chế độ maximize
		
		// Khi di chuột vào các method thì sẽ thấy void, string,..tức là kiểu dữ liệu trả về là gì => Nếu là void: thì thôi
		//=> Nếu là string, object...thì mình có thể thực hiện thao tác GÁN biến cho dữ liệu trả về, ví dụ:
		WebElement elementNaoDo = driver.findElement(By.ByXPath.id("")); //Di chuột vào findElement thấy hiện WebElement -> dùng luôn
		List<WebElement> nhieuElementNaoDo = driver.findElements(By.className(osName)); ////Di chuột vào findElement thấy hiện List<WebElement> -> dùng luôn
		String URLNaoDo = driver.getCurrentUrl();
		
		//II. Test GUI (graphic user interface) hay dùng những hàm sau:
		//Font/ Size/ Color/ Position/ Location/ Padding...
		// Tuy nhiên làm auto mà test GUI thì phí, nên ưu tiên auto test chức năng trước (Functional UI)
		// Sau đó làm auto test cho giao diện sau (Graphic UI)
		
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS); //Chờ cho element được tìm thấy trong khoảng time bao lâu
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); //Chờ cho page load thành công sau xx giây
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS); //Chờ cho script được inject thành công vào browser/element sau xx giây
		driver.navigate().back(); // Giả lập tính năng back trên trình duyệt
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("URL");
		driver.switchTo().alert();
		driver.switchTo().frame(3000);
	}

	@Test
	public void TC_02_Tuong_Tac_Element() {
		//Các hàm tương tác với element ở trên browser sẽ thông qua các hàm element.
		//Các biến này gọi là biến element. Ví dụ elementt.clean()...
		//Dấu . đằng sau element hay driver là để thực hiện next action muốn truy cập vào 1 class, interface, object, string....
		driver.get("https://keep.google.com/u/0/#home");
		
		//Khai báo biến cùng với kiểu dữ liệu trả về của hàm findElement
		WebElement refreshButton = driver.findElement(By.xpath("//div[@class='gb_rb']")); // Khai báo biến cùng với kiểu dữ liệu trả về của hàm 
		//(Ở đây hàm findElement trả về biến WebElement
		// Sau đó thao tác với biến thôi, khỏi cần gọi nứa
		refreshButton.getLocation();
		refreshButton.click();
	
		// Dùng trực tiếp - dùng có 1 lần:
		driver.findElement(By.xpath("//div[@class='gb_rb']")).click();
		element.sendKeys("email@gmail.com");
		element.clear(); //Để xoá dữ liệu sau lần thao tác trước đó (ví dụ trong text field, những field editable....)
		element.sendKeys(Keys.ENTER); 
		element.getAttribute("Placeholder"); // Get value của attribute là placeholder
		element.getText(); //Trả về text của 1 element (Link, Header, Message, Helper Text...)
		
		// Test GUI: Font, Location, Size.... (visualize testing)
		element.getCssValue("font-size"); //trả về font size của element
		element.getRect(); // Trả về toạ độ của element
		element.getSize(); // Trả về size của element
		
		// Ít dùng
		element.getScreenshotAs(OutputType.FILE); // Chụp hình và attach vào HTML report
		element.getTagName(); // trả về thẻ HTML của element
		
		//trả về giá trị đúng/sai của 1 element, thao tác được hay không => thường trả về data type boolean (true/false) 
		element.isDisplayed();
		element.isEnabled();
		element.isSelected();
		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}