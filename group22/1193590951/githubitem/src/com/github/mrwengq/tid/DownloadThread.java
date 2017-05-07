package com.github.mrwengq.tid;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.github.mrwengq.tid.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String fileName;
	CyclicBarrier  cb;
	
	public DownloadThread( Connection conn, String fileName ,int startPos, int endPos,CyclicBarrier cb){
		
		this.conn = conn;		
		this.fileName = fileName;
		this.startPos = startPos;
		this.endPos = endPos;
		this.cb = cb;
	}
	public void run(){
		byte[] b = null;
		try {
			 b  = conn.read(startPos,endPos);
			 RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			 raf.seek(startPos);
			 raf.write(b);
			 raf.close();
			 conn.close();
			 cb.await();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
