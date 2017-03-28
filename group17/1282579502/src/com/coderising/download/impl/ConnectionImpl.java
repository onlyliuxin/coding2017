package com.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

public class ConnectionImpl implements Connection {

	protected URL resourceUrl = null;
	URLConnection connection = null;
	
	public ConnectionImpl(String iUrl) throws ConnectionException{
		try {
			this.resourceUrl = new URL(iUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getMessage());
		}
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int size = endPos - startPos +1;
		System.out.println(Thread.currentThread().getName() + ": " + " startPos: " +startPos + " endPos: " + endPos +" size = " + size);
		byte[] data = new byte[size];
//		if(this.connection == null){
//			this.connect();
//		}
//		InputStream is = resourceUrl.openStream(); 
		this.connection = resourceUrl.openConnection();
		HttpURLConnection conn = (HttpURLConnection)connection;
		conn.setRequestProperty("Range", "bytes="+startPos+"-" + endPos);
		conn.connect();
		InputStream is = conn.getInputStream();
		int count = 0;
		int readSum =0;
		while(readSum<size && count++ <5){
			readSum += is.read(data, readSum, size-readSum);
			//System.out.println("read " + readSum); 
		}
		return data;
	}

	@Override
	public int getContentLength() {
		int ret = -1;
		if(connection == null) {
			
			System.out.println("connection is NULL, open it");
			this.connect();
		}
		
		if(connection != null) {
			ret = connection.getContentLength();
			System.out.println("connection not null, total length=" + ret);
			this.close();
		}
		return ret;
	}

	@Override
	public void close() {
		if(connection != null){
			try {
				connection.getInputStream().close();
				//connection.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void connect() {
		try {
			this.connection = resourceUrl.openConnection();
//			this.getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
