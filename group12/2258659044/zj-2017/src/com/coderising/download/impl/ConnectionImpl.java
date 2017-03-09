package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	/*http连接*/
	private HttpURLConnection httpConnection;
	
	/*下载文件名称*/
	private String fileName;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		InputStream is = getDownloadStream(startPos,endPos);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        byte[] buffer = new byte[1024]; 
        int length = -1; 
        while ((length = is.read(buffer)) != -1) { 
            baos.write(buffer, 0, length); 
        } 
        baos.flush();
        byte[] data = baos.toByteArray(); 		
        return data;
	}

	@Override
	public InputStream getDownloadStream(int startPos, int endPos) throws IOException {
		//请求服务器下载部分文件 指定文件的位置  
		httpConnection.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		httpConnection.connect();
		return httpConnection.getInputStream();
	}	
	@Override
	public int getContentLength() {	
		return httpConnection.getContentLength();
	}

	@Override
	public void close() {		
		httpConnection.disconnect();
	}
	
	public void setHttpConnection(HttpURLConnection httpConnection) {
		this.httpConnection = httpConnection;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getDownloadName() {
		return fileName;
	}
		
}
