package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

public class ConnectionImpl implements Connection {
	
	private URL urlObj;

	private URLConnection connection;
	
	private final static int MAX_BUFF_LENGTH=1024;

	
	public ConnectionImpl(URL urlObj) throws IOException{
		this.urlObj=urlObj;
//		this.connection=urlObj.openConnection();
	}
	
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		InputStream inputstream = null;
		ByteArrayOutputStream os=new ByteArrayOutputStream();

		try {
			
//			connection.setAllowUserInteraction(true);
			
			connection=urlObj.openConnection();
			
			connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
			
			inputstream = connection.getInputStream();
			
			
			byte[] buff = new byte[MAX_BUFF_LENGTH];
			
			int len;
			inputstream.skip(startPos);
			// 一次不能刚好读1024个字符
			while ((len = inputstream.read(buff)) != -1) {
				os.write(buff, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputstream.close();
			os.close();
		}
		return os.toByteArray();
	}

	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}

	@Override
	public void close() {

	}
	
	public void setUrlObj(URL urlObj) {
		this.urlObj = urlObj;
	}
	
	public void setConnection(URLConnection connection) {
		this.connection = connection;
	}

}
