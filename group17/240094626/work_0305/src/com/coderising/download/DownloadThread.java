package com.coderising.download;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	private int threadId;
	private String localFile;
	private static final int  BUFF_LENGTH = 1024 * 4;
	boolean isFinished = false;
	int currSize = 0;
	CyclicBarrier barrier;

	public DownloadThread( Connection conn, int startPos, int endPos,int threadId,String localFile,CyclicBarrier barrier){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.threadId = threadId;
		this.localFile = localFile;
		this.barrier = barrier;
	}
	public void run(){	
		try {
			System.out.println("Thread"+threadId+" begin download bytes range:"+startPos+"-"+endPos);
			RandomAccessFile raf = new RandomAccessFile(localFile, "rw");
			int totalLen = endPos - startPos + 1;
			while(currSize < totalLen){
				int start = currSize + startPos;
				int end = start + BUFF_LENGTH-1;
				byte[] data = conn.read(start,(end>endPos?endPos:end));
				
				raf.seek(start);
				raf.write(data);
				currSize += data.length;
				
				
			}
			raf.close();
			barrier.await(); //等待别的线程完成
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
