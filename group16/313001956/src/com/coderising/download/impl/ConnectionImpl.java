package com.coderising.download.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	URL fileurl = null;
	HttpURLConnection uRLconn = null;
	InputStream inStream = null;

	@Override
	public byte[] read(int startPos, int endPos, File file) throws IOException {
		uRLconn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		if (inStream == null)
			inStream = uRLconn.getInputStream();
		int size = endPos - startPos + 1;

		byte[] bt = new byte[size];

		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		raf.seek(startPos);
		int lenth=0;
		//lenth = inStream.read(bt,0,size);
		while ((lenth = inStream.read(bt,0,size)) != -1)
			raf.write(bt, 0, lenth);
		raf.close();

		return bt;

	}

	@Override
	public int getContentLength() {
		int fileSize = uRLconn.getContentLength();
		return fileSize;
	}

	@Override
	public void close() {
		if (inStream != null)
			try {
				inStream.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void setConn(HttpURLConnection uRLconn) {
		this.uRLconn = uRLconn;
	}

	public HttpURLConnection getConn() {
		return this.uRLconn;
	}

	public void setFileurl(URL fileurl) {
		this.fileurl = fileurl;
	}

	public URL getFileurl(URL fileurl) {
		return this.fileurl;
	}

	public void setinStream(InputStream inStream) {
		this.inStream = inStream;
	}

	public InputStream getinStream() {
		return this.inStream;
	}
}
