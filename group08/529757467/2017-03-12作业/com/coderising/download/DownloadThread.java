package com.coderising.download;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {

	private Connection conn;
	private int startPos;
	private int endPos;
	private CyclicBarrier barrier;

	public DownloadThread(Connection conn, int startPos, int endPos, CyclicBarrier barrier) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			conn.read(startPos, endPos);
			barrier.await();
		} catch (IOException | InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
