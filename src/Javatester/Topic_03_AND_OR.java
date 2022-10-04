package Javatester;

public class Topic_03_AND_OR {

	public static void main(String[] args) {
		boolean statusA;
		boolean statusB;
		
		
		//Ví dụ với AND &&
		//Cả 2 cái đều đúng ->  kết quả mới đúng
		// Chỉ cần 1 cái sai -> kết quả sai
		statusA = false;
		statusB = true;
		System.out.println("Ket qua la " + (statusA && statusB));
		
		//Ví dụ với OR ||
		// Chỉ cần 1 cái đúng -> Kết quả đúng
		statusA = false;
		statusB = true;
		System.out.println("Ket qua la " + (statusA || statusB));
		
	}

}
