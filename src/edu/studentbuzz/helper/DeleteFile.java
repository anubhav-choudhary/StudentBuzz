package edu.studentbuzz.helper;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
public class DeleteFile {
	private String path;
	private int code;
	private HttpServletRequest request;
	public DeleteFile() {}
	public DeleteFile(String path, int code, HttpServletRequest request) {
		this.path = path;
		this.code = code;
		this.request = request;
	}
	public void config(String path, int code, HttpServletRequest request) {
		this.path = path;
		this.code = code;
		this.request = request;
	}
	public void delete() {
		String root = "../-/";
		switch (code) {
			case 1:
				root = "profile_pictures";
				break;
			case 2:
				root = "attachments";
				break;
			default:
				root = "none";
		}
		File file = new File(request.getSession().getServletContext().getRealPath("/").replace('\\', '/') + root + "\\" + path);
		file.delete();
	}
}