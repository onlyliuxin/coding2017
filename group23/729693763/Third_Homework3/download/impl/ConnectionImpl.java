package com.zhous.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.zhous.download.api.*;

public class ConnectionImpl implements Connection{


	InputStream is = null;
	URL url = null;
	HttpURLConnection http = null;
	ByteArrayOutputStream bos = new ByteArrayOutputStream();

	//构造方法：
	public ConnectionImpl() {
	}

	//打开url并且创建连接
	public ConnectionImpl(String url) {
		try {
			this.url = new URL(url);
			http = (HttpURLConnection) this.url.openConnection();
			//设置超时
			http.setConnectTimeout(5000);
			http.setReadTimeout(5000);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		//指定http中读取一定长度的byte
		http.setRequestProperty("Range","bytes= "+Integer.toString(startPos)+"-"+Integer.toString(endPos));
		http.connect();

		//读取输入流到其中
		is = http.getInputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len = is.read(buffer) ) != -1) {

			bos.write(buffer, 0, len);
		}

		//关闭所有流，每次read的时候自动关闭。
		close();
		return bos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return http.getContentLength();
	}

	@Override
	public void close() {
		try {
			bos.close();
			if(is != null) {
				is.close();
			}
			http.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
