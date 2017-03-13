package download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

import download.api.Connection;

public class ConnectionImpl implements Connection{
	HttpURLConnection URLConn;
	InputStream inputStream = null;

	public ConnectionImpl(HttpURLConnection URLConn) {
		this.URLConn = URLConn;
	}



	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] bytes = new byte[endPos - startPos + 1];
		RandomAccessFile raf = null;

		URLConn.setRequestProperty("Range", "bytes" + startPos + "-" + endPos);
		int request = URLConn.getResponseCode();
		byte[] buffer = new byte[1024];
		if (request == 206) {
			InputStream inputStream = URLConn.getInputStream();

			int length = 0;
			while ((length = inputStream.read(bytes)) != -1) {
				raf.write(buffer,0,length);
			}

			inputStream.close();
		}

		return buffer;
	}

	@Override
	public int getContentLength() {
		
		return URLConn.getContentLength();
	}

	@Override
	public void close() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (URLConn != null) {
			URLConn.disconnect();
		}
		
	}

}
