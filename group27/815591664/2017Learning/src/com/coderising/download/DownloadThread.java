package com.coderising.download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	String threadId;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, String threadId, int startPos, int endPos){
		
		this.conn = conn;
		this.threadId = threadId;
		this.startPos = startPos;
		this.endPos = endPos;
		
	}
	public void run(){
		System.out.println("线程"+threadId+"开始下载,起点为"+startPos+",终点为"+endPos);
		
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("f:/test.jpg", "rw");
			byte[] content = conn.read(startPos, endPos);
			raf.seek(startPos);//文件写入的开始位置.
			raf.write(content);
			System.out.println("线程"+threadId+"下载完毕！");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
	}
}
