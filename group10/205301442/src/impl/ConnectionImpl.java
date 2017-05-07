package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
    
    private HttpURLConnection conn; 
	private InputStream is;
	public ConnectionImpl(HttpURLConnection conn) {
		try {
			this.conn = conn;
			is = conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		  if(is==null){
			  return null;
		  }

		  is.skip(startPos);
		  int len = endPos-startPos;
		  byte[] bt = new byte[len];
		  byte[] temp = new byte[1024];
		  int m=0;
		  int nowLen=0;
		  while((m=is.read(temp))>0){
			  if(nowLen+m>len){
				  System.arraycopy(temp, 0, bt, nowLen, len-nowLen);
				  break;
			  }
			  System.arraycopy(temp, 0, bt, nowLen, m);
			  nowLen += m;
			  
		  }
		  return bt;
	}

	@Override
	public int getContentLength() {
		if(is==null){
			return 0;
		}
		try {
			int length=conn.getContentLength();
			return length;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void close() {
		if(is!=null){
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
