package Javatester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		//1. Kiểu dữ liệu nguyên thuỷ (Primitive): Liên quan đến số: 
	    
		    // * Số nguyên (Integer): byte, short, int, long (k có phần thập phân)
		       // => Mục đích:biểu diễn độ rộng để lưu trữ dữ liệu từ nhỏ đến lớn
		       byte bNUmber = 127; // giới hạn là -127 -> 127
		       short sNUmber = 129;
		       int iNumber = 13123;   // cái này dùng nhiều này
		       long lNumber = 1322112312; // cái này dùng nhiều này
		       
	    	// * Số thực (Floating point): float, double (có phần thập phân)
		       double Haheight = 1.66d; // giới hạn là từ -3.4E +38 đến 3.4E + 38
		       float Haheight1 = 1.6f;
		       
		    // * Logical: boolean (true or false)
		       boolean Haisbeautiful = true; // Hà rất xinh  //cái này dùng nhiều này
		    		   Haisbeautiful = false; // Hà không xinh
		    		   
	    	// * Kí tự (symbols): char (trong character)
		       char a = 'A'; // Ít dùng
		
		//2. Kiểu dữ liệu tham chiếu (Reference)
		    // Class
		       FirefoxDriver driver = new FirefoxDriver();
		       
		    // Interface
		       WebElement Aixinhnhat;
		       
		    // String
		       String Aixinhnhat1 = "Haxinh"; // Là cái chứa nhiều giá trị tham chiếu char
		       
		    // Object
		       Object Haxinhh;
		       
		    // Array
		       String []studentname = {"Ha", "Huy"};  //cái này dùng nhiều này => xác định trong xpath xem đâu là chuỗi string, string nằm trong dấu ""
		       
		    // Collection: Set, Queue, List
		       List <WebElement> checkboxes = driver.findElements (By.cssSelector(Aixinhnhat1));
		       
		    // Map
		       Map<String, Integer> student = new HashMap<String, Integer>();
		       
		   
// Sự khác biệt giữa kiểu tham chiếu và nguyên thuỷ: nguyên thuỷ k thay đổi giá trị, còn tham chiếu thì thay đổi
		      
		       
	}


}
