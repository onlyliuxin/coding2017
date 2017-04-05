package com.github.mrwengq.tid.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import com.github.mrwengq.tid.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL url;
	
	public ConnectionImpl(String url ){
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		URLConnection con = url.openConnection();
		con.setRequestProperty("RANGE","bytes="+startPos+"-"+endPos);
		BufferedInputStream bf = new BufferedInputStream(con.getInputStream());
		byte[] b = new byte[1024];
		int tolen =  endPos - startPos +1;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		while(bo.size()<tolen){
			int len = bf.read(b);
			if(len<0){
				break;
			}
			bo.write(b,0,len);
		}
		if(bo.size()>tolen){
			byte[] data = bo.toByteArray();
			return Arrays.copyOf(data, tolen);
		}
		return bo.toByteArray();
	}

	@Override
	public int getContentLength() {
		int a = 0;
		try {
			
			URLConnection con = url.openConnection();
			a = con.getContentLength();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void close() {
		
		
	}


}
