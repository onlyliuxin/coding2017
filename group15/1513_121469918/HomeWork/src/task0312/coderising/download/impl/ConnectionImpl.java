package task0312.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import task0312.coderising.download.api.Connection;



public class ConnectionImpl implements Connection {
	HttpURLConnection urlConnect;
	public ConnectionImpl(HttpURLConnection urlConnect) {
		this.urlConnect = urlConnect;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] result = new byte[endPos-startPos+1];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 设置发出请求，指定下载部分
		urlConnect.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		int code = urlConnect.getResponseCode();
		if(code == 206){
			InputStream is = urlConnect.getInputStream();
			byte[] bys = new byte[1024];
			int len = 0;
			while ((len = is.read(bys)) != -1) {
				baos.write(bys, 0, len);
			}
			
			result = baos.toByteArray();
			is.close();			
		}
		return result;
	}

	@Override
	public int getContentLength() {
		return urlConnect.getContentLength();
	}

	@Override
	public void close() {

	}

}
