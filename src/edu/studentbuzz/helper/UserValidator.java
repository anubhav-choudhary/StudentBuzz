package edu.studentbuzz.helper;
import javax.servlet.http.HttpSession;
public class UserValidator {
	String usertype;
	HttpSession session;
	boolean valid;
	public UserValidator(String usertype, HttpSession session) {
		this.usertype = usertype;
		this.session = session;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public boolean isValid() {
		usercheck();
		return valid;
	}
	private void usercheck() {
		valid = (session.getAttribute(usertype) != null);
	}
	public String genPath() {
		String path;
		if (session.getAttribute("admin") != null) {
			path = "admin/index.jsp";
		} else if (session.getAttribute("faculty") != null) {
			path = "faculty/index.jsp";
		} else if (session.getAttribute("student") != null) {
			path = "student/index.jsp";
		} else {
			path = "login.jsp?error=6";
		}
		return path;
	}
}