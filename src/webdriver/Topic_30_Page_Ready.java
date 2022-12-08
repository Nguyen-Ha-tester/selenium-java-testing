package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize(); //Với hàm action thì nên full màn hình và k thao tác khi di chuột
		
		
	}

	public void TC_01_OrangeHRMlive() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		//Chờ page load xong
		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Click xong chuyển qua trang mới, đợi cho các elements load ổn định
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@title='Assign Leave']/p")).getText(),"Assign Leave");
		//Nếu dùng các loại wait như explicitWait hoặc implicitWait chờ cho element visible cũng được, nhưng cần
		//viết nhiều dòng, mỗi element lại 1 dòng code khác nhau.
		// Còn dùng kiểu wait Page Ready thì chỉ cần gọi hàm isPageLoadedSuccess() nó sẽ load cho cả page.
		// sau đó sẽ Assert.assertTrue(isPageLoadedSuccess() là được
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		//Click xong chuyển qua trang mới
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("div.oxd-table-filter-header button.oxd-icon-button")).click();
		
		driver.findElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input")).sendKeys("Peter Mac");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		//Click xong load lại 1 phần trang (phần table bên dưới)
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='data' and text()='Peter Mac']")).isDisplayed());
	}
	
	
	@Test 
	public void TC_02_Test_Project() {
		driver.get("https://blog.testproject.io/");
		action = new Actions(driver);
		//Chờ page load xong
		Assert.assertTrue(isPageLoadedSuccess());
		
		//Hover chuột vào context menu mình muốn chọn
		action.moveToElement(driver.findElement(By.cssSelector("aside#secondary input.search-field"))).perform();
		//Chờ page load xong. Tại sao khi hover thôi mà cũng cần wait cho page ready? Vì còn code chạy ngầm (xem ở network tab trong devtool)
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("aside#secondary input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("aside#secondary span.glass")).click();
	
		//Ở đoạn này chỉ page wait thôi thì vẫn chưa ready, cần dùng thêm wait. 
		// Rất nhiều cách kết hợp thêm, ví dụ dưới đây dùng explicitWait cho đến khi 1 element nào đó visible
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("aside#secondary input.search-field")));

		Assert.assertTrue(isPageLoadedSuccess());

		List<WebElement> postSelenium = driver.findElements(By.xpath("//div[@class='posts-wrap']/div"));
		for (WebElement eachpostSelenium : postSelenium) {
			Assert.assertTrue(eachpostSelenium.getText().contains("Selenium"));	}		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isPageLoadedSuccess()
	{	
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		
		// Điều kiện để cho jQuery được load xong
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>(){
		@Override
		public Boolean apply(WebDriver driver) {
			return (Boolean) jsExecutor.executeScript("return (window.jQuery!= null) && (jQuery.active == 0);");
		}//Lưu ý có 1 số page, user phải thao tác lên màn hình thì mới return true
	};
		// Điều kiện để cho DOM được load xong
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>(){
		@Override
		public Boolean apply(WebDriver driver) {
			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		}
	};
	return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);


	}
}
