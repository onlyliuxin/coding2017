package com.coding.download;

import com.coding.download.api.Connection;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	File file;

	public DownloadThread(File file, Connection conn, int startPos, int endPos){
		this.file = file;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			System.out.println("DownloadThread.run");
			byte[] buffer = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(startPos);
			raf.write(buffer, 0, buffer.length);
			raf.close();
		} catch (IOException e) {
			System.out.println("e = " + e.getMessage());
		}
	}
}
