package week3_fileDownloader.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import week3_fileDownloader.api.Connection;

public class ConnectionImpl   implements Connection{
	URL url = null;
	public ConnectionImpl(String str){
		try {
			url = new URL(str);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	// 这个类的主要作用是执行下载，获取字节数组
	// 这个类负责打开、关闭下载链接
	@Override
	public byte[] read(int startPos, int endPos)  {
		HttpURLConnection conn = null;
		byte[]res = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Range","bytes="+startPos+"-"+endPos);
			int responcode = conn.getResponseCode();
			if(200 < responcode && responcode < 300){
				InputStream input = conn.getInputStream();
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				res = new byte[2048];
				while(output.size()<endPos - startPos){
					int len = input.read(res);
					if(len>0)output.write(res,0,len);
					else break;
				}
				return Arrays.copyOf(output.toByteArray(), endPos-startPos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(conn!=null) conn.disconnect();
		return res;
	}

	@Override
	public int getContentLength() {
		try{
			URLConnection con = url.openConnection();
			return con.getContentLength();
		}catch(Exception e){
			e.printStackTrace();
		}
        return -1;
	}

	@Override
	public void close() {
	}
}
