package com.github.orajavac.coding2017.download;

import java.io.RandomAccessFile;

import com.github.orajavac.coding2017.download.api.Connection;
import com.github.orajavac.coding2017.download.api.DownloadListener;

public class DownloadThread extends Thread{
	boolean downloadFinished=false;
	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		RandomAccessFile out = null;
		try{
			byte[] buffer = conn.read(startPos, endPos);
			out = new RandomAccessFile("src/com/github/orajavac/coding2017/download/1.jpg","rwd");
			out.seek(startPos);
			out.write(buffer);
			FileDownloader fileloader = new FileDownloader("");
			fileloader.getListener();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
