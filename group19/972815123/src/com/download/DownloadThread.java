package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	
	String fileName;

	Object obj;
	private DownloadListener downLoadThread;
	
//	public DownloadThread setObj(Object obj){
//		this.obj = obj;
//		return this;
//	}
	
	public DownloadThread setOnThreadFinished(DownloadListener downLoadThread){
		this.downLoadThread = downLoadThread;
		return this;
	}
	
	
	public DownloadThread( Connection conn, int startPos, int endPos, String fileName){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.fileName = fileName;
	}
	public void run(){	
		try {
			double id = Thread.currentThread().getId();
			System.out.println(id);
			byte[] byArr = conn.read(startPos, endPos);
			int len = byArr.length;
			
			Thread.sleep(2000);
			
			RandomAccessFile currenctPart = new RandomAccessFile(fileName, "rw");
			currenctPart.seek(startPos);
			System.out.println(len + "readed length");
			currenctPart.write(byArr,0,len);
			currenctPart.close();
			System.out.println(id);
			downLoadThread.notifyFinished();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
