package com.coding.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coding.basic.LinkedList;
import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;
import com.coding.download.impl.ConnectionManagerImpl;





public class Test {
	
	public static void main(String[] args) throws IOException, ConnectionException {
		/*URLConnection con = new URL("https://img6.bdstatic.com/img/image/smallpic/22.jpg").openConnection();
		InputStream in = con.getInputStream();
		System.out.println("---------------");
		
		con.setRequestProperty("Range", "bytes=" + 1 + "-" + 2);*/
		/*URLConnection con = new URL("https://img6.bdstatic.com/img/image/smallpic/22.jpg").openConnection();
		con.getContentLength();
		con.connect();
		//con.setRequestProperty("Range", "bytes=" + 0 + "-" + 27573);
		con.addRequestProperty("Range", "bytes=" + 0 + "-" + 27573);
		System.out.println(con.getContentLength());
		InputStream in = con.getInputStream();
		int length = con.getContentLength();
		File file = new File("c:/test/test.jpg");
		//RandomAccessFile out = new RandomAccessFile(file, "rw");
		FileOutputStream out = new FileOutputStream(file);
		byte[] buff = new byte[length];
		int len = 0;
		while((len=in.read(buff))!=-1){
			out.write(buff, 0, len);
		}
		out.close();*/
		/**/Connection con = new ConnectionManagerImpl().open("https://img6.bdstatic.com/img/image/smallpic/22.jpg");
		byte[] buff = con.read(0, 27573);
		File file = new File("c:/test/test.jpg");
		FileOutputStream out = new FileOutputStream(file);
		out.write(buff);
		out.close();
	}

}
