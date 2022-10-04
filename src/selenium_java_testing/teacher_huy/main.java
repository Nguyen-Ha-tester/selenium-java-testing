package selenium_java_testing.teacher_huy;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("ha yeu huy nhieu chut chut");
//		int a = 1;
//		int b = 2;
//		
//		int tong = a + b;
//		System.out.println("tổng bằng " + tong);
		String c = "ha xinh gai";
		String d = "huy dep trai";
		String tong_string = c + " yeu " + d;
		System.out.println(tong_string);
		
		System.out.println(TinhTong("Ha","Huy"));
	
//		int bien_mot = 10;
//		int bien_hai = 20;
//		System.out.println(bien_mot + bien_hai);
		
	}
	
	//Format hàm: [phạm vi truy cập] [kiểu trả về] tên hàm(optional: biến truyền vào nếu có){ ... code }
	//https://www.w3schools.com/java/java_methods_param.asp
	public static String TinhTong(String a, String b) {
		String result = a + " yeu " + b;
		
		return result;
	}

}
