package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String filePath;

	public DownloadThread( Connection conn, int startPos, int endPos,String filePath){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		
		this.filePath = filePath;
	}
	
	public void run(){	
		try {
			System.out.println("begin download startPos="+startPos+",endPos="+endPos);
			
			byte[] buf = conn.read(startPos, endPos);
			
			RandomAccessFile randomFile = new RandomAccessFile(filePath, "rwd");
			
			randomFile.seek(startPos);
			randomFile.write(buf,0,endPos-startPos);
			
			randomFile.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
