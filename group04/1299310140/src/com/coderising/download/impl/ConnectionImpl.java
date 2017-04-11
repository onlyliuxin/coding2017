package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private URLConnection urlconn;
	private InputStream fis;
	
	public ConnectionImpl(URLConnection urlconn) {
		super();
		this.urlconn = urlconn;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException{
		this.fis = this.urlconn.getURL().openStream();
		byte[] buffer = new byte[512];
		int count = 0;//某次read的字节数
		int sum = 0;//read的总字节数
		int length = endPos - startPos + 1;//当前线程需读取的字节数
		byte[] download = new byte[length];
		fis.skip(startPos);
		while((count = fis.read(buffer)) != -1){
			if(sum + count >= length){
				System.arraycopy(buffer, 0, download, sum, length - sum);
				sum = length;
				break;
			}else{
				System.arraycopy(buffer, 0, download, sum, count);
				sum = sum + count;
			}
		}
		return download;
	}

	@Override
	public int getContentLength() {
		return this.urlconn.getContentLength();
	}

	@Override
	public void close() {
		if(this.fis != null){
			try {
				this.fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
