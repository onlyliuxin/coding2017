package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	private byte[] b = null;
	private static Lock lock = new ReentrantLock();
	private static int index=0;
	
	static FileOutputStream file = null;
	static{
		try {
			file = new FileOutputStream(new File("e://readme.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		try {
			this.b =  conn.read(startPos, endPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			conn.close();
		}

	}
	public void  run(){	
		if(lock.tryLock()){
			if(index==startPos){
				try {
					if (file!=null) {
						file.write(b);
					}
					index=endPos+1;
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
	
			}else{
				lock.unlock();
			}
		}
	}
}
