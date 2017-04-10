package com.example.download;


import com.example.download.api.Connection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String file;
	CountDownLatch latch;

	public DownloadThread( Connection conn, int startPos, int endPos,String file,CountDownLatch latch){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
		this.latch = latch;
	}
	public void run(){
		RandomAccessFile randomAccessFile = null;
		try {
			byte[] data = conn.read(startPos, endPos);
			randomAccessFile = new RandomAccessFile(file, "rw");
			randomAccessFile.seek(startPos);
			randomAccessFile.write(data);
			latch.countDown();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (randomAccessFile != null) {
					randomAccessFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			conn.close();
		}
	}
}
