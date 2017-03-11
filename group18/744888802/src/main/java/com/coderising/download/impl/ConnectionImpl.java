package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionImpl implements Connection{
	private String url ;
	private String saveFile= "d:/temp/123.jpg";

	public ConnectionImpl(String url){
		this.url = url;
		try {
			URL httpUrl = new URL(this.url);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		URL url = new URL(this.url);
		URLConnection con = url.openConnection();

		con.setAllowUserInteraction(true);
		con.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

			RandomAccessFile fos = new RandomAccessFile(saveFile, "rw");
			fos.seek(startPos);
			byte[] buf = new byte[256];
			BufferedInputStream bis = null;
			long curPos = startPos;//当前下载的位置

			bis = new BufferedInputStream(con.getInputStream());

			while(curPos < endPos){
				int len = bis.read(buf, 0, 256);
				if(len == -1){
					break;
				}
				fos.write(buf, 0, len);
				curPos = curPos + len;
			}
			System.out.println("Download " + startPos + " - " + endPos + " finish!");
			bis.close();
			fos.close();



		return null;
	}

	@Override
	public int getContentLength() {
		URLConnection con = null;
		try {
		URL url = new URL(this.url);
			con = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return con.getContentLength();
	}

	@Override
	public void close() {

		
	}

}
