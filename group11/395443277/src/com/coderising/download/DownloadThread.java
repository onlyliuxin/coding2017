package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {
	Connection conn;
	int startPos;
	int endPos;
	String filePath;
	CyclicBarrier barrier;

	public DownloadThread(Connection conn, int startPos, int endPos, String filePath, CyclicBarrier barrier) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
		this.barrier = barrier;
	}

	public void run() {
		try {
			byte[] bytes = conn.read(startPos, endPos);
			System.out.println(bytes.length);

			RandomAccessFile RAFile = new RandomAccessFile(filePath, "rw");
			RAFile.seek(startPos);
			RAFile.write(bytes, 0, bytes.length);
			RAFile.close();
			conn.close();

			barrier.await();

		} catch (IOException e) {
			// TODO: what if download fail, deal with exception, need to inform main thread.
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
