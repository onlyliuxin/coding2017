package com.coding.basic.homework_03.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coding.basic.homework_03.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	boolean downOver = false;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		RandomAccessFile raf = null;
		try {
//			System.out.println(Thread.currentThread().getName() + "开始下载...");
			byte[] bytes = conn.read(startPos, endPos);
			
//			if(!file.exists()){
//				file.createNewFile();
//			}
			synchronized(this){
				File file = new File("copy.jpg");
				raf = new RandomAccessFile(file,"rw");
				raf.seek(startPos);
				raf.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(raf != null){
				try {
					raf.close();
					downOver = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}