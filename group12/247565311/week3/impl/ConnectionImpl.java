package week3.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import week3.api.Connection;

public class ConnectionImpl   implements Connection{
	HttpURLConnection conn = null;
	// 这个类的主要作用是执行下载，获取字节数组
	// 这个类负责打开、关闭下载链接
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		if(conn == null || startPos>=endPos) return null;
		byte[]res = null;
		conn.setRequestProperty("Range","bytes="+startPos+"-"+endPos);
		int responcode = conn.getResponseCode();
		if(200 < responcode && responcode < 300){
			InputStream input = conn.getInputStream();
			res = new byte[endPos-startPos];
			input.read(res);
			input.close();
		}
		conn.disconnect();
		return res;
	}

	@Override
	public int getContentLength() {
		if(conn == null) return 0;
		return conn.getContentLength();
	}

	@Override
	public void close() {
		if(conn == null) return;
		conn.disconnect();
	}
	public void setConn(HttpURLConnection urlconn) {
		conn = urlconn;
	}
}
