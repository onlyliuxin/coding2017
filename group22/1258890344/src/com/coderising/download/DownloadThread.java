package com.coderising.download;

import java.io.File;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	File file;

	public DownloadThread( Connection conn, int startPos, int endPos,File file){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.file=file;
	}
	
	public void run(){	
		try {
			conn.read(startPos, endPos,file);
//			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
