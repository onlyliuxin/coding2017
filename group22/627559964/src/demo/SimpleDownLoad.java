package demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SimpleDownLoad {
	
	public static void main(String[] args) {
		String downUrl = "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/super/crop=378,138,1512,942/sign=9cca06797b3e6709aa4f1fbf06f4a805/f9198618367adab471a1e11d8fd4b31c8701e47f.jpg";
		String dirUrl = "C:\\Users\\Administrator\\Desktop\\1.jpg";
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			URL url = new URL(downUrl);
			URLConnection connection = url.openConnection();
			in = connection.getInputStream();
			
			File file = new File(dirUrl);
			out = new FileOutputStream(file);
			
			byte[] files = new byte[1024];
			int length = 0;
			while ((length = in.read(files)) > -1) {
				out.write(files, 0, length);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
