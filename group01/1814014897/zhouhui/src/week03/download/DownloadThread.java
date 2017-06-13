package week03.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import week03.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;

	public DownloadThread( Connection conn, int startPos, int endPos,CyclicBarrier barrier){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.barrier = barrier;
	}
	
	@Override
	public void run(){	
			try {
				byte[] piece = conn.read(startPos, endPos);
				System.out.println("此线程下载总长度:"+piece.length+",范围:"+startPos+"~"+endPos);
				RandomAccessFile m = new RandomAccessFile("download.jpg", "rw");
				m.seek(startPos);
				m.write(piece);
				m.close();
				barrier.await();
			} catch (IOException|InterruptedException|BrokenBarrierException  e) {
				e.printStackTrace();
			}
		}		
}
