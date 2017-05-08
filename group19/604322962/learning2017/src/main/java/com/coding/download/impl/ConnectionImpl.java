package com.coding.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.coding.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URLConnection urlConnection;

	public ConnectionImpl(URLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int readBytes = 0;
		/*int len = endPos-startPos+1;
		byte[] buffer = new byte[1024];
		urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		InputStream is = urlConnection.getInputStream();//已经设置了请求的位置，返回的是当前位置对应的文件的输入流
		while (readBytes<len) {
			int read = is.read(buffer,0,endPos-startPos+1);
			if (read == -1)
				break;
			readBytes += read;

		}
		is.close();
		return buffer;*/
		InputStream in = urlConnection.getInputStream();
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		in.skip(startPos);
		while((len = in.read(buf))!=-1){
			bytestream.write(buf, 0, len);
		}
		in.close();
		byte data[] = bytestream.toByteArray();
		bytestream.close();
		return data;
	}

	@Override
	public int getContentLength() {

		return urlConnection.getContentLength();
	}

	@Override
	public void close() {

	}


}
