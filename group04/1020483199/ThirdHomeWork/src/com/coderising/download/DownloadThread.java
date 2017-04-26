package com.coderising.download;



import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{
	
	
	private Connection conn;
	private int startPos;
	private int endPos;
	private String filePath;
	private CyclicBarrier barrier;

	public DownloadThread(Connection conn, int startPos, int endPos,String filePath,CyclicBarrier barrier){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
		this.barrier = barrier;
	}
	public void run(){	
		try {
			System.out.println("线程" + this.getName() + "开始下载. startPos:" + startPos + "; endPos:" + endPos);
			byte[] buffer = conn.read(startPos, endPos);
			RandomAccessFile ra = new RandomAccessFile(filePath, "rw");
			ra.seek(startPos);
			ra.write(buffer);
			//关闭流
			ra.close();
			conn.close();
			barrier.await();
			System.out.println("线程" + this.getName() + "下载完成.");
			}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
