package org.xukai.coderising.download.impl;

import com.google.common.base.Preconditions;
import org.xukai.coderising.download.api.Connection;
import org.xukai.coderising.download.api.ConnectionException;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;


public class ConnectionImpl implements Connection {

	private HttpURLConnection urlConnection;


	@Override
	public byte[] read(int startPos, int endPos) throws ConnectionException {
		byte[] buff = new byte[0];
		BufferedInputStream inputStream = null;
		try {
			urlConnection.setRequestProperty("Range","bytes=" + startPos + "-" + (endPos));
			inputStream = new BufferedInputStream(urlConnection.getInputStream());
			buff = new byte[endPos-startPos];
			int len = 0;
			int hasRead = 0;
			while (hasRead < buff.length &&(len=inputStream.read(buff,hasRead,buff.length-hasRead)) != -1 ){
                hasRead = hasRead + len;
            }
			Preconditions.checkArgument(hasRead == buff.length,"读取输入流异常");
		} catch (Exception e) {
			throw new ConnectionException(e.getMessage());
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		return buff;
	}

	@Override
	public int getContentLength() {
		return urlConnection.getContentLength();
	}

	@Override
	public void close() {
		if (urlConnection != null){
			urlConnection.disconnect();
		}
	}

	public void setUrlConnection(HttpURLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}
}
