package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL url;
	
	public ConnectionImpl(String _url) {
		try {
			url = new URL(_url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream in = connection.getInputStream();
		
		byte[] buffer = new byte[1024];
		int length = endPos - startPos + 1;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while(out.size() < length) {
			int len = in.read(buffer);
			if(len < 0) {
				break;
			}
			out.write(buffer, 0, len);
		}
		
		if(out.size() > length) {
			byte[] file = out.toByteArray();
			return Arrays.copyOf(file, length);
		}
		
		return out.toByteArray();
	}

	@Override
	public int getContentLength() {
		
        URLConnection con;
		try {
			con = url.openConnection();
			
			return con.getContentLength(); 
			
		} catch (IOException e) {			
			e.printStackTrace();
		}  
        
		return -1;
	}

	@Override
	public void close() {

	}

}
