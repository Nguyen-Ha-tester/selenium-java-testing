package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotations {
	  @Test
	  public void TC_01() {
		  System.out.println("Testcase 1");
	  }
	  
	  @Test
	  public void TC_02() {
		  System.out.println("Testcase 2");
	  }
	  
	  @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("Before Method");
	  }
	
	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("After Method");
	  }
	
	  @BeforeClass
	  public void beforeClass() {
		  System.out.println("Before Class");
	  }
	
	  @AfterClass
	  public void afterClass() {
		  System.out.println("After Class");
	  }
	
	  @BeforeTest
	  public void beforeTest() {
		  System.out.println("Before Tes");
	  }
	
	  @AfterTest
	  public void afterTest() {
		  System.out.println("After Test");
	  }
	
	  @BeforeSuite
	  public void beforeSuite() {
		  System.out.println("Before Suite");
	  }
	
	  @AfterSuite
	  public void afterSuite() {
		  System.out.println("After Suite");
	  }

}
