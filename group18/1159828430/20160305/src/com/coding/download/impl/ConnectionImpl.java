package com.coding.download.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;

public class ConnectionImpl implements Connection {
	
	private HttpURLConnection conn;
	
	private BufferedInputStream inputStream;

	public ConnectionImpl (String urlLocation) throws ConnectionException {
		
		URL url = null;
		
		try{
			if (urlLocation != null && !"".equals(urlLocation)) {
				url = new URL(urlLocation);
			}
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setAllowUserInteraction(true);
		} catch(Exception e) {
			throw new ConnectionException("创建Connection对象失败");
		}

            
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws ConnectionException {
		int readBytes = 0;
		int length = endPos - startPos + 1;
		byte[] buf = new byte[length];
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		try {
			inputStream = new BufferedInputStream(conn.getInputStream());
			while (readBytes < length) {  
				int read = inputStream.read(buf, readBytes, length - readBytes);  
                if (read == -1) {  
                    break;  
                }  
                readBytes += read;  
            }  
			
		} catch (Exception e) {
			
			throw new ConnectionException("读取失败"+e.getMessage());
		}
        
		return buf;
	}

	@Override
	public int getContentLength() {
		
		return conn.getContentLength();
	}

	
	@Override
	public void close() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("关闭失败");
			}
		}
	}

}
