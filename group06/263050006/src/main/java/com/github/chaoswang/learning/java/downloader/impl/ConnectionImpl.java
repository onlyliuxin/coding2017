package com.github.chaoswang.learning.java.downloader.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import com.github.chaoswang.learning.java.downloader.api.Connection;

public class ConnectionImpl implements Connection{

	private HttpURLConnection conn;
	private InputStream is;
	public ConnectionImpl(String url){
		initConn(url);
	}
	
	private void initConn(String url){
		try{
			URL httpUrl = new URL(url);    
	        conn = (HttpURLConnection)httpUrl.openConnection();    
	        //设置超时为3秒  
	        conn.setConnectTimeout(3 * 1000);
        }catch(IOException e){
        	e.printStackTrace();
        }
		
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		downloadByBufferedStream("F:\\6977.png");
		return null;
	}
	
	private void downloadByBufferedStream(String destFilePath){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try{
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
			int index = 0;
			//read方法是一个字节一个字节读
			while((index = bis.read()) != -1){
				System.out.println(index);
				bos.write(index);
				bos.flush();
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (bis != null){
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (bos != null){
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Charset.defaultCharset());
	}

	@Override
	public int getContentLength() {
		int length = 0;
		try {
			is = conn.getInputStream();
			length = is.available();
			System.out.println("length:"+length);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return length;
	}

	@Override
	public void close() {
		try {
			if(is != null){
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
