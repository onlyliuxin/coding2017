package com.github.chaoswang.learning.java.downloader;

import java.io.IOException;

import com.github.chaoswang.learning.java.downloader.api.Connection;
import com.github.chaoswang.learning.java.downloader.api.DownloadListener;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	DownloadListener listener;

	public DownloadThread( Connection conn, int startPos, int endPos, DownloadListener listener){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.listener = listener;
	}
	public void run(){	
		try {
			conn.read(startPos, endPos);
			listener.notifyFinished();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}