package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Customize_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait; // Khai baso wait
	JavascriptExecutor jsExecutor;
	
	//Khai báo + Khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}

		// khởi tạo driver 
		driver = new FirefoxDriver(); 
		
		//System.out.println(driver.toString());. này để in ra driver của system đang dùng có ID là gì? -> GUID
		
		// Khởi tạo
		jsExecutor = (JavascriptExecutor) driver; 
		
		//driver.manage().window().setSize(new Dimension(1366,768));

		 // khởi tại wait
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Jquery1() {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//Gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 class
		
		/*Number*/
		selectInCustomDropdown("span#number-button", "ul#number-menu div", "1");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "1");
		
		// tá đaaaaaaaaaa
		// Dùng cho các dropdown khác luôn
		/*Speed*/
		selectInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast"); // Dùng cho các dropdown khác luôn
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");

		/*File*/
		selectInCustomDropdown("span#files-button", "ul#files-menu div", "jQuery.js"); // Dùng cho các dropdown khác luôn
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "jQuery.js");

		/*Title*/
		selectInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Mrs."); // Dùng cho các dropdown khác luôn
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");

		
		
		//1 - Click vào arrow icon (hoặc icon nào đó trong dropdown box) để các option xổ ra
			driver.findElement(By.cssSelector("span#number-button")).click();
		//2 - Chờ all options trong dropdown được load xổ ra xong
			//Lưu ý: Chờ lúc này k dùng hàm sleep cứng (static wait) được. Lý do: Nếu quá nhiều option thì cần nhiều hơn số thời gian đã định -> lại k đủ, nếu ít icon load xong rồi mà vẫn wait thì phí time -> thừa 
			//Phải dùng hàm wait nào đó để nó linh động thời gian:
			//Nếu chưa tìm thấy thì sẽ tìm lại trong khoảng thời gian được set
			//Nếu tìm thấy rồi thì không cần phải chờ hết khoảng time
			//=> cho nên dùng Webdriverwait
			
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));// Chờ cho tất cả những element trong cây HTML/Dom được load ra hết nhưng k quan tâm có hiển thị hay k
			//explicitWait.until(ExpectedConditions.visibilityOfAllElements(null));// Chờ cho tất cả những element trong cây HTML/DOM load ra hết VÀ hiển thị

		//3: 
			//- TH1: Nếu item mình đang cần chọn đã hiển thị -> Đi sang B4
			//- TH2: Nếu k thì cần scroll down
		//4 - Ktra text của option. Nếu đúng option mk cần tìm thì -> Click
			//Cần phải viết code để duyệt qua từng item và kiểm tra theo điều kiện: nếu text lấy ra bằng với text mình mong muốn thì click vào. Gồm 3 bước
			
			//1-  cần lưu giữ tất cả các item lại thì mới duyệt qua được:
			List<WebElement> allItem = driver.findElements(By.cssSelector("ul#number-menu div")); //Lưu 19 option vào allItem (item ý là option ý)
			
			//2- Duyệt qua từng item (cứ duyệt qua từng item thì dùng vòng lặp. Ở đây dùng "Vòng lặp foreach"
			for (WebElement item : allItem) { //Ở đây đang dùng biến 'item' để kiểm duyệt trong vòng lặp 'for'
			
				//3- Lấy ra text
				String textItem = item.getText();
			
				//4- Kiểm tra nếu text get ra = text mình expect thì click vào
				if (textItem.equals("7")) {
					//Nó sẽ nhận 1 điều kiện của Boolean (true/ false)
					// Nếu như điều kiện đúng -> vào trong hàm if
					// Nếu như điều kiện sai -> thì bỏ qua
					
				//5- Click
				item.click();
				
			//6-  break vòng lặp sau khi đã tìm được được item mong muốn => tránh mất thời gian duyệt all item.
			break;
					}	
				}
		// 7 - Verify laị 1 lần nữa xem giá trị mk click đã được hiện lên thật chưa
			Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"7");
		
		sleepInSecond(5);
		
		
	}
	
	@Test
	public void TC_02_Jquery2 () {
	
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		
		//Scroll
		scrollToElement("img.image-background"); // Gõ scrollToElement("locator đến vị trí locator muốn xem");
		sleepInSecond(5);
		
		//Select 1
		selectInCustomDropdown("button#selectize-input", "button#selectize-input+div>a", "ACCORD Trắng");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(),"ACCORD Trắng");

		//Scroll
		scrollToElement("img.img-dtcp.d-block ");
		
		//Select 2
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Bắc Giang");
		sleepInSecond(5);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Bắc Giang");

		//Select 3
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(5);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Khu vực II");
	}
	
	@Test
	public void TC_03_ReactJS() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectInCustomDropdown("div.ui.fluid.selection.dropdown", "div.ui.fluid.selection.dropdown div.visible.menu.transition>div", "Elliot Fu");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).getText(),"Elliot Fu");

	}
	
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//Hàm scroll trên browser
	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector(cssLocator)));
		//Nếu true thì scroll lên mép trên
		// Nếu false thì scroll xuống mép dưới
	}
	
	//Function: 
		//Block này không dùng cho bất kì 1 dropdown nào cụ thể cả
		// Dùng chung cho các dropdown của cả 1 dự án. Vì thường trong 1 dự án các dropdown sẽ thường giống nhau 1 source code, khác nhau thằng cha, con, cái cần lấy là gì thôi
		// Cho nên phải gọi biến
	public void selectInCustomDropdown (String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItem = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItem) {
			String textActualItem = item.getText();
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
		break;
			}
		}
		//Assert.assertEquals(driver.findElement(By.cssSelector(textVerify)).getText(),textExpectedItem); Hà không nên gộp hàm này vì nếu gộp là sai logic, bad practice. 
		// Về mặt coding thì đây là bad practice
		//Nếu có viết thành hàm thì mỗi hàm chỉ nên xử lý 1 tính năng, chứ k phải tính ít hay nhiều steps
		//Hàm selectInCustomDropdown làm 1 tính năng: chọn ietm cho 1 dropdown
		//Hàm verify item đã được chọn thì có viết hàm thì nên viết thành 1 hàm riêng như "isDropdownItemSelected(), k đưa chung vào hàm select. 
	}

	// Sleep cứng (static wait)
	// Chú ý hàm WebdriverWait này phải bỏ ngoài block
	// afterclasshttps://opensource-demo.orangehrmlive.com/
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}