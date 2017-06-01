package com.coderising.download.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

//无public 这是一个包级的实现，安全
class ConnectionImpl implements Connection{
	/*
	 * Uniform Resource Locator 统一资源定位符
	 * URL包含：协议protocol、主机host、端口号port、文件路径path、请求参数query、定位位置fragment
	 * 若连接HTTP协议的URL，openConnection()方法返回HttpURLConnection对象
	 * 若连接的为一个JAR文件，openConnection()方法返回JarURLConnection 对象
	 * ……
	 */
	private URL url; 
	private final static int BUFFER_SIZE = 1024;
	
	public ConnectionImpl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分段读取
	 */
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		
		InputStream is = conn.getInputStream();
//		is.skip(startPos);//skip的底层实现也是在read
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//（内存中的）字节数组输出流：在内存中创建一个字节数组缓冲区。无参时默认设置为32字节
		byte[] buffer = new byte[BUFFER_SIZE];
		int totalLength = endPos - startPos + 1;
//		int length = 0;
		
		while(baos.size() < totalLength){
			int len = is.read(buffer);
			if(len < 0){
				break;
			}
			baos.write(buffer, 0, len);
		}
		
		if(baos.size() > totalLength){//取出来太多，多了的不要
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLength);
		}
//		while((length=is.read(buffer)) != -1){
//			bout.write(buffer, 0, length);//读入缓存
//		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		try {
			URLConnection conn = url.openConnection();			
			return conn.getContentLength();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void close() {
		
		
	}

}
