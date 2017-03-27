package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URL url;
	
	private final int byte_Size = 1024;
	
	public ConnectionImpl(URL url) {
		this.url = url;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		URLConnection opCon = url.openConnection();
		opCon.setAllowUserInteraction(true);
		opCon.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		//读入流
		InputStream in = opCon.getInputStream();
		//写出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] bt = new byte[byte_Size];
		int length = 0;
		while(-1!=(length=in.read(bt))){
			out.write(bt, startPos, endPos);
		}
		
		in.close();
		out.close();
		return out.toByteArray();
	}

	@Override
	public int getContentLength() {
		int contentLength = 0;
		try {
			contentLength = url.openConnection().getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return contentLength;
	}

	@Override
	public void close() {
		if(url!=null){
			url = null;
		}
		
	}

}
