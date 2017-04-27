package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.utils.IOUtils;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	private File file;
	private CyclicBarrier cyclicBarrier; //线程的计数器
	
	public DownloadThread(Connection conn, int startPos, int endPos,File file, CyclicBarrier cyclicBarrier) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
		this.cyclicBarrier = cyclicBarrier;
	}
	
	public DownloadThread(Connection conn, int startPos, int endPos,File file) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
	}

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		
		try {
			byte[] data = conn.read(startPos, endPos);
			
			//在最底层newRandomAccessFile对象，也防止了数据读写冲突。如果是通过参数传进来的，则有冲突的风险，需要用synchronized关键字
			RandomAccessFile asf = new RandomAccessFile(file, "rw");
			
			asf.seek(startPos);
			asf.write(data);
			asf.close();
			conn.close();
			
			cyclicBarrier.await();//等待别的线程完成
			
		} catch (Exception e) {
			e.printStackTrace();
			//发生了异常要如何处理？
			//TODO 重试
		}
		
	}
}
