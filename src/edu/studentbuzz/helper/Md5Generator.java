package edu.studentbuzz.helper;
import java.security.MessageDigest;
public class Md5Generator {
	String source;
	String result;
	public Md5Generator(String source) {
		this.source = source;
	}
	public String getResult() {
		calculate();
		return result;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	private void calculate() {
		StringBuilder sb = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			byte[] digest = md.digest();
			sb = new StringBuilder();
			for (byte b: digest) {
				sb.append(Integer.toHexString((int)(b & 0xff)));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		result = sb.toString();
	}
}