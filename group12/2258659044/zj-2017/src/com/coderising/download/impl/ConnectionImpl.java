package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private HttpURLConnection urlCon;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		//请求服务器下载部分文件 指定文件的位置  
		urlCon.setRequestProperty("Range", "bytes="+startPos+"-"+endPos); 
		InputStream is = urlCon.getInputStream();
        byte[] buffer = new byte[endPos-startPos];
        is.read(buffer);
		return buffer;
	}

	@Override
	public int getContentLength() {	
		return urlCon.getContentLength();
	}

	@Override
	public void close() {		
		urlCon.disconnect();
	}
	
	public void setUrlCon(HttpURLConnection urlCon) {
		this.urlCon = urlCon;
	}
	
}
