package com.coderising.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionManagerImpl;

public class TestDownLoad {
	
	
	public static void main(String[] args) {
		
		String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489315701982&di=bf402ad7afb5c77637ed1be7574a9151&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3D0897145a544e9258a63486e6ac83d1d1%2Fb912c8fcc3cec3fdfdb75b4bd488d43f87942706.jpg";
		
		ConnectionManager cm = new ConnectionManagerImpl();
		
		Connection conn=null;
		try {
			 conn=cm.open(url);
			 
			 
			 int length=conn.getContentLength();
			 
			 byte[] buff=conn.read(0, length);
			 
			 FileOutputStream os=new FileOutputStream("E:/downloadTest/2.jpg");
			 
			 os.write(buff, 0, length);
			 
			 os.close();

			 
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try {
			URL url2=new URL(url);
			
			URLConnection conn2=url2.openConnection();
			
//			conn2.setAllowUserInteraction(true);
			
			int len=conn2.getContentLength();
			
			int test;
			
			InputStream in=url2.openStream();
			
			byte[] buffer=new byte[1024];
			
			FileOutputStream out=new FileOutputStream("E:/downloadTest/3.jpg");
			
			while(((test=in.read(buffer))!=-1)){
				System.out.println(test);
				out.write(buffer,0,test);
			}
			
			out.close();
			in.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 System.out.println("测试结束");
	}

}
