package main.week03.download;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import main.week03.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	
	int startPos;
	int endPos;
	
	CyclicBarrier barrier;
	
	String localFile;

	public DownloadThread(Connection conn, int startPos, int endPos,
			String localFile, CyclicBarrier barrier) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.localFile = localFile;
		this.barrier = barrier;
	}

	public void run() {

		try {
			System.out.println("Begin to read [" + startPos + "-" + endPos
					+ "]");

			byte[] data = conn.read(startPos, endPos);
			//设置文件的读取权限，每个线程都独立有这个实例，这样，多线程读写同一文件就没问题。
			RandomAccessFile file = new RandomAccessFile(localFile, "rw");

			file.seek(startPos);

			file.write(data);

			file.close();
			
			conn.close();

			barrier.await(); // 等待别的线程完成

		} catch (Exception e) {
			//如果线程出错了，无法await，怎么处理？
			e.printStackTrace();
		} finally{}//这块里应该写close的

	}
}
