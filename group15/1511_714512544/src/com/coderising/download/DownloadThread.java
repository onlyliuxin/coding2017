package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{
	private RandomAccessFile raf;
	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		try {
			this.conn = conn;
			this.startPos = startPos;
			this.endPos = endPos;
			raf = new RandomAccessFile("d:/1.png", "rwd");
			if(raf.length() == 0){
                raf.setLength(conn.getContentLength());
            }
		} catch (IOException e) {
			throw new ExceptionInInitializerError();
		}
	}
	public void run(){	
		//实现
		try {
			conn.read(startPos,endPos);
		} catch (IOException e) {
			throw new RuntimeException("读取错误");
		}

	}
}
