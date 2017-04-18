package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;

	public DownloadThread( Connection conn, int startPos, int endPos, CyclicBarrier barrier){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.barrier=barrier;
	}
	public void run(){	
		try {
			byte[] buff = conn.read(startPos, endPos);
			RandomAccessFile rf = new RandomAccessFile("zz.jpg", "rw");
			rf.seek(startPos);
			rf.write(buff);
			rf.close();
			conn.close();
			barrier.await();
		} catch (IOException | InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}
}
