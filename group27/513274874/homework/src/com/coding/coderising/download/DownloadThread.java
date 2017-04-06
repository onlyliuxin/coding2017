package com.coding.coderising.download;

import com.coding.coderising.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	// 将下载到的字节输出到raf中
	private RandomAccessFile raf;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			conn.read(startPos,endPos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
