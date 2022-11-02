package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Alert {
	// Khai báo biến driver
	WebDriver driver;
	
	//Khai báo biến alert
	Alert alert;
	// Khai báo + Khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		// Khởi tao driver để thao tác trên trình duyệt firefox
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Cái implicitWait nó là thời gian dùng để findElement 
		//Nếu không có thì khi chạy lệnh findElement nó search k ra thì sẽ báo fail luôn
		//Còn nếu có thời gian thì sau 0.5s nó sẽ search lại thêm 1 lần nữa 
		//Nếu máy load chậm do mạng mà k có implicitWait thì khả năng fail sẽ rất cao á

	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Mở alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		//Switch (khởi tạo biến alert): hứng giá trị cho biến alert đã khai báo ở trên
		//Dùng khi alert  đang xuất hiện
		alert = driver.switchTo().alert();
		
		//verify text ở trong alert
		Assert.assertEquals(alert.getText(),"I am a JS Alert");
		
		//Accept alert
		alert.accept();
		
		//Verify accept alert thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {

		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
	}
		

	@Test
	public void TC_03_Prompt_Alert() {
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		String name = "haxinhdep";
		alert.sendKeys(name);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: " + name);

	}
	
	@Test
	public void TC_04_Accept_Alert_Login() {
		driver.get("https://demo.guru99.com/v4");
		
		//Click login button
		driver.findElement(By.name("btnLogin")).click();
		
		alert = driver.switchTo().alert();
		
		//Verify trc khi accept
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		//Accept alert
		alert.accept();
		
		//Verify sau khi accept xong thì trở về trang login cũ
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
	//Cách 1: Chạy được cho tất cả các browser	
		//Pass hẳn User/ password vào URL trước. Ở đây username và password là admin
		//http://username:password@the-internet.herokuapp.com/basic_auth
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
	}	
	public void TC_06_Authentication_Alert_2() {
	//Cách 2: 
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getAuthenticationURL(basicAuthenURL));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
					 	
	}
	public void TC_07_Authentication_Alert_3 () {
		//Case ngoại lệ rất hiếm gặp. Vì nó không work với cách truyền Username, Password vào trong URL luôn
		//Tham khảo
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getAuthenticationURL(String basicAuthenURL) {
		String[] authenURLArray = basicAuthenURL.split("//");
		return basicAuthenURL = authenURLArray[0] + "//" +"admin" + ":" + "admin" +"@" + authenURLArray[1];
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}
;