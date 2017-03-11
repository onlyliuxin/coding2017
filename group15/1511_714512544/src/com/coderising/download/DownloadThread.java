package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{
	Connection conn;
	int startPos;
	int endPos;
	String url;

	public DownloadThread( Connection conn, int startPos, int endPos){
		try {
			this.conn = conn;
			this.startPos = startPos;
			this.endPos = endPos;
			url = conn.getUrl();
			RandomAccessFile raf = new RandomAccessFile("d:/t.jpg", "rwd");
			if(raf.length() == 0){
                raf.setLength(conn.getContentLength());
            }
            raf.close();
		} catch (IOException e) {
			throw new ExceptionInInitializerError();
		}
	}
	public void run(){	
		//实现
		try {
			conn.read(url,startPos,endPos);
		} catch (IOException e) {
			throw new RuntimeException("读取错误");
		}
	}
}
