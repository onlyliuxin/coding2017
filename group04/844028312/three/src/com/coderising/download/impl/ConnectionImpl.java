package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	HttpURLConnection urlConnection;
	
	

	public HttpURLConnection getUrlConnection() {
		return urlConnection;
	}

	public void setUrlConnection(HttpURLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] buffer = new byte[endPos-startPos+1];
		int count=0;
		urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream ips=urlConnection.getInputStream();
		//ips.skip(startPos);
		while(count<buffer.length-1){
			int len=ips.read(buffer, count, endPos-startPos-count);
			if(len==-1){
				break;
			}
			count=count+len;
		}
		return buffer;

	}

	@Override
	public int getContentLength() {
		
		return urlConnection.getContentLength();
	}

	@Override
	public void close() {
		urlConnection.disconnect();
		
	}

}
