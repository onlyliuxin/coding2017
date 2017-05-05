package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;

public class ConnectionImpl implements Connection{
	
	private URLConnection mUrlConnection = null;
	private InputStream iStream;
	private int currPos = 0;
	
	public ConnectionImpl(URL url) throws ConnectionException{
		try {
			mUrlConnection = url.openConnection();
			mUrlConnection.connect();
			iStream = mUrlConnection.getInputStream();
		} catch (IOException e) {
			mUrlConnection = null;
			e.printStackTrace();
			throw new ConnectionException();
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		int bytesLen = endPos - startPos + 1;
		byte[] buffer = new byte[bytesLen];

		if (currPos < startPos) {
			for (int remaining = startPos - currPos; remaining > 0;) {
				remaining -= iStream.skip(remaining);
			}
		} else if (currPos > startPos) {
			// should not read previous bytes of input stream. 
			// return null to end this thread.
			return null;
		}
		
		for (int offset = 0, len; 
			bytesLen > 0 && (len = iStream.read(buffer, offset, bytesLen)) >= 0;
			offset += len, bytesLen -= len) ;
		currPos = endPos + 1;
		
		return buffer;
	}

	@Override
	public int getContentLength() {
		if (mUrlConnection != null) {
			return mUrlConnection.getContentLength();
		}
		return 0;
	}

	@Override
	public void close() {
		try {
			if (iStream != null) {
				iStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		currPos = 0;
		mUrlConnection = null;
	}

}
