package download;


import download.api.Connection;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	private CyclicBarrier barrier;
	private String localFile;
	public DownloadThread(Connection conn, int startPos, int endPos, String localFile, CyclicBarrier barrier){

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.localFile = localFile;
		this.barrier = barrier;
	}
	public void run(){


		try {
			System.out.println("Begin to read [" + startPos +"-"+endPos+"]");

			byte[] data = conn.read(startPos, endPos);

			RandomAccessFile file = new RandomAccessFile(localFile,"rw");

			file.seek(startPos);

			file.write(data);

			file.close();

			conn.close();

			barrier.await(); //等待别的线程完成

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
