package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread{
	Connection conn;
	int startPos;
	int endPos;
	String savePath;
	CyclicBarrier barrier;  //栅栏

	public DownloadThread(Connection conn, int startPos, int endPos, String savePath, CyclicBarrier barrier){
		this.startPos = startPos;
		this.endPos = endPos;
		this.conn = conn;
		this.savePath = savePath;
		this.barrier = barrier;
	}
	public void run(){
		RandomAccessFile raf = null;
		//实现
		try {
			RandomAccessFile file = new RandomAccessFile(savePath,"rw");
			byte[] data= conn.read(startPos,endPos);

			raf.seek(startPos);
			raf.write(data);
			raf.close();
			conn.close();

			barrier.await();  //等待其他线程执行到这里，//等待别的线程完成
		} catch (Exception e) {
			throw new RuntimeException("读取错误");
		}
	}
}
