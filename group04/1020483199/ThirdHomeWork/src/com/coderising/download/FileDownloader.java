package com.coderising.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import org.junit.internal.runners.statements.RunAfters;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {
	
	private String url;
	
	private String localFile;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	private final static int thread_count = 3;
	
	public FileDownloader(String _url,String localFile) {
		this.url = _url;
		this.localFile = localFile;
	}
	
	public void execute(){
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，
		//通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 
		//调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 
		//这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接，
		//然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 
		//返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
		
		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		CyclicBarrier barrier = new CyclicBarrier(thread_count, new Runnable() {
			
			@Override
			public void run() {
				listener.notifyFinished();
			}
		});
		Connection conn = null;
		try {
			
			conn = cm.open(this.url);
			
			int length = conn.getContentLength();	
			
			createPlaceHolderFile(this.localFile,length);
			
			int[] [] ranges = allowcateDownloadRange(thread_count,length);
			
			for(int i = 0; i < thread_count; i++){
				
				DownloadThread thread= new DownloadThread(conn, ranges[i][0], ranges[i][1], localFile, barrier);
				
				thread.start();
				
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
			
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
		
		
		
	}
	
	private int[][] allowcateDownloadRange(int threadCount, int length) {
		int[][] ranges = new int[thread_count][2];
		
		int eachThreadSize = length / threadCount;
		
		int left = length % threadCount;
		
		for(int i = 0 ; i < thread_count;i++){
			int startPos = i * eachThreadSize;
			
			int endPos = (i+1) * eachThreadSize - 1;
			
			if((i==thread_count -1)){
				endPos += left;
			}
			
			ranges[i][0] = startPos;
			ranges[i][1] = endPos;
		}
		
		return ranges;
	}

	private void createPlaceHolderFile(String localfile, int length) throws IOException {
		RandomAccessFile file = new RandomAccessFile(localfile, "rw");
		for(int i = 0;i < length ; i++){
			file.write(0);
		}
		file.close();
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	
	
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
	
}
