package week3.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import week3.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	String localFile;
	// 它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
	// 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待
	CyclicBarrier barrier;

	public DownloadThread(Connection conn, int startPos, int endPos,
			String localFile, CyclicBarrier barrier) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.localFile = localFile;
		this.barrier = barrier;
	}

	@Override
	public void run() {

		System.out.println("Begin to read [" + startPos + "-" + endPos + "]");

		try {
			byte[] data = conn.read(startPos, endPos);
			
			RandomAccessFile file = new RandomAccessFile(localFile, "rw");
			
			file.seek(startPos);
			
			file.write(data);
			
			file.close();
			
			conn.close();
			
			barrier.await();//等待别的线程完成
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
