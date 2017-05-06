package org.coding.three.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.coding.three.download.api.Connection;
import org.coding.three.download.api.ConnectionException;


public class ConnectionImpl implements Connection{
	private static final int BUFFER_SIZE = 1024;
	private URL url;
	public ConnectionImpl (String url) throws ConnectionException {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		// ***
		urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream is = urlConnection.getInputStream();
		int size = endPos - startPos + 1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int len = 0;
		while(baos.size() < size) {
			if((len = is.read(buffer)) < 0) {
				break;
			}
			baos.write(buffer, 0, len);
		}
		if(baos.size() > size) {
			return Arrays.copyOf(baos.toByteArray(), size);
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		try {
			return url.openConnection().getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		try {
			url.openStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
