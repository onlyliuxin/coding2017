package com.easy.util.download;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;


public class TestApp {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://om9xacvdp.bkt.clouddn.com/blog-self-logo.jpg");
		InputStream is=url.openStream();
		
		DataInputStream dis=new DataInputStream(is);
		File file =new File("D://1.jpg");
		
		FileOutputStream fos=new FileOutputStream(file);
		
		byte[] buffer = new byte[1024];
		int length;
		while((length=dis.read(buffer))>0){
			fos.write(buffer, 0, length);
		}
		
		fos.close();
		dis.close();
		
		
		
	}
}
