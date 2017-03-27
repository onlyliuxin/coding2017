package com.vvv.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import com.vvv.download.api.Connection;

public class ConnectionImpl implements Connection{
    private static final int CONNECTION_TIMEOUT = 5*1000;
    private static final int READ_TIMEOUT = 20*1000;
    private static final int BUFF_LENGTH = 4*1024;
    
    private String requestMethod = "GET";

    private int contentLength = 0;
    private String contentType;
    private String contentFileName;
    
    @Override
	public String getFileName() {
		return contentFileName;
	}

	public void setFileName(String contentFileName) {
		this.contentFileName = contentFileName;
	}

	private String url;
    private HttpURLConnection httpConn;
    
	public ConnectionImpl(String url) {
		this.url = url;
		try {
			httpConn = createConnection(this.url);
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				setContentLength(httpConn.getContentLength());
				setContentType(httpConn.getContentType());
				setFileName(getName());
				System.out.println(",contentType "+httpConn.getContentType()+",fileName "+getFileName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ConnectionImpl(String url, boolean isFirst) {
		close();
		this.url = url;
		try {
			httpConn = createConnection(this.url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		if(startPos<0 || startPos>endPos) return null;
		try {
			httpConn.setRequestProperty("Range", "bytes=" + startPos + "-"+endPos);
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String contentlenth = httpConn.getHeaderField("Content-Length");
				if (contentlenth != null) {
					setContentLength(Integer.parseInt(contentlenth));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		InputStream inputStream = httpConn.getInputStream();

		byte[] buff = new byte[BUFF_LENGTH];
		int length = -1;
		while ((length = inputStream.read(buff)) > 0) {
			os.write(buff, 0, length);
		}
		if (inputStream != null) {
			inputStream.close();
			inputStream = null;
		}
		close();		
		return os.toByteArray();
	}

	@Override
	public int getContentLength() {		
		return this.contentLength ;
	}
   
	public void setContentLength(int len){
		this.contentLength = len;
	}
    
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	private String getName() {
		String fileName;
		String disposition = httpConn.getHeaderField("Content-Disposition");
		if (disposition != null && !"".equals(disposition)) {
			fileName = disposition.split(";")[1].split("=")[1].replaceAll("\"", "");
		} else {
			fileName = url.substring(url.lastIndexOf("/") + 1);
		}

		if (fileName != null && !"".equals(fileName)) {
			try {
				fileName = URLDecoder.decode(fileName, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			fileName = "file_" + (int) (Math.random() * 10);
		}
		return fileName;
	}
	
	@Override
	public void close() {
		if (httpConn != null) {
			httpConn.disconnect();
			httpConn = null;
		}
	}
	
	private HttpURLConnection createConnection(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setConnectTimeout(CONNECTION_TIMEOUT);
		conn.setReadTimeout(READ_TIMEOUT);
		conn.setRequestMethod(requestMethod);  
		conn.setRequestProperty("User-Agent", "vvv download");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Keep-Alive", "300");  
		return conn;
	}	

}
