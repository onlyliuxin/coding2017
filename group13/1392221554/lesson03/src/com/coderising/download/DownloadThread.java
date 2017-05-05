package com.coderising.download;

import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	long startPos;
	long endPos;
    private RandomAccessFile raf; 
    final String OUT_FILE_NAME = "avatar.jpg";  
    private String status = "unrun";

	public DownloadThread(Connection conn, long startPos, long endPos){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void run(){
		this.status = "running";
		try {
			synchronized (this){
				// 以指定输出文件创建多个RandomAccessFile对象  
	            RandomAccessFile raf = new RandomAccessFile(OUT_FILE_NAME, "rw");
				raf.seek(startPos); 
				byte[] buff = this.conn.read(this.startPos, this.endPos);
				raf.write(buff,0,(int)(endPos-startPos));
				raf.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.status = "finished";
	}
}
