package com.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

import com.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	File file;
	CountDownLatch countDL;

	public DownloadThread( Connection conn, int startPos, int endPos, File file, CountDownLatch countDL){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
		this.countDL = countDL;
	}
	public void run(){	
		System.out.println(Thread.currentThread().getId() + " begin download, start point:" + startPos + ", end point:" + endPos);
		try {
			byte[] buffer = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(startPos);
			raf.write(buffer);
			raf.close();
			System.out.println(Thread.currentThread().getId() + " finished download");
			countDL.countDown();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		countDL.countDown();
	}
}
