package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Mix_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
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
	}

	public void TC_01_Element_Found() {
		
		//Trường hợp 1: Element có xuất hiện mà không cần chờ hết TimeOut/
		//Dù có set cả 2 loại wait thì cũng không ảnh hưởng gì cả
		
		explicitWait = new WebDriverWait(driver, 5);
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Implicit Wait chỉ apply cho việc findElement thôi
		// Explicit Wait apply cho các điều kiện của element
		//=> Luôn luôn tìm element trước rồi apply điều kiện sau   
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("Thoi gian bat dau cua explicit = " + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("Thoi gian ket thuc cua explicit = " + getTimeStamp());
		
		//Implicit
		System.out.println("Thoi gian bat dau cua implicit = " + getTimeStamp());
		driver.findElement(By.id("email"));
		System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());
		
	}
	
	public void TC_02_Element_Not_Found_Implicit() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");
		
		// Implicit:
			//Cách 1:
			//output khi k tìm thấy
				// Có cơ chế tìm lại sau mỗi 500 milisecond (0.5s)
				// Hết timeout sẽ đánh fail testcase này
				// Và throw ra 1 exception: NoSuchElement
				// Mà nếu bị fail thì k đến bước tiếp theo được
				// => Nên phải dùng hàm try, catch. Khi try fail thì sẽ throw ra exception. Sau đó catch sẽ bắt cái exception lại và tiếp tục
				
				System.out.println("Thoi gian bat dau cua implicit = " + getTimeStamp());
				try {
					driver.findElement(By.id("haxinhgai"));
				} catch (Exception e) {
					System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());
				}
				
			// Cách 2:
				//List<WebElement> notFoundElement = driver.findElements(By.id("haxinhgai"));
				//System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());
			// Tương tự case implicit > explicit ở TC_03. Vì nếu k set explicit thì timeout cho explicit = 0

	}
		
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
//case 1: implicit = explicit. Hết 5s
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		//Implicit
		System.out.println("Thoi gian bat dau cua implicit = " + getTimeStamp());
		try {
			driver.findElement(By.id("haxinhgai"));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());

		}
		
		//Explicit:
		System.out.println("Thoi gian bat dau cua explicit = " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("haxinhgai")));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua explicit = " + getTimeStamp());
	}
//case 1: implicit < explicit. Hết 10s
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		
		//Implicit
		System.out.println("Thoi gian bat dau cua implicit = " + getTimeStamp());
		try {
			driver.findElement(By.id("haxinhgai"));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());

		}

		// Explicit:
		System.out.println("Thoi gian bat dau cua explicit = " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("haxinhgai")));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua explicit = " + getTimeStamp());
		}
// case 1: implicit > explicit: Hết 10s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		//Implicit
		System.out.println("Thoi gian bat dau cua implicit = " + getTimeStamp());
		try {
			driver.findElement(By.id("haxinhgai"));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua implicit = " + getTimeStamp());

		}

		// Explicit:
		System.out.println("Thoi gian bat dau cua explicit = " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("haxinhgai")));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua explicit = " + getTimeStamp());
		}// Khi trộn lẫn implicit và explicit thì sẽ chạy theo kiểu async, tức là thằng implicit start trước 1 chút hoặc đồng thời với explicit => tổng thời gian cho case này là 10s (implicit)
		// Nó sẽ chạy theo cơ thế async nên kết quả timestamp trả về có thể khác nhau mỗi lần chạy
		
		 
	}
	
	@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
			explicitWait = new WebDriverWait(driver,5);
			driver.get("https://www.facebook.com/");
			// Explicti - By là tham số nhận vào của hàm explicit - visibilytyOfElementLocated(By)
			System.out.println("Thoi gian bat dau cua explicit = " + getTimeStamp());
			try {
				explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("haxinhgai")));
			} catch (Exception e) {
				System.out.println("Thoi gian ket thuc cua explicit = " + getTimeStamp());
			}
			// Tương tự case implicit < explicit ở TC_03. Vì nếu k set implicit thì timeout cho implicit = 0
			//Chạy hết: 5s vì tham số By k phải tìm element, chịu ảnh hưởng của hàm visibilityOfElementLocated
			//Nhận timeOut của wait nào: explicit
			//Testcase: Pass
	}
			
	public void TC_05_Element_Not_Found_Explicit_Element() {
		explicitWait = new WebDriverWait(driver,5);
		driver.get("https://www.facebook.com/");

		System.out.println("Thoi gian bat dau cua explicit = " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("haxinhgai"))));
		} catch (Exception e) {
			System.out.println("Thoi gian ket thuc cua explicit = " + getTimeStamp());
		}
		
		//Chạy hết: 0s (vì implicit k set nên sẽ = 0s)
		//Nhận timeOut của wait nào: implicit
		//Testcase: Pass
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


	//Hàm show ra time-stamp mốc thời gian tại thời điểm gọi hàm này
	// time-stamp = ngày - giờ - phút - giây
	public String getTimeStamp() {
	
		Date date = new Date();
		return date.toString();
	}
}