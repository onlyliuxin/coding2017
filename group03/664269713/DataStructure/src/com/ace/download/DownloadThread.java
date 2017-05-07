package com.ace.download;

import com.ace.download.api.Connection;
import com.ace.download.api.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	CyclicBarrier barrier;
	String filePath;

	public DownloadThread(Connection conn, int startPos, int endPos, String filePath, CyclicBarrier barrier){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
		this.barrier = barrier;
	}
	public void run(){
		try {
			byte[] bytes = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile(filePath, "rwd");
			raf.seek(startPos);
			raf.write(bytes);
			raf.close();
			conn.close();
			barrier.await();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
