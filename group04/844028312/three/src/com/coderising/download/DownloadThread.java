package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionManagerImpl;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;
	String url;
	public DownloadThread(CyclicBarrier barrier, String url, int startPos, int endPos){
		this.barrier=barrier;
		//this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.url=url;
	}
	public void run(){	
		try {
			ConnectionManager cm = new ConnectionManagerImpl();
			conn=cm.open(url);
			
			byte[] b=conn.read(startPos, endPos);
			
			RandomAccessFile randomFile = new RandomAccessFile("D://test.zip", "rw");
			write(randomFile,b);
			barrier.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  synchronized void write(RandomAccessFile randomFile ,byte[] b) throws IOException{
		randomFile.seek(startPos);
		randomFile.write(b);
		randomFile.close();
	}
}
