package org.coding.three.download;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import org.coding.three.download.api.Connection;

public class DownloadThread extends Thread{

	CyclicBarrier barrier;
	Connection conn;
	int startPos;
	int endPos;
	String destpath;

	public DownloadThread(CyclicBarrier barrier, Connection conn, int startPos, int endPos, String destpath) {
		this.barrier = barrier;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.destpath = destpath;
	}
	public void run(){	
		try {
			byte[] b = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile(destpath, "rw");
			raf.seek(startPos);
			raf.write(b);
			raf.close();
			conn.close();
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
