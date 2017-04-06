package download.impl;

import java.io.*;
import java.net.HttpURLConnection;

import download.api.ConnectionException;

class HttpConnection extends BaseConnection {

	HttpConnection(String url, int startPos, int endPos) throws ConnectionException {
		super(url, startPos, endPos);
	}

	@Override
	protected void init(int startPos, int endPos) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) super.connection;
		connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		openInputStream();
	}
}
