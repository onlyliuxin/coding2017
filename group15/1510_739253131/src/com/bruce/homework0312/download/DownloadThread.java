package com.bruce.homework0312.download;

import com.bruce.homework0312.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	private String localFile;
	private CyclicBarrier barrier;

	public DownloadThread(Connection conn, int startPos, int endPos, String localFile, CyclicBarrier barrier){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.localFile = localFile;
		this.barrier = barrier;
	}

	public void run(){
		System.out.println("Begin to read [" + startPos + "-" + endPos + "]");
		try {
			byte[] data = conn.read(startPos, endPos);
            RandomAccessFile file = new RandomAccessFile(localFile, "rw");
            file.seek(startPos);
            file.write(data);
            file.close();
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
