package com.github.orajavac.coding2017.download.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.github.orajavac.coding2017.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private String url;
	
	public ConnectionImpl(String url){
		this.url=url;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URL url = new URL(this.url);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		byte[] buffer = new byte[1024];
		int size = 0;         
        while ((size = in.read(buffer)) != -1) {         
            out.write(buffer, 0, size);         
        }
        byte[] b = out.toByteArray();
        out.close();
        in.close();
		return b;
	}

	@Override
	public int getContentLength() {
		try{
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)u.openConnection();
			return conn.getContentLength();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		
		
	}

}
