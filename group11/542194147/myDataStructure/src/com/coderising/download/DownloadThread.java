package com.coderising.download;

import java.io.IOException;

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
			conn.read(startPos, endPos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
