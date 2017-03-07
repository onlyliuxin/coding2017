package com.coderising.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private HttpURLConnection urlCon;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
				
		return null;
	}

	@Override
	public int getContentLength() {	
		return urlCon.getContentLength();
	}

	@Override
	public void close() {
				
	}
	
	public void setUrlCon(HttpURLConnection urlCon) {
		this.urlCon = urlCon;
	}
	
}
