package com.github.wdn.coding2017.coderising.download;


import com.github.wdn.coding2017.coderising.download.api.Connection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	private static final Object lock = new Object();
	private static final File file = new File("E:\\down.jpg");
	private CountDownLatch latch;
	public DownloadThread(Connection conn, int startPos, int endPos,CountDownLatch latch){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.latch = latch;
	}
	public boolean writeFile(){
		if (file.length()==startPos) {
			synchronized (lock){
				byte[] image = new byte[0];
				try {
					image = conn.read(startPos, endPos);
					FileOutputStream fos = new FileOutputStream(file,true);
					fos.write(image);
					fos.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}else{
			return false;
		}
	}
	public void run(){
		while (!writeFile()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		latch.countDown();
	}
}
