package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{
	
	
	private Connection conn;
	private int startPos;
	private int endPos;
	private String filePath;

	public DownloadThread(Connection conn, int startPos, int endPos,String filePath){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
	}
	public void run(){	
		try {
			System.out.println("线程" + this.getName() + "开始下载. startPos:" + startPos + "; endPos:" + endPos);
			byte[] buffer = conn.read(startPos, endPos);
			RandomAccessFile ra = new RandomAccessFile(filePath, "rw");
			ra.seek(startPos);
			ra.write(buffer, 0, buffer.length);
			ra.close();
			System.out.println("线程" + this.getName() + "下载完成.");
			}catch (IOException e) {
				e.printStackTrace();
		}
	}
}
