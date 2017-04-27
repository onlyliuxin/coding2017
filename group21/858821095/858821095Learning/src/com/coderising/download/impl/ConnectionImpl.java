package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	URL url;
	static final int BUFF_SIZE=1024;
	public ConnectionImpl(String _url){
		try {
			this.url=new URL(_url);
		} catch ( IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] readBytes = new byte[BUFF_SIZE];
		int len = endPos-startPos+1;
		
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Range", "bytes= "+startPos+" - "+endPos);
		InputStream is = conn.getInputStream();
		
		
		ByteArrayOutputStream  bao = new ByteArrayOutputStream ();
		int count=0;
		while((count=is.read(readBytes))!=-1){
			bao.write(readBytes, 0, count);
		}
		byte[] buff = bao.toByteArray();
		Arrays.copyOf(buff, len);
		is.close();
		bao.close();
		return buff;
	}

	@Override
	public int getContentLength() {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			return conn.getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		
	}

}
