package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;


//非public class,仅包内可见,依靠ConnectionMangerImpl实现
class ConnectionImpl implements Connection {
	
	URL url;
	static final int BUFFER_SIZE = 1024;
	
	ConnectionImpl(String _url) throws ConnectionException {
		try {
			this.url = new URL(_url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
	}
	
	/*
	 * 分段读取数据
	 * @see com.coderising.download.api.Connection#read(int, int)
	 */
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		/*
		 * HttpURLConnection是基于HTTP协议的，HttpURLConnection的对象不能直接构造，需要通过url.openConnection()来获得HttpURLConnection对象
		 * 示例如下：
		 * String slurl = "http://....";
		 * URL url = new URL(slurl);
		 * HttpURLConnection httpCoon =  (HttpURLConnection) url.openConnection(); 
		 * 调用HttpURLConnection连接对象的getInputStream()函数,将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端
		 */
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); 
		httpConn.setRequestProperty("Range","bytes=" + startPos + "-" + endPos); //只发一个特定的片段，比is.skip有效
		InputStream is = httpConn.getInputStream();// 注意，实际发送请求的代码段就在这里 -------------------------------输入流
		
		//is.skip(startPos);//跳过startPos之前的内容
		
		byte[] buff = new byte[BUFFER_SIZE];
		int totalLen = endPos - startPos + 1; //注意+1
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节数组流， 可以捕获内存缓冲区的数据，转换成字节数组。----输出流
		
		while(baos.size() < totalLen){
			
			int len = is.read(buff);//从输入流中读取数据到buff数组,同时返回读取长度，每次读取量小于等于1024
			if (len < 0){
				break;
			}
			baos.write(buff, 0, len);//buff数组中的数据写入输出流
		}
		
		if(baos.size() > totalLen){
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}
		
		return baos.toByteArray();
	}
	
	/*
	 * 获取资源长度
	 * @see com.coderising.download.api.Connection#getContentLength()
	 */
	@Override
	public int getContentLength() {
		URLConnection con;
		try {
			con = url.openConnection();
			
			return con.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void close() {

		
	}
	
}
