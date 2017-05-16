package download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import download.api.Connection;
import download.api.ConnectionException;

/**
 * ConnectionImpl这个类不是public,是省略的，只在impl这个包中可见
 * @author 12946
 *
 */
class ConnectionImpl implements Connection{

	URL url;
	static final int BUFFER_SIZE = 1024;
	
	public ConnectionImpl(String url) throws ConnectionException {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
		
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		InputStream input = httpConn.getInputStream();
//		input.skip(startPos);
		byte[] buff = new byte[BUFFER_SIZE];
		int totalLen = endPos - startPos + 1;
		//内存中的输出流，我们可以往这个内存中的输出流写东西
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while(baos.size() < totalLen){
			int len = input.read(buff);//input输入流read的数据放到buff中
			if(len < 0){
				break;
			}
			baos.write(buff, 0, len);
		}
		
		if(baos.size() > totalLen){//最后一次写入baos时，可能会写入多的数据
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}
		
		return baos.toByteArray();
		
	}

	@Override
	public int getContentLength() {
		try {
			URLConnection httpConn = url.openConnection();
			return httpConn.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		
		
	}

}
