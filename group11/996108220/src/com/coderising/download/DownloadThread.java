package com.coderising.download;

import java.io.IOException;


import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

public class DownloadThread extends Thread{
	
	Connection conn;
	int startPos;
	int endPos;
	boolean status=false;
	CyclicBarrier barrier;

	public DownloadThread( Connection conn, int startPos, int endPos,CyclicBarrier barrier){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.barrier=barrier;
	}
	public void run(){
        try {
        	
			byte[] buffer=conn.read(startPos, endPos);
	        RandomAccessFile fos = new RandomAccessFile(FileDownloader.SAVE_FILE, "rw");  
	        fos.seek(startPos);
	        fos.write(buffer, 0, endPos-startPos);
	        fos.close();
	        barrier.await(); 
        	
        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
        
        System.out.println("线程" + this.getId() + "已经完成");
		
	}
	
		
}
