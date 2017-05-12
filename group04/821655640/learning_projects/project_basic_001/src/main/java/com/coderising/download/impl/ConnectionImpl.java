package com.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URLConnection uc = null;
	private BufferedInputStream bs = null;
	private URL url;
	int startPos;
	int endPos;
	public ConnectionImpl(String path,int _startPos,int _endPos) throws Exception {
		try {
			if (startPos >= _endPos || _startPos < 0) {
				throw new IllegalArgumentException();
			}
			this.startPos = _startPos;
			this.endPos = _endPos;
			url = new URL(path);
			uc = url.openConnection();
			uc.setRequestProperty("Range", "bytes=" + _startPos + "-" + _endPos);
			bs = new BufferedInputStream(uc.getInputStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	public ConnectionImpl(String path) throws Exception {
		try {
			
			url = new URL(path);
			uc = url.openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int read(byte data[])  {
		int ret = 0;
		try {
			ret  = bs.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return ret;
	}

	public int getContentLength() {
		return uc.getContentLength();
	}
	
	public URL getURL() {
		return url;
	}
	
	public void close() {
		try {
			if (null != bs) {
				bs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int getStartPos() {
		return this.startPos;
	}
	public int getEndPos() {
		return this.endPos;
	}

}
