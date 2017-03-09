package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;
	RandomAccessFile randomAccessFile;

	public CyclicBarrier getBarrier() {
		return barrier;
	}
	public void setBarrier(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	/**
	 * 构造函数
	 * 
	 * @param conn 线程独立的连接
	 * @param startPos 被下载文件的开始index
	 * @param length 被下载文件的end index
	 */
	public DownloadThread( Connection conn, int startPos, int endPos){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			byte[] b = conn.read(startPos, endPos);
			RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\tomcat.gif", "rwd");
			randomAccessFile.seek(startPos);
			randomAccessFile.write(b, 0, b.length);
			randomAccessFile.close();
			barrier.await();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn.close();
		}
	}
}
