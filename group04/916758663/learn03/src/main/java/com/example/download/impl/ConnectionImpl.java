package com.example.download.impl;

import com.example.download.api.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ConnectionImpl implements Connection {

	private URL url;

	ConnectionImpl(String urlStr){
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
    URLConnection urlConnection = url.openConnection();
    urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-"
        + endPos);
    InputStream inputStream = urlConnection.getInputStream();
    int len = endPos + 1 - startPos;
    int bytesRead = 0;
		byte[] buffer = new byte[len];
    while (bytesRead < len) {
      int result = inputStream.read(buffer, bytesRead, len - bytesRead);
      if (result == -1){
        break;
      }
      bytesRead += result;
    }
    inputStream.close();
		return buffer;
	}

	@Override
	public int getContentLength() {
		try {
      URLConnection urlConnection = url.openConnection();
      return urlConnection.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
    return -1;
	}

	@Override
	public void close() {
	}

}
