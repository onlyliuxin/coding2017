package com.coderising.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread implements Runnable {

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;
	String filePath;

	public static final Object objA = new Object();
	 
	public DownloadThread(CyclicBarrier barrier, Connection conn, int startPos, int endPos, String filePath){
		this.barrier = barrier;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
	}
	
	public void run(){	

		try{
			System.out.println("begin download startPos="+startPos+",endPos="+endPos);
			RandomAccessFile file = null;

			byte[] buffer = conn.read(startPos , endPos);
			file = new RandomAccessFile(filePath, "rw");
			file.seek(startPos);
			file.write(buffer, 0, buffer.length);
			file.close();
		
			barrier.await();

			conn.close();
		}catch(Exception e){
			System.out.println("download error:startPos="+startPos+",endPos="+endPos);
		}

	}
	
 
}
