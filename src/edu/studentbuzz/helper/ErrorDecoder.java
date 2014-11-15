package edu.studentbuzz.helper;
public class ErrorDecoder {
	int code;
	String report;
	public ErrorDecoder(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getReport() {
		genReport();
		return report;
	}
	private void genReport() {
		switch (code) {
			case 1:
				report = "Invalid email or password.";
				break;
			case 2:
				report = "Incomplete data supplied.";
				break;
			case 3:
				report = "Given passwords did not match.";
				break;
			case 4:
				report = "Email already registered.";
				break;
			case 5:
				report = "Account is not active.";
				break;
			case 6:
				report = "Please Login Again.";
				break;
			case 7:
				report = "Atleast one subcategory must be selected.";
				break;
			case 8:
				report = "old Password is wrong";
				break;
			case 9:
				report = "Invalid Department";
				break;
			case 10:
				report = "Invalid SubCategory";
				break;
			case 11:
				report = "Invalid Student";
				break;
			case 12:
				report = "Invalid Faculty";
				break;
			case 13:
				report = "You can not delete your account.";
				break;
			case 14:
				report = "Invalid Admin";
				break;
			case 15:
				report = "Error while uploading.";
				break;
			case 16:
				report = "No Profile picture to remove.";
				break;
			case 17:
				report = "Invalid Operation";
				break;
			case 18:
				report = "Invalid Post";
				break;
			case 19:
				report = "Invalid Attachment";
				break;
			case 20:
				report = "Not Allowed";
				break;
			case 21:
				report = "Invalid Post";
				break;
			case 22:
				report = "You can not delete this department.";
				break;
			case 23:
				report = "Invalid Report";
				break;
			case 24:
				report = "You can not delete this subcategory.";
				break;
			default:
				report = "Invalid Error Code";
				break;
		}
	}
	public String getHTML() {
		genReport();
		return "<center><div style='color:red; border-radius: 8px;border: 1px solid  gray;width:80%'>" + report + "</div></center></br>";
	}
}