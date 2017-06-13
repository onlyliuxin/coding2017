package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String filePath = null;
	private boolean finished = false;

	public boolean isFinished() {
		return finished;
	}
	public DownloadThread( Connection conn, int startPos, int endPos,String filePath){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
	}
	public void run(){	
		File f = new File(this.filePath);
		
		try {
			if(!f.exists()){
				f.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			byte[] ba = conn.read(startPos, endPos);
			System.out.println("["+this.getName()+"]ByteArray length="+ba.length+",endPos-startPos+1="+(endPos-startPos+1));
			raf.seek(startPos);
			raf.write(ba, 0, endPos-startPos+1);
			finished = true;
			System.out.println("["+this.getName()+"]DownloadThread-startPos="+startPos+"-endPos="+endPos+":download completed");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
