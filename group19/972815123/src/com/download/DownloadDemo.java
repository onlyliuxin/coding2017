package com.coderising.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

public class DownloadDemo {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://bpic.588ku.com/back_pic/02/66/65/68578b3fca8af67.jpg");
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		
		System.out.println("content length:" + conn.getContentLength() /1024 + " * 1024");
		System.out.println("stream avilable:" + is.available()/1024  + " * 1024");
		int length = conn.getContentLength();
		
		byte[] buffer = new byte[1024];
		int hasRead = 0;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while((hasRead = is.read(buffer)) != -1){
			out.write(buffer, 0, hasRead);
		}
		byte[] result = out.toByteArray();

		RandomAccessFile file = new RandomAccessFile("demo.jpg", "rw");
		file.write(result);
		file.close();
		is.close();
	}
}
