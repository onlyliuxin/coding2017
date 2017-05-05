package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URL url;
	
	private int length;
	
	private InputStream is;
	
	public ConnectionImpl(String url){
		try {
			this.url = new URL(url);
			this.length = getConnection(this.url).getContentLength();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error opening connection.",e);
		}
	}

	private HttpURLConnection getConnection(URL url) throws MalformedURLException, IOException, ProtocolException {
		HttpURLConnection conn = (HttpURLConnection)this.url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(30*1000);
		return conn;
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] buffer = new byte[1024];
		int tempLen = 0;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HttpURLConnection conn = getConnection(url);
		conn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		this.is = conn.getInputStream();
		
		while((tempLen = this.is.read(buffer))!=-1){
			baos.write(buffer, 0, tempLen);
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return this.length;
	}

	@Override
	public void close() {
		if(this.is!=null)
		{
			try {
				this.is.close();
				this.url = null;
			} catch (IOException e) {
				// Safe to ignore
				e.printStackTrace();
			}
		
		}

	}

}
