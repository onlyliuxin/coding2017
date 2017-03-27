package org.wsc.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.wsc.coderising.download.api.Connection;
import org.wsc.coderising.download.api.ConnectionException;

/**
 *
 * 连接类
 * 
 * @author Administrator
 * @date 2017年3月6日下午7:10:13
 * @version v1.0
 *
 */
public class ConnectionImpl implements Connection {
	
	/** 默认缓冲大小 */
	private final static int DEFAULT_SIZE = 1024;

	private HttpURLConnection conn;

	private InputStream is;
	
	private ByteArrayOutputStream bos;

	@SuppressWarnings("static-access")
	@Override
	public byte[] read(int startPos, int endPos) throws IOException, ConnectionException {
		// 设置读取范围
		conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		conn.setFollowRedirects(true);//自动执行重定向
		conn.setConnectTimeout(30000);//等待响应时间
		checkStatus();
		byte[] buf = new byte[Math.min(getContentLength(), DEFAULT_SIZE)];
		is = new BufferedInputStream(conn.getInputStream());
		bos = new ByteArrayOutputStream();
		int lenth;//实际读取长度
		//读取
		while ((lenth = is.read(buf))!= -1) 
			bos.write(buf, 0, lenth);
		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {
		if (bos != null)
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (is != null)
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(conn != null)
			conn.disconnect();
	}
	
	@Override
	public String getFileName() {
		String fileName = null;
		String field = conn.getHeaderField("Content-Disposition");
		if(field == null ){
			String urlStr = conn.getURL().toString();
			fileName = urlStr.substring(urlStr.lastIndexOf("/")+1);
		}else{
			fileName=field.substring(field.indexOf("filename")+10, field.length()-1);
		}
		System.out.println(fileName);
		return fileName;
	}
	
	/**
	 * 检查连接状态
	 * @throws ConnectionException
	 */
	private void checkStatus() throws ConnectionException {
		try {
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_PARTIAL) {
				throw new ConnectionException("server response code: " + responseCode);
			}
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}

	public HttpURLConnection getConn() {
		return conn;
	}

	public void setConn(HttpURLConnection conn) {
		this.conn = conn;
	}

	public ConnectionImpl(HttpURLConnection conn) {
		super();
		this.conn = conn;
	}
	
}
