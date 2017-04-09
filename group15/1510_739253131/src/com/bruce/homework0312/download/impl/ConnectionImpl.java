package com.bruce.homework0312.download.impl;

import com.bruce.homework0312.download.api.Connection;
import com.bruce.homework0312.download.api.ConnectionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

class ConnectionImpl implements Connection{

	URL url;
	static final int BUFFER_SIZE = 1024;

	ConnectionImpl(String _url) throws ConnectionException{
		try {
			//根据字符串路径拿到一个URL对象
			url = new URL(_url);
		} catch (MalformedURLException e) {
			throw new ConnectionException(e);
		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream inputStream = httpConn.getInputStream();
        //跳过inputStream前startPos字节数据，但由于skip内部是从头读取并且跳过的，该方法达不到预期效果
//        inputStream.skip(startPos);
        byte[] buffer = new byte[BUFFER_SIZE];
        int totalLen = endPos - startPos + 1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (outputStream.size() < totalLen) {
            //从输入流中读取最多buffer.length个字节，并将其存储在缓冲区数组buffer中
            int read = inputStream.read(buffer);
            //读到文件末尾时，inputStream.read(buffer)返回-1
            if (read < 0) {
                break;
            }
            //将buffer中从0开始到read个字节写入outputStream
            outputStream.write(buffer, 0, read);
        }

        if (outputStream.size() > totalLen) {
            byte[] data = outputStream.toByteArray();
            return Arrays.copyOf(data, totalLen);
        }
        return outputStream.toByteArray();
	}

	@Override
	public int getContentLength() {
        URLConnection conn;
        try {
            conn = url.openConnection();
            return conn.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
	}

	@Override
	public void close() {
		
	}

}
