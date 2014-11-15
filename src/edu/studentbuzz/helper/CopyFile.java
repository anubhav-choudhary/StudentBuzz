package edu.studentbuzz.helper;
import java.io.*;
public class CopyFile {
	public void copyFile(InputStream in , File dest) throws IOException {
		OutputStream out = new FileOutputStream(dest.getPath());
		byte[] buf = new byte[1024];
		int len;
		while ((len = in .read(buf)) > 0) {
			out.write(buf, 0, len);
		} in .close();
		out.close();
	}
}
