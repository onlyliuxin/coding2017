package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	HttpURLConnection urlConnection = null;

	public ConnectionImpl(HttpURLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		urlConnection.setRequestMethod("GET");
		urlConnection.setRequestProperty("Range","bytes=" + startPos + "-" + endPos);
		urlConnection.setConnectTimeout(5000);
		ByteArrayOutputStream buffer_array = new ByteArrayOutputStream(endPos - startPos);
		if (urlConnection.getResponseCode() == 206) {
			InputStream inputStream = urlConnection.getInputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) != -1) {
				buffer_array.write(buffer,0,len);
			}
			System.out.printf("input stream ,startp :%s , endp:%s , result length is :%d \n",startPos,endPos,buffer_array.size());
			inputStream.close();
			buffer_array.close();
		}
		urlConnection.disconnect();
		return buffer_array.toByteArray();
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
