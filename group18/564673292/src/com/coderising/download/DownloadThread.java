package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ThreadListener;

import java.io.IOException;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	ThreadListener listener;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			byte[] buffer = conn.read(startPos, endPos);
			listener.onComplete(buffer);

        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setListener(ThreadListener listener){
	    this.listener = listener;
	}
}
