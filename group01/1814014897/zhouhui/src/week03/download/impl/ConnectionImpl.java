package week03.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import week03.download.api.Connection;
import week03.download.api.ConnectionException;

public class ConnectionImpl implements Connection {
	
	HttpURLConnection conn;
	URL urlLocation;

	public ConnectionImpl(String url) throws ConnectionException {
		try {
			open(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void open(String url) throws Exception {
		urlLocation = new URL(url);
		conn = (HttpURLConnection) urlLocation.openConnection();
	}
	
	
	@Override
	public byte[] read(int startPos, int endPos) {
		
		ByteArrayOutputStream baos = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) urlLocation.openConnection();
			conn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
			InputStream in = conn.getInputStream();
			baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len = in.read(buffer)) != -1){
				baos.write(buffer, 0, len);
			}
			in.close();
			baos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {
		conn.disconnect();
	}

}
