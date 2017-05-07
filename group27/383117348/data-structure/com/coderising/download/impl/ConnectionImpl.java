package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL url;
	private HttpURLConnection connection;
	
	public ConnectionImpl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private HttpURLConnection initConnection(){
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection)url.openConnection();
			con.setConnectTimeout(2000);
			con.setRequestMethod("GET");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		connection = initConnection();
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos); //nullPointException
		InputStream input = connection.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int i=0;
		byte[] bt = new byte[1024];
		while((i=input.read(bt))!=-1){
			bos.write(bt,0,i);
		}
		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
		HttpURLConnection con = initConnection();
		try {
			if (con.getResponseCode() == 200){
				//服务器返回内容的长度，本质就是文件的长度
				return con.getContentLength();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void close() {
		this.connection=null;
	}

}
