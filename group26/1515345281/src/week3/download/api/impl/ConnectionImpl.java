package week3.download.api.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import week3.download.api.Connection;
import week3.download.api.ConnectionException;


public class ConnectionImpl implements Connection{

	URL url;
	
	static final int BUFFER_SIZE=1024*10;
	
	
	public ConnectionImpl(String _url) throws ConnectionException{
		try {
			this.url=new URL(_url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
	}
	
	@Override
	public void close() {
	
	}

	@Override
	public int getContentLength() {
	    URLConnection conn;
	    try {
			conn=url.openConnection();
			return conn.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  -1;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		HttpURLConnection httpConn=(HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Range","bytes="+startPos+"-"+endPos);//设置请求一般属性
		
		InputStream in=httpConn.getInputStream();
		
		BufferedInputStream bis=new BufferedInputStream(in,BUFFER_SIZE);
		byte[] buffer=new byte[BUFFER_SIZE];
		
		//缓冲区会随着数据的不断写入而自动增长
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		
		int totalLen=endPos-startPos+1;
		
		while(baos.size() < totalLen){
			int len=bis.read(buffer);
			if(len==-1){
				break;
			}
			baos.write(buffer, 0, len);
		}
		
		if(baos.size() > totalLen){
			byte[] data=baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}
		
		return baos.toByteArray();
	}

}
