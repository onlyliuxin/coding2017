package com.m0312.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.m0312.download.api.Connection;

public class ConnectionImpl implements Connection{
	URLConnection urlCon;
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] buffer=new byte[endPos-startPos];
		InputStream is=urlCon.getInputStream();
		is.skip(startPos);
		is.read(buffer, 0, endPos-startPos);
		is.close();
		return buffer;
	}

	@Override
	public int getContentLength() {
		return urlCon.getContentLength();
	}

	@Override
	public void close() {
		if(urlCon!=null){
			//???
		}
	}
	@Override
	public URLConnection getUrlCon() {
		return urlCon;
	}
	@Override
	public void setUrlCon(URLConnection urlCon) {
		this.urlCon = urlCon;
	}

}
