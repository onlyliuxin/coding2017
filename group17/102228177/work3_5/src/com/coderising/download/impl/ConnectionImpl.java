package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL url;
	private InputStream is;
	
	public ConnectionImpl(String urlPath) {
		try {
			url = new URL(urlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		//每次都需要重新获取链接，而采用局部变量是为了防止出现线程同步
		HttpURLConnection con = getConnection();
		//设置分段下载的请求头,设置从服务器上读取的文件块
        con.setRequestProperty("Range","bytes="+startPos+"-"+endPos);
        
        if(con.getResponseCode() == 206){
        	 is = con.getInputStream();
             
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             
             byte[] b = new byte[1024];

             int len = 0;

             while((len = is.read(b)) != -1){
             	outputStream.write(b ,0 ,len);
             }
             
             outputStream.close();
             
             close();
             
             return outputStream.toByteArray();
        }
        
        return new byte[]{};
	}
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	@Override
	public int getContentLength() {
		HttpURLConnection con = getConnection();
		try {
			if (con.getResponseCode() == 200){
				//服务器返回内容的长度，本质就是文件的长度
				return con.getContentLength();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 关闭连接
	 */
	@Override
	public void close() {
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	/**
	 * 获取HttpURLConnection链接
	 * @return
	 */
	private HttpURLConnection getConnection() {
		HttpURLConnection con = null;
		try {
			//获取链接
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return con;
	}
}
