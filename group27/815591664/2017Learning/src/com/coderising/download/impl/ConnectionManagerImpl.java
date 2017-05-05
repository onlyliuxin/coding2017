package com.coderising.download.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		try {
			URL urlObj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)urlObj.openConnection();
			//超时
			conn.setConnectTimeout(3*1000);
	        //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        conn.setRequestMethod("GET");
	        return new ConnectionImpl(conn);
		} catch (Exception e) {
			throw new ConnectionException();
		}
		
	}
	
	public static void main(String[] args) throws ConnectionException, IOException {
		ConnectionManager cm = new ConnectionManagerImpl();
		Connection conn = cm.open("http://localhost:8080/ForDownload/test.jpg");
		
		System.out.println(conn.getContentLength());
		
		byte[] content = conn.read(0, conn.getContentLength()-1);
		OutputStream os = new FileOutputStream("d:test.jpg");
		os.write(content);
		os.flush();
		
		conn.close();
		os.close();
	}

}
