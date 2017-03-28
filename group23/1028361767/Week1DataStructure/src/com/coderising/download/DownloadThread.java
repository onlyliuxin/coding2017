package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		try {
			byte[] bytes = conn.read(startPos, endPos);
			RandomAccessFile file = new RandomAccessFile("/Users/jie/Desktop/test.png", "rw");
			file.seek(startPos);
			file.write(bytes);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
