package com.coderising.download;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier ;
	String filePath;

	public DownloadThread(CyclicBarrier barrier , Connection conn, int startPos, int endPos , String filePath){
		
		this.barrier = barrier;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
	}
	public void run(){	
		try{
			System.out.println("begin download startPos="+startPos+",endPos="+endPos);
			byte[] buffer = conn.read(startPos , endPos);
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			file.seek(startPos);
			file.write(buffer, 0, buffer.length);
			file.close();
			barrier.await();
		}catch(Exception e){
			System.out.println("download error:startPos="+startPos+",endPos="+endPos);
		}
		
	}
}
