package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		try {
			byte[] b = conn.read(startPos, endPos);
			String path = "D:" + File.separator + "test"+File.separator+"123.jpg";
			RandomAccessFile f = new RandomAccessFile(path, "rw");
			f.seek(startPos);
			f.write(b);
			f.close();
			System.out.println(Thread.currentThread().getName()+"线程下载完毕");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}