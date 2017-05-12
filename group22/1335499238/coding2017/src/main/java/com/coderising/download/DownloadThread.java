package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CountDownLatch cdl;

	public DownloadThread( Connection conn, int startPos, int endPos, CountDownLatch cdl){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.cdl = cdl;
	}
	public void run(){	
		try {
			byte[] read = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile("F:\\test.rar", "rwd");
			raf.seek(startPos);
			raf.write(read);
			raf.close();
			cdl.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
