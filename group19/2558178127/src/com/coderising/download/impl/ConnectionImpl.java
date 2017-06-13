package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

public class ConnectionImpl implements Connection{

	private HttpURLConnection conn;
	

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int len = 0;
		InputStream in = null;
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			
			conn.setRequestProperty("Range", "bytes=" + startPos + "-"
					+ (endPos));
			in = conn.getInputStream();
			while (startPos < endPos && (len = in.read(buf)) != -1) {
				bytestream.write(buf, 0, len);
			}
		} catch (Exception e) {
			new ConnectionException(e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}
		byte data[] = bytestream.toByteArray();
		System.out.println("byte size():"+bytestream.size());
		bytestream.close();
		return data;
	}

	@Override
	public int getContentLength() {
		return (int) conn.getContentLengthLong();
	}

	@Override
	public void close() {
		if(conn!=null){
			conn.disconnect();
		}
	}
	
	public InputStream getInputStream(){
		InputStream ins = null;
		try {
			ins = conn.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ins;
	}

	@Override
	public void setConnection(HttpURLConnection urlCn) {
		conn = urlCn;
	}

}
