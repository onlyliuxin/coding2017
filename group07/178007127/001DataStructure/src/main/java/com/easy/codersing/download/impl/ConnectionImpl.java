package com.easy.codersing.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.easy.codersing.download.api.Connection;

public class ConnectionImpl implements Connection {

	private URL url;
	
	
	public ConnectionImpl(URL url) {
		this.url = url;
	}


	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Range", "bytes=" + startPos + "-"+ endPos);
		
		InputStream is = conn.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = is.read(buffer)) != 1) {
			bos.write(buffer, 0, len);
		}
		
		bos.close();
		dis.close();
		is.close();
		return bos.toByteArray();
	}
	@Override
	public int getContentLength() {
		int len=0;
		
		try {
			HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();;
			len = urlConnection.getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return len;
	}

	@Override
	public void close() {
	
	}

}
