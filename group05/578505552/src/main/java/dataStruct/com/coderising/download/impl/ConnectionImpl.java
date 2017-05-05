package dataStruct.com.coderising.download.impl;

import dataStruct.com.coderising.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ConnectionImpl implements Connection{

	private HttpURLConnection conn;

	public ConnectionImpl(HttpURLConnection urlConnection) {
		this.conn = urlConnection;
	}

	public byte[] read(int startPos, int endPos) throws IOException {
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		if (conn.getResponseCode() == 206){
			InputStream inputStream = conn.getInputStream();
			byte[] temp = new byte[endPos - startPos + 1];
			while ((inputStream.read(temp)) != -1){

			}
			return temp;
		}
		return new byte[0];
	}

	public int getContentLength() {
		try {
			if (conn.getResponseCode() == 200){
				return conn.getContentLength();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return 0;
	}

	public void close() {
		if (conn != null){
			conn.disconnect();
			conn = null;
		}
	}
}
