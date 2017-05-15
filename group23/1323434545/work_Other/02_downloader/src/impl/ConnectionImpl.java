package impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import api.Connection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection huConn;
	

	public ConnectionImpl(String url) {
		try {
			huConn = (HttpURLConnection) new URL(url).openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		huConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedInputStream bis = new BufferedInputStream(huConn.getInputStream());
		int len =0;
		byte[] bytes = new byte[1024];
		while((len=bis.read(bytes))!=-1){
			os.write(bytes, 0, len);
		}
		os.flush();
		return os.toByteArray();
	}

	@Override
	public int getContentLength() {
		return huConn.getContentLength();
	}

	@Override
	public void close() {
		if(null!=huConn){
			huConn.disconnect();
		}
		
	}

}