import static util.Print.println;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import sun.net.www.protocol.http.HttpURLConnection;

public class test {

	public static String url = "http://sw.bos.baidu.com/sw-search-sp/software/89179b0b248b1/QQ_8.9.20026.0_setup.exe";
	public static String url2 = "http://image.beekka.com/blog/201304/bg2013042401.jpg";
	public static String downloadLocation = "C:\\";
	public static String tempName = "";
	public static String fileName = "";

	LinkedList a;

	private static void createTempFile1(String from) {
		long length = 0;
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(from);
			conn = (HttpURLConnection) url.openConnection();
			String file = conn.getURL().getFile();
			fileName = file.substring(file.lastIndexOf('/') + 1);
			tempName = fileName.substring(0, fileName.lastIndexOf('.') + 1) + "lyj";
			length = conn.getContentLength();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		tempName = downloadLocation + tempName;
		fileName = downloadLocation + fileName;
		bufferFile(tempName, length);
	}

	public static void bufferFile(String name, long len) {

		FileOutputStream temp = null;
		try {
			temp = new FileOutputStream(name);
			long length = len;
			byte[] buffer = new byte[1024];
			long times = length / 1024;
			int left = (int) (length % 1024);
			for (int i = 0; i < times; i++) {
				temp.write(buffer);
			}
			temp.write(buffer, 0, left);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				temp.flush();
				temp.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void download(String src) {
		createTempFile1(src);

		URL url = null;
		HttpURLConnection conn = null;
		FileOutputStream out = null;
		InputStream in = null;
		try {
			url = new URL(src);
			conn = (HttpURLConnection) url.openConnection();
			in = conn.getInputStream();
			out = new FileOutputStream(tempName);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			conn.disconnect();
			println(fileName);
			println(rename(tempName));
			println("Download Complete!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

	}

	public static boolean rename(String temp) {
		File file = new File(temp);
		File f1 = new File(fileName);
		if (file.exists()) {
			file.renameTo(f1);
			file = f1;
			System.out.println("文件重命名为：" + f1.getName());
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {

		download(url2);

	}

}
