package code03.impl;

import code03.api.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;


public class ConnectionImpl implements Connection{

	private static final Logger logger = LoggerFactory.getLogger(ConnectionImpl.class);


	private URLConnection urlConnection;
	private int length = -1;

	public ConnectionImpl(URLConnection urlConnection){
		this.urlConnection  = urlConnection;
	}

	/**
	 * 读取urlConnection.getInputStream()中的数据，返回byte[]
	 */
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int contentLength = getContentLength();
		if(startPos < 0 || endPos > contentLength || contentLength <= 0){
			logger.info("index out of range !");
			return null;
		}

		InputStream raw = null;
		BufferedInputStream in = null;
		int size = endPos - startPos + 1;
		byte[] data = new byte[size];
		try{
			raw = urlConnection.getInputStream();
			in = new BufferedInputStream(raw);
			in.skip(startPos);

			int offset = 0;
			while(offset < size){
				int bytesRead = in.read(data, offset, size - offset);
				while (bytesRead  == -1){break;}
				offset += bytesRead;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			raw.close();
			in.close();
		}
		return data;
	}

	@Override
	public int getContentLength() {
		if(length != -1){
			return length;
		}
		length = urlConnection.getContentLength();

		//if without content-length header
		if(length == -1) {
			int offset = 0;
			InputStream raws = null;
			BufferedInputStream ins = null;
			try {
				raws = urlConnection.getInputStream();
				ins = new BufferedInputStream(raws);

				int max_size = 1024 * 1024;//1M
				byte[] data = new byte[max_size];

				int bytesRead = 0;
				while (bytesRead != -1) {
					ins.read(data, offset, max_size - offset);
					offset += bytesRead;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					raws.close();
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			length = offset;
		}
		return length;
	}

	@Override
	public void close() {
		if(urlConnection != null){
			urlConnection = null;
		}
	}

}
