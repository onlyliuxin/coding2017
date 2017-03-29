package com.basic.week3.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.basic.week3.download.api.Connection;

public class DownloadThread extends Thread{
	public static int count=0;
	Connection conn;
	int startPos;
	int endPos;
	
	public DownloadThread(Connection conn, int startPos, int endPos){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			count++;
			conn.read(startPos, endPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			count--;
		}
	}
}
