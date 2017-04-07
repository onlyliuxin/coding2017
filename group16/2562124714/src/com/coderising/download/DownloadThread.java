package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CountDownLatch latch;
	RandomAccessFile ResultFile;

	public DownloadThread( Connection conn, int startPos, int endPos, CountDownLatch latchArg, RandomAccessFile fileArg){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.latch = latchArg;
		this.ResultFile = fileArg;
	}
	public void run(){
		try {
			byte []b = this.conn.read(this.startPos, this.endPos);
			System.out.println(b.toString());
			ResultFile.seek(startPos);
			ResultFile.write(b, 0, endPos - startPos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.latch.countDown(); //下载完成就lockdown
	}
}
