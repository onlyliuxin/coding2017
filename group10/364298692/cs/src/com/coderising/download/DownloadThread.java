package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.utils.IOUtils;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	private File file;
	private CyclicBarrier cyclicBarrier; //线程的计数器
	
	public DownloadThread(Connection conn, int startPos, int endPos,File file, CyclicBarrier cyclicBarrier) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
		this.cyclicBarrier = cyclicBarrier;
	}
	
	public DownloadThread(Connection conn, int startPos, int endPos,File file) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
	}

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		
		try {
			byte[] data = conn.read(startPos, endPos);
			
			RandomAccessFile asf = new RandomAccessFile(file, "rw");
			
			asf.seek(startPos);
			asf.write(data);
			asf.close();
			conn.close();
			
			cyclicBarrier.await();//等待别的线程完成
			
		} catch (Exception e) {
			e.printStackTrace();
			//发生了异常要如何处理？
			//TODO 重试
		}
		
	}
}
