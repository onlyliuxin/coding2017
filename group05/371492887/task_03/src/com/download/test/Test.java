package com.download.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {
	public static void main(String[] args) throws IOException {
		
		InputStream is = null;
		
		FileInputStream isText = null;
		
		OutputStream os = null;
		
		
		
		try {
			System.out.println("开始下载图片");
			String urlStr="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489315701982&di=bf402ad7afb5c77637ed1be7574a9151&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3D0897145a544e9258a63486e6ac83d1d1%2Fb912c8fcc3cec3fdfdb75b4bd488d43f87942706.jpg";
			URL url=new URL(urlStr);
			
			URLConnection con=url.openConnection();
			
			 is=con.getInputStream();
			 int length=con.getContentLength();
			
			 isText=new FileInputStream(new File("D:/db.sql"));
			 
			byte[] bs =new byte[length];
			
			
			int len;
			
			os=new FileOutputStream("E:/downloadTest/test.jpg");
			int count=0;
			while((len=is.read(bs))!=-1){
				os.write(bs,0,len);
				count++;
			}
			System.out.println(count);
			System.out.println("图片下载结束");
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			os.close();
			
			is.close();
		}
		
	}

}
