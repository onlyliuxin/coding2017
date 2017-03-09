package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		URL remotUrl = null;
		HttpURLConnection httpCon = null;
		ConnectionImpl conn = new ConnectionImpl();
		try {
			remotUrl = new URL(url);    
			httpCon = (HttpURLConnection)remotUrl.openConnection();
			httpCon.setRequestMethod("GET");
			httpCon.setConnectTimeout(6000);
			httpCon.setReadTimeout(6000);			
			httpCon.setDoInput(true);
			httpCon.setRequestProperty("connection", "keep-alive");
			httpCon.setRequestProperty("accept", "*/*");			
			//设置Connection属性
			conn.setHttpConnection(httpCon);
			conn.setFileName(getFileName(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return conn;
	}
	
	/**
	 * 获取文件名称
	 * @param url
	 * @return
	 */
	private String getFileName(String url){
		
		String fileName = "";
		if(url.contains("&")&&url.contains("=")){
			fileName = url.substring(url.lastIndexOf("=")+1);
		}else{
			fileName = url.substring(url.lastIndexOf("/")+1);
		}
		return fileName;
	}
}