package edu.studentbuzz.helper;
public class MsgDecoder {
	int code;
	String report;
	public MsgDecoder(int code) {
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
				report = "Your Account updated Successfully.";
				break;
			case 2:
				report = "New Department Added Successfully.";
				break;
			case 3:
				report = "New Faculty Added Successfully.";
				break;
			case 4:
				report = "New Student Added Successfully.";
				break;
			case 5:
				report = "New SubCategory Added Successfully.";
				break;
			case 6:
				report = "Your Account Deleted Successfully.";
				break;
			case 7:
				report = "Department Succesfully Updated.";
				break;
			case 8:
				report = "Faculty Succesfully Updated.";
				break;
			case 9:
				report = "Student Succesfully Updated.";
				break;
			case 10:
				report = "SubCategory Succesfully Updated.";
				break;
			case 11:
				report = "Account Activated Succesfully.";
				break;
			case 12:
				report = "Account Deactivated Succesfully.";
				break;
			case 13:
				report = "Profile Picture Changed";
				break;
			case 14:
				report = "New Administrator Added Successfully.";
				break;
			case 15:
				report = "Profile Picture Removed.";
				break;
			case 16:
				report = "New Post Added Successfully.";
				break;
			case 17:
				report = "New Attachment Added Successfully.";
				break;
			case 18:
				report = "Attachment Deleted Successfully.";
				break;
			case 19:
				report = "Post Succesfully Updated.";
				break;
			case 20:
				report = "Post Succesfully Deleted.";
				break;
			case 21:
				report = "Comment Succesfully Deleted.";
				break;
			case 22:
				report = "Report Succesfully Sent.";
				break;
			case 23:
				report = "Department Succesfully Deleted.";
				break;
			case 24:
				report = "Report Succesfully Deleted.";
				break;
			case 25:
				report = "SubCategory Succesfully Deleted.";
				break;
			case 26:
				report = "Faculty Succesfully Deleted.";
				break;
			case 27:
				report = "Student Succesfully Deleted.";
				break;
			default:
				report = "Invalid Message Code";
				break;
		}
	}
	public String getHTML() {
		genReport();
		return "<center><div style='color:green; border-radius: 8px;border: 1px solid  gray;width:80%'>" + report + "</div></center></br>";
	}
}