package testng;

import org.testng.annotations.Test;

public class Topic_02_Priority {

	@Test(priority = 3, description = "RWD-4023: Check create new employee") // Mô tả testcase rõ hơn để sau này đọc lại nắm business, nếu testcase có sai thì quay lại sửa/ tạo ticket mới
	public void EndUser_Create_New_Employee() {
	}

	@Test(priority = 2)
	public void EndUser_View_New_Employee() {
	}

	@Test(priority = 1)
	public void EndUser_Edit_New_Employee() {
	}

	@Test(enabled = false)
	public void EndUser_Move_New_Employee() {
	}

}
