package com.coding.basic.homework_03.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coding.basic.homework_03.download.api.Connection;

public class ConnectionImpl implements Connection{
//	public RandomAccessFile raf = null;
	public HttpURLConnection httpUrl = null;
	public URL Url = null;
//	public BufferedInputStream bis = null;
//	FileInputStream fis = null;
	InputStream is = null;
	public ConnectionImpl(String url) throws IOException{
		
		Url = new URL(url);
		httpUrl = (HttpURLConnection) Url.openConnection();
		httpUrl.connect();
		is = httpUrl.getInputStream();
		
//		fis = (FileInputStream) httpUrl.getInputStream();
//		bis = new BufferedInputStream(httpUrl.getInputStream());
//		raf = new RandomAccessFile(url, "r");
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int length = endPos - startPos + 1;
		byte[] result = new byte[length];
		int num = 0;
		if(startPos != 0){
			is.skip(startPos);
		}	
		num = (int)is.read(result);
//		System.out.println("length : " + length);
//		System.out.println("num : " + num);
		
//		System.out.println(startPos +"....读到了...."+endPos);
//		
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println("num:" + num);
//		fis.skip(startPos);
//		fis.read(result, startPos, length);
//		bis.read(result, startPos, length);
		
//		raf.seek(startPos);
//		raf.read(result);
		return result;
	}

	@Override
	public int getContentLength() throws IOException {
		int num = 0;
		return httpUrl.getContentLength();
//		return (int) raf.length();
	}

	@Override
	public void close() throws IOException {
//		if(raf != null){
//			raf.close();
//		}
//		if(bis != null){
//			bis.close();
//		}
		if(is != null){
			is.close();
		}
		
	}

}