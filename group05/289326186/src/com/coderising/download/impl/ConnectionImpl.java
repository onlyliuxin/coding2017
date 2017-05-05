package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	URLConnection conn;
	
	public ConnectionImpl(URLConnection conn){
		this.conn = conn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		conn.connect();
		is = conn.getInputStream();
		bos = new ByteArrayOutputStream();
		int len = endPos - startPos;
		byte[] b = new byte[len];
		while((len = is.read(b)) != -1) {
            bos.write(b, 0, len);
        }
		bos.flush();
		is.close();
		bos.close();
		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {
		
	}

}