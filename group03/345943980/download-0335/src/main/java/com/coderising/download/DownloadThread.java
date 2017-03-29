package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {

	private Connection conn;
	private int startPos;
	private int endPos;
	private RandomAccessFile randomFile;
	private CountDownLatch threadsCnt;

	public DownloadThread(Connection conn, int startPos, int endPos, RandomAccessFile randomFile,
			CountDownLatch threadsCnt) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.randomFile = randomFile;
		this.threadsCnt = threadsCnt;
	}

	public void run() {
		try {
			byte[] buffer = conn.read(startPos, endPos);
			randomFile.seek(startPos);
			randomFile.write(buffer, 0, buffer.length);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			threadsCnt.countDown();
			System.out.println("当前线程：" + Thread.currentThread().getName() + ";剩余线程数："
					+ threadsCnt.getCount());
		}
	}
}