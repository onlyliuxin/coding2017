package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionImpl implements Connection{
    private HttpURLConnection conn;
    private BufferedInputStream bis;

	ConnectionImpl(String url) throws IOException {
	    conn = (HttpURLConnection) (new URL(url)).openConnection();
		this.init();
	}

	private void init() throws IOException {
		conn.setRequestMethod("GET");
		bis = new BufferedInputStream(conn.getInputStream());
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
        byte[] buffer = new byte[2]; // TODO glitch when the byte size is too big
//        BufferedReader br = new BufferedReader(new InputStreamReader(bis));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((bis.read(buffer, 0, buffer.length)) != -1){
            baos.write(buffer, 0, buffer.length);
        }
        return baos.toByteArray();
//        int i = 0;
//        br.skip(startPos);
//        while((c = br.read()) != -1 && i <= endPos - startPos){
//            bytes[i++] = (byte)c;
//        }
//        return bytes;
    }

	@Override
	public int getContentLength() {
        String contentLength = conn.getHeaderField("Content-Length");
        return Integer.parseInt(contentLength);
	}

	@Override
	public void close() {
		conn.disconnect();
	}

}
