package com.dudy.learn01.download;

import com.dudy.learn01.download.api.Connection;

import java.io.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread{

	private Connection conn;
    private int startPos;
	private int endPos;
	private RandomAccessFile raf;
	//private CyclicBarrier cb;
	private  CountDownLatch downLatch;


	public DownloadThread(Connection conn, int startPos, int endPos,
						   /*CyclicBarrier cb*/
						   CountDownLatch downLatch){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
//		this.cb = cb;
		this.downLatch = downLatch;
	}
	public void run(){
		try {
			byte[] read = conn.read(startPos, endPos);

			System.out.println("read length: -> "+read.length);
			//这里要注意新创建一个RandomAccessFile对象，而不能重复使用download方法中创建的
			raf = new RandomAccessFile(new File("/Users/dudy/Desktop/1.png"), "rw");
			//将写文件的指针指向下载的起始点
			raf.seek(startPos);
			raf.write(read, 0, read.length);

			downLatch.countDown();
//			cb.await();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				if (raf != null){
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (conn != null){
				conn.close();
			}

		}
	}
}