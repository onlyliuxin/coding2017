package com.multiThreadDownload;

import com.multiThreadDownload.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread {
	Connection conn;
	int startPos;
	int endPos;
	int threadId = 0;
	CyclicBarrier barrier;

	public DownloadThread(CyclicBarrier barrier, Connection conn, int threadId,
			int startPos, int endPos) {
		this.barrier = barrier;
		this.conn = conn;
		this.threadId = threadId;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	public void run() {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("yunpan.exe", "rwd");

			raf.seek(startPos);

			byte[] buffer = conn.read(startPos, endPos);

			raf.write(buffer, 0, buffer.length);
			raf.close();
			barrier.await();
			System.out.println("threadId" + threadId +"download success !");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}
}
