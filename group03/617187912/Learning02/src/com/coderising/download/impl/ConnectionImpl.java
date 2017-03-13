package com.coderising.download.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Stream;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	public ConnectionImpl(URL url) {
		// TODO Auto-generated constructor stub
		this.url = url;
//		conn = url.openConnection();
	}
	private URL url;
	private URLConnection conn;
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] bt = new byte[endPos-startPos];
		InputStream stream =url.openStream();
		stream.read(bt, 0, endPos-startPos-1);		
		return bt;
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {

	}

}
