package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadThreadListener;

import java.io.IOException;

public class DownloadThread extends Thread{
    Connection conn;
	DownloadThreadListener dt;
    int startPos;
	int endPos;


	public DownloadThread( Connection conn, int startPos, int endPos){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}

	public void setListener(DownloadThreadListener dt){
	    this.dt = dt;
    }

	public void run(){
        try {
            byte[] bytes = conn.read(startPos, endPos);
            dt.onThreadComplete(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
