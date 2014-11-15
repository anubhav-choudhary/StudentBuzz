package edu.studentbuzz.helper;
import java.sql.ResultSet;
public class IDgenerator {
	String bean_name;
	long id;
	public IDgenerator(String bean_name) {
		this.bean_name = bean_name;
	}
	public String getBean_name() {
		return bean_name;
	}
	public void setBean_name(String bean_name) {
		this.bean_name = bean_name;
	}
	public long getId() {
		generate();
		return id;
	}
	private void generate() {
		String primary_key;
		if (bean_name.equals("attachment")) primary_key = "Attachment_ID";
		else if (bean_name.equals("comment")) primary_key = "Comment_ID";
		else if (bean_name.equals("department")) primary_key = "Dept_ID";
		else if (bean_name.equals("post")) primary_key = "Post_ID";
		else if (bean_name.equals("report")) primary_key = "reportid";
		else if (bean_name.equals("sub_category")) primary_key = "Sub_ID";
		else primary_key = "";
		if (!primary_key.equals("")) {
			String query = "Select " + primary_key + " from " + bean_name + " order by " + primary_key + " DESC";
			dbConnection dbcon = new dbConnection();
			dbcon.open();
			ResultSet rs = dbcon.executeQuery(query);
			try {
				if (rs.next()) {
					id = rs.getLong(1) + 1;
				} else {
					id = 1;
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			dbcon.close();
		}
	}
}