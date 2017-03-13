package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	//文件保存路径
	String path;

	//计数器
	CountDownLatch countDownLatch;


	public DownloadThread( Connection conn, int startPos, int endPos, String path, CountDownLatch countDownLatch){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.path = path;
		this.countDownLatch = countDownLatch;
	}
	public void run(){
		try {
			RandomAccessFile currentPaat = new RandomAccessFile(path, "rw");
			currentPaat.seek(startPos);
			byte[] buffer = conn.read(startPos, endPos);
			currentPaat.write(buffer);
			currentPaat.close();
			countDownLatch.countDown();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
