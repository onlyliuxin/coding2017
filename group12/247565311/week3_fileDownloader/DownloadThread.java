package week3_fileDownloader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import week3_fileDownloader.api.Connection;

public class DownloadThread extends Thread{
	Connection conn;
	CyclicBarrier barrier;
	int startPos;
	int endPos;
	String path = "";
	int step = 1024*200; // 每200k写一次文件
	public DownloadThread(CyclicBarrier _barrier, Connection conn, int startPos, int endPos,String filepath){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.path = filepath;
		this.barrier = _barrier;
	}
	public void run(){	
		// 获取下载的字节数组，写入文件，注意这是一个多线程程序。
		// 这个函数只会写一部分的文件
		
		// 从connect那里获取字节数组，如果没有字节数组了，就表示这部分下载完成
		// 向filepath写文件
		if(conn == null) return;
		int curEndPos = startPos;
		while(curEndPos<endPos){
			startPos = curEndPos;
			curEndPos += step;
			if (curEndPos > endPos) 
				curEndPos = endPos;
			try {
				byte[] data = conn.read(startPos, curEndPos);
				RandomAccessFile files = new RandomAccessFile(path,"rw");
				files.seek(startPos);
				files.write(data);
				files.close();
				System.out.println("startPos"+startPos + ", length:"+data.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		conn.close();
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
