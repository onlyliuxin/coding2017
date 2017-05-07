package com.github.chaoswang.learning.java.downloader.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

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
	        conn.setConnectTimeout(3 * 1000);
	        conn.connect();
	        is = conn.getInputStream();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		//缓冲的意思是，告诉输出流，请你把它读满，可能读1024，也可能不足1024
		byte[] buffer = new byte[1024];    
		is.skip(startPos);
        //该线程需要下载的字节数
		int currentSectionLength = endPos - startPos + 1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
		int len = 0;    
		int hasRead = 0;
        while((len < currentSectionLength) && ((hasRead = is.read(buffer)) != -1)) {    
            bos.write(buffer, 0, hasRead);  
            len += hasRead;
        }    
        bos.close();    
        is.close();
        //这里的bytes可能比currentSectionLength稍多，须截取
        byte[] downloadedBytes = bos.toByteArray();
        byte[] needToDownload = Arrays.copyOf(downloadedBytes, currentSectionLength);
        return needToDownload;    
	}

	@Override
	public int getContentLength() {
		int length = conn.getContentLength();
		System.out.println("length:" + length);
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
