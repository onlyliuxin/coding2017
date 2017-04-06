package com.coding.download;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.coding.download.api.Connection;
import com.coding.util.IOUtils;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	private File file;
	private CyclicBarrier barrier;

	public DownloadThread(Connection conn, int startPos, int endPos,File file,CyclicBarrier barrier) {
		this.barrier = barrier;	
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
	}
	
	public DownloadThread(Connection conn, int startPos, int endPos,File file) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
	}

	public void run() {
		byte[] buff;
		try {
			buff = conn.read(startPos, endPos);
			if(buff!=null&&buff.length!=0){
				IOUtils.writeFile(file, startPos, buff);
			}
			if(barrier!=null){  //修改后代码
				barrier.await();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} 
	}
}
