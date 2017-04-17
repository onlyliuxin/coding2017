package com.coderising.download.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

public class ConnectionImpl implements Connection{
	URL url;
	static final int BUFFER_SIZE = 1024;
	
	ConnectionImpl(String _url) throws ConnectionException{
		try {
			url = new URL(_url);
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		//��ʼ
		System.out.println("��ʼ");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// ���ö�ȡ��λ��
		httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		// ��URL���ӻ��������
		InputStream is = httpConn.getInputStream();
		
		//is.skip(startPos);
		byte[] buff = new byte[BUFFER_SIZE];
		int totalLen = endPos - startPos + 1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while(baos.size() < totalLen){
			int len = is.read(buff);
			if(len<0){
				break;
			}
			baos.write(buff,0,len);
		}
		
		if(baos.size() > totalLen){
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		URLConnection con;
		try {
			con = url.openConnection();
			return con.getContentLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		
	}

}
