package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_TechPanda() {
		
		// 1. Truy cập vào trang http://live.techpanda.org/
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		
		// 2. Get domain của page
		executeForBrowser("return document.domain;");
		Assert.assertEquals(executeForBrowser("return document.domain;"),"live.techpanda.org");

		// 3. Get URL của page
		executeForBrowser("return document.URL");
		Assert.assertEquals(executeForBrowser("return document.URL;"),"http://live.techpanda.org/");
		sleepInSecond(3);
		// 4. Open MOBILE page
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		// 5. Add sp SAMSUNG GALAXY vào cart
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
		sleepInSecond(3);
		
		// 6. Verify message được hiển thị
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		sleepInSecond(3);
		
		// 7. Open Customer Service page
		scrollToElementOnTop("//span[text()='Company']");
		hightlightElement("//span[text()='Company']");
		sleepInSecond(2);
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.URL;"),"http://live.techpanda.org/index.php/customer-service/");
		
		// 8. Scroll tới element Newsletter textbox nằm ở cuối page
		scrollToElementOnTop("//input[@id='newsletter']");
		hightlightElement("//input[@id='newsletter']");
		sleepInSecond(2);

		// 9. Input email hợp lệ vào Newsletter textbox
		sendkeyToElementByJS("//input[@id='newsletter']", "ha" + getRandomNumber() + "@gmail.com");
		sleepInSecond(2);
		
		// 10. Click vào Subscribe button
		hightlightElement("//button[@class='button']");
		clickToElementByJS("//button[@class='button']");
		sleepInSecond(3);
		
		// 11. Verify text có hiển thị 'Thank you for your subscription'
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		// 12. Navigate tới domain http://demo.guru99.com/v4/
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		Assert.assertEquals(executeForBrowser("return document.URL;"),"https://demo.guru99.com/v4/");
		
	}
	
	@Test
	public void TC_02_HTML5_Validation_Massage() {
		driver.get("https://warranty.rode.com/");
		
		//Đặt biến cho dễ dùng với các step sau
		String registerButton = "//button[contains(text(),'Register')]";
		String firstName = "//input[@id='firstname']";
		String surName = "//input[@id='surname']";
		String emailAddress = "//div[contains(text(),'Register')]/parent::div//input[@id='email']";
		String password = "//div[contains(text(),'Register')]/parent::div//input[@id='password']";
		String confirmPassword = "//div[contains(text(),'Register')]/parent::div//input[@id='password-confirm']";
		
		//kiểm tra validation message ở field firstname & điền giá trị vào field để đến step tiếp theo
		clickToElementByJS(registerButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage(firstName), "Please fill out this field.");
		sendkeyToElementByJS(firstName,"Automation");
		sleepInSecond(3);
		
		//kiểm tra validation message ở field surname & điền giá trị vào field để đến step tiếp theo
		clickToElementByJS(registerButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage(surName), "Please fill out this field.");
		sendkeyToElementByJS(surName,"Test");
		sleepInSecond(3);

		//kiểm tra validation message ở field email & điền giá trị vào field để đến step tiếp theo
			//Thử nhập invalid email
		clickToElementByJS(registerButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage(emailAddress), "Please fill out this field.");
		sendkeyToElementByJS(emailAddress,"auto@test@gmail.com");
		sleepInSecond(3);
			//Thử lại với valid email
		clickToElementByJS(registerButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage(emailAddress), "Please enter an email address.");
		sendkeyToElementByJS(emailAddress,"autotest@gmail.com");
		sleepInSecond(3);
		
		//kiểm tra validation message ở field password & điền giá trị vào field để đến step tiếp theo
		clickToElementByJS(registerButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage(password), "Please fill out this field.");
		sendkeyToElementByJS(password,"1111111111111");
		sleepInSecond(3);
		
		// kiểm tra validation message ở field password & điền giá trị vào field để đến step tiếp theo
		clickToElementByJS(registerButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage(confirmPassword), "Please fill out this field.");
		sendkeyToElementByJS(confirmPassword, "1111111111111");
		sleepInSecond(3);
		
		//CLick đăng kí và verify đăng kí thành công
		clickToElementByJS(registerButton);
		sleepInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.URL;"),"https://warranty.rode.com/registration");
	}
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public int getRandomNumber() {
		return new Random().nextInt(9999);
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
