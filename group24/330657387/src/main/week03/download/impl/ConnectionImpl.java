package main.week03.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import main.week03.download.FileDownloader;
import main.week03.download.api.Connection;
import main.week03.download.api.ConnectionException;

//包级可见，是保护措施
class ConnectionImpl implements Connection {

	URL url;
	static final int BUFFER_SIZE = 1024;

	public ConnectionImpl(String _url) throws ConnectionException {
		try {
			//传入的字符串必须是url格式
			url = new URL(_url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int totalLen = endPos - startPos + 1;
		
		//是URLConnection的子类，负责http协议的链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStream inputStream = conn.getInputStream();

		//客户端可以在请求里放置参数，设置接收数据区间
		//代替了is.skip(),但是is.skip里有read，所以是边读边移动下标的，和本程序意图相违背。
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

		byte[] buffer = new byte[BUFFER_SIZE];

		//输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		while (baos.size() < totalLen) {

			//一次读取1024字节
			int len = inputStream.read(buffer);
			if (len < 0) {
				break;
			}
			baos.write(buffer, 0, len);
		}

		//防止这个线程过度读取
		if (baos.size() > totalLen) {
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}

		return baos.toByteArray();
	}

	@Override
	public int getContentLength() throws ConnectionException {
		int res = -1;
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			res = conn.getContentLength();
			conn.disconnect();
			return res;
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}

	@Override
	public void close() {
		
	}

}
