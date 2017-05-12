package com.coderising.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;


public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;
	String localFile;

	public DownloadThread(Connection conn, int startPos, int endPos, CyclicBarrier barrier, String localFile) {
		super();
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.barrier = barrier;
		this.localFile = localFile;
	}

	public void run() {
		
		try {	
			
			byte[] data = conn.read(startPos, endPos);		
			RandomAccessFile file = new RandomAccessFile(localFile,"rw");
			file.seek(startPos);	
			file.write(data);
			file.close();
			conn.close();
			barrier.await(); //等待别的线程完成
			
		} catch (Exception e) {			
			e.printStackTrace();
			
		} 
	}
}
