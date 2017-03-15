package com.easy.codersing.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.easy.codersing.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	File file;

	public DownloadThread(Connection conn, int startPos, int endPos,File file){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.file=file;
	}
	
	public void run(){	
		try {
			System.out.println("begin download: startPos="+startPos+",endPos="+endPos);
			
			byte[] buffer = conn.read(startPos, endPos);
			RandomAccessFile raf=new RandomAccessFile(file, "rwd");			
			raf.seek(startPos);
			raf.write(buffer,startPos,endPos);
			raf.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
