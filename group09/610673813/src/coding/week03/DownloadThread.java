package coding.week03;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

import coding.week03.download.api.Connection;


public class DownloadThread extends Thread {

	private Connection conn;
	private int startPos;
	private int endPos;
	private RandomAccessFile file;
	private CountDownLatch threadsSignal;
	
	public DownloadThread(CountDownLatch threadsSignal,Connection conn, int startPos, int endPos, RandomAccessFile file) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
		this.threadsSignal=threadsSignal;
	}

	public void run() {
		try {
			byte[] buffer = conn.read(startPos, endPos);
			file.seek(startPos);
			file.write(buffer, 0, buffer.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			threadsSignal.countDown();
			System.out.println("当前线程："+Thread.currentThread().getName()+";剩余线程数："+threadsSignal.getCount());
		}
	}
}
