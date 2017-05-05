package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;


public class DownloadThread extends Thread{

	Connection conn;
	CountDownLatch latch;
	String localpath;
	RandomAccessFile raf;
	int startPos;
	int endPos;

	public DownloadThread(Connection conn, int startPos, int endPos, String localpath ,RandomAccessFile raf ,  CountDownLatch latch){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.latch = latch;
		this.localpath = localpath;
		this.raf = raf;
	}

	public void run(){
		try {
			RandomAccessFile raf = new RandomAccessFile(localpath,"rwd");
			byte[] slice_bytes = conn.read(startPos, endPos);
			raf.seek(startPos);
			raf.write(slice_bytes,0,slice_bytes.length);
			raf.close();
			latch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
