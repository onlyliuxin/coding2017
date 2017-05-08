package com.github.wdn.coding2017.coderising.download.impl;

import com.github.wdn.coding2017.coderising.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;


public class ConnectionImpl implements Connection {
	HttpURLConnection connection;
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		if (connection == null) {
			throw new IllegalArgumentException("connection is null");
		}
		int code = connection.getResponseCode();
		ByteArrayOutputStream baos=null;
		InputStream is=null;
		if(code==200){
			//5读取服务器资源的流
			is= connection.getInputStream();
			//准备内存输出流 临时存储的
			baos = new ByteArrayOutputStream();
			byte buff[] = new byte[1024];
			int len=0;
			int sum = 0;
			while((len=is.read(buff))!=-1){
				baos.write(buff,0,len);
				baos.flush();
				sum+=len;
				if(sum>=endPos){
					break;
				}
			}
		}
		byte[] readResult = baos.toByteArray();
		return Arrays.copyOfRange(readResult,startPos,endPos);
	}

	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}

	@Override
	public void close() {

	}
	public void setHttpURLConnection(HttpURLConnection httpURLConnection){
		this.connection = httpURLConnection;
	}
}
