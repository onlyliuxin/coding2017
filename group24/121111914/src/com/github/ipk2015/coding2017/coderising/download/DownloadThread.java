package com.github.ipk2015.coding2017.coderising.download;

import java.io.IOException;

import com.github.ipk2015.coding2017.coderising.download.api.Connection;
import com.github.ipk2015.coding2017.coderising.download.api.DownloadListener;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	int threadId;
	DownloadListener listener;
	public DownloadThread( Connection conn, int startPos, int endPos){
		super();
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public DownloadThread( Connection conn, int startPos, int endPos,int threadId,DownloadListener listener){
		super();
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.threadId=threadId;
		this.listener=listener;
	}
	public void run(){	
		try {
			conn.read(startPos,endPos,threadId,listener);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
