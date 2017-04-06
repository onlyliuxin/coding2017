package com.github.fei9009.coderising0305.download;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.github.fei9009.coderising0305.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;
	String localFile;
	
	public DownloadThread( Connection conn, int startPos, int endPos, String localFile, CyclicBarrier barrier){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.localFile = localFile;
		this.barrier = barrier;
	}
	public void run(){	
		try{
			byte[] buffer = conn.read(startPos, endPos);
			RandomAccessFile file = new RandomAccessFile(localFile,"rw");
			file.seek(startPos);	
			file.write(buffer);
			file.close();
			conn.close();
			barrier.await(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
