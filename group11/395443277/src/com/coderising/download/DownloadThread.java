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
	byte[] totalBytes;

	public DownloadThread(Connection conn, int startPos, int endPos, String filePath, CyclicBarrier barrier,
			byte[] totalBytes) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
		this.barrier = barrier;
		this.totalBytes = totalBytes;
	}

	public void run() {
		try {
			byte[] bytes = conn.read(startPos, endPos);
//			System.out.println(bytes.length);
//
//			int i = 0;
//			while (startPos <= endPos) {
//				totalBytes[startPos] = bytes[i];
//				i++;
//				startPos++;
//			}
			 RandomAccessFile RAFile = new RandomAccessFile(filePath, "rw");
			 RAFile.seek(startPos);
			 RAFile.write(bytes, 0, bytes.length);
			 RAFile.close();

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
