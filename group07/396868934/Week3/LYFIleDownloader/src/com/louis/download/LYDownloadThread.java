package com.louis.download;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.louis.download.api.LYConnection;

public class LYDownloadThread extends Thread {
	LYConnection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier ;
	File file;
	int current;

	public LYDownloadThread( LYConnection conn, int startPos, int endPos){
		
//		this.barrier = barrier;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
//		this.file = file;
		this.current = startPos;
	}
	
	public void run() {	
		try{
			System.out.println("begin download: startPos="+startPos+",endPos="+endPos);
			byte[] buffer = conn.read(startPos , endPos);
			RandomAccessFile rafile = new RandomAccessFile(file, "rw");
			rafile.seek(startPos);
			rafile.write(buffer, 0, buffer.length);
			rafile.close();
			barrier.await();
			System.out.println("finish download: startPos="+startPos+",endPos="+endPos);
		}catch(Exception e){
			System.out.println("download error:startPos="+startPos+",endPos="+endPos+",msg:"+e.getMessage());
		}
	}
}
