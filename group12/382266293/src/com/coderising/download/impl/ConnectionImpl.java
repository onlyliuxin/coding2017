package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;


public class ConnectionImpl implements Connection {

	private static int buffer_size = 1024;
	private ConnectionManager cm;
	private HttpURLConnection httpConn;
	private URL url;
	private boolean finished = false;

	public ConnectionImpl(ConnectionManager cm, String _url) {
		this.cm = cm;
		try {
			url = new URL(_url);
			httpConn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		httpConn.disconnect();
	}

	@Override
	public int getContentLength() {
		int len = httpConn.getContentLength();

		return len;

	}

	@Override
	public String getFileName() {
		String fileName = httpConn.getURL().getFile();
		fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
		return fileName;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
			httpConn.setRequestProperty("connection", "keep-alive");
			httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)");
			httpConn.setRequestProperty("Host", "172.26.203.62");
			httpConn.setRequestProperty("Referer", "http://172.26.203.62/smg_xtbd/bianpai/playListAction.do?method=egpList");
			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");

			httpConn.setRequestProperty("Cookie", "theme=THEME1; userName=liyijun; modules=m_work|m_message; JSESSIONID=0000GVQZgIsXFQyoCCmhnH1weFW:15ldljac7");
			in = httpConn.getInputStream();
			out = new ByteArrayOutputStream();
			in = httpConn.getInputStream();
			// in.skip(startPos);

			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			int totalLen = endPos - startPos + 1;

			if (out.size() > totalLen) {
				byte[] data = out.toByteArray();
				return data;
			}

			return out.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setFinished() {
		finished = true;
	}

}
