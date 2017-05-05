package com.m0312.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.m0312.download.api.Connection;
import com.m0312.download.api.DownloadListener;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String descFilePath;
	private CyclicBarrier cyclicBarrier;
	
	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public DownloadThread( Connection conn, int startPos, int endPos,
			String descFilePath,CyclicBarrier cyclicBarrier){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.descFilePath=descFilePath;
		this.cyclicBarrier=cyclicBarrier;
	}
	@Override
	public void run(){	
		try {
			/*byte[] bytes=conn.read(startPos, endPos);
			os=new FileOutputStream(new File(descFilePath)); 
			os.write(bytes, startPos, endPos-startPos+1);
			cyclicBarrier.await();//等待其他线程
			*/			
			System.out.println("开始读["+startPos+","+endPos+"]");
			byte[] buffer = conn.read(startPos , endPos);
			RandomAccessFile file = new RandomAccessFile(descFilePath, "rw");
			file.seek(startPos);
			file.write(buffer, 0, buffer.length);
			file.close();
			cyclicBarrier.await();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		//System.out.println("所有线程都下载完成");
		//通知 FileDownloader ，自己已经做完
		
	}
}







