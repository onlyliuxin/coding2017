package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CountDownLatch latch;  
	
	File file = new File("C://download.mp3");

	public DownloadThread( Connection conn, int startPos, int endPos, CountDownLatch latch){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.latch = latch;
	}
	public void run(){	
		RandomAccessFile raf = null;
		try {
			byte[] byteRead = conn.read(startPos, endPos);
			raf = new RandomAccessFile(file, "rw");;
			raf.seek(startPos);
			raf.write(byteRead);

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			latch.countDown();
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			conn.close();
		}
	}
}