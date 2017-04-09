package com.coderising.download.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection  ;

public interface Connection {
	URL fileurl = null;
	HttpURLConnection   conn = null;
	InputStream inStream = null;

	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * 
	 * @param startPos
	 *            开始位置， 从0开始
	 * @param endPos
	 *            结束位置
	 * @return
	 */
	public byte[] read(int startPos, int endPos,File file) throws IOException;

	/**
	 * 得到数据内容的长度
	 * 
	 * @return
	 */
	public int getContentLength();

	/**
	 * 关闭连接
	 */
	public void close();

	public void setConn(HttpURLConnection  conn);
	public void setFileurl(URL fileurl);
	public HttpURLConnection  getConn();
	public URL getFileurl(URL fileurl) ;
	public void setinStream(InputStream inStream);
	public InputStream getinStream();
}
