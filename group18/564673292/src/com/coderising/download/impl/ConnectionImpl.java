package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionImpl implements Connection{
    HttpURLConnection conn;
    String url;
    public ConnectionImpl(String url){
        this.url = url;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
        HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        c.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream in = c.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int nRead = 0;
        byte[] buffer = new byte[1024];
        while((nRead = in.read(buffer)) != -1){
            out.write(buffer, 0 , nRead);
        }
        in.close();
        out.close();
        c.disconnect();
		return out.toByteArray();
	}

	@Override
	public int getContentLength() {
        int length = conn.getContentLength();
        this.close();
        return length;
	}

	@Override
	public void close() {
		conn.disconnect();
	}

}
