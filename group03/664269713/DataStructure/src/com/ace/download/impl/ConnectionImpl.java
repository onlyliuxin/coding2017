package com.ace.download.impl;

import com.ace.download.api.Connection;
import com.ace.download.api.ConnectionManager;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;


public class ConnectionImpl implements Connection {
	private URL urlObj;
	private InputStream inputStream;
	private ByteArrayOutputStream byteArrayOutputStream;

	public ConnectionImpl(URL urlObj){
		this.urlObj = urlObj;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
        URLConnection urlConnection = urlObj.openConnection();
        urlConnection.setAllowUserInteraction(true);
        urlConnection.setRequestProperty("Range","bytes=" + startPos + "-" + endPos);
		inputStream = urlConnection.getInputStream();
        byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] bytes = new byte[1024];
        int length = 0;
        while((length = inputStream.read(bytes)) != -1){
			byteArrayOutputStream.write(bytes, 0, length);
        }
        inputStream.close();
        byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public int getContentLength() throws IOException {
        URLConnection urlConnection = urlObj.openConnection();
		return urlConnection.getContentLength();
	}

	@Override
	public void close() {

	}

}
