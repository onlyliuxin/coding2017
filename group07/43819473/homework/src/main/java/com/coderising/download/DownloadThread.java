package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	private CyclicBarrier barrier;
	DownloadListener listener;
	public DownloadThread(CyclicBarrier barrier, Connection conn, int startPos, int endPos, DownloadListener listener){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.barrier=barrier;
		this.listener=listener;
	}
	public void run(){
		try {
			System.out.println("startPos:"+startPos+",endPos:"+endPos);
			byte[] data=conn.read(this.startPos,this.endPos);
			byte2file(data,"d:\\easyrecovery-v3.3.29.50320.zip");
			barrier.await();
			System.out.println(Thread.currentThread().toString()+" finish");
			listener.notifyFinished();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	private static void byte2file(byte[] data, String path){
		if(data.length<3||path.equals("")) return;
		try{
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(data,0,data.length);
			outputStream.close();
		} catch(Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
	}
}
