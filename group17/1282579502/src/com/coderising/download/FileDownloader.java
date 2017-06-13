package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.concurrent.Semaphore;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	RandomAccessFile targetFile = null;
	
	Semaphore fileLock = null;
	
	int numberOfThreads = 4;
	int finishedCount = 0;
	Semaphore finishCountLock = null;

	public FileDownloader(String _url) {
		this.url = _url;
		String[] tokens = _url.split("/");
		String targetFileName = tokens[tokens.length -1];
		String currentPath = new File("").getAbsolutePath();
		File file = new File(currentPath +"/"+targetFileName);
		try {
			targetFile = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fileLock = new Semaphore(1, false);
		finishCountLock = new Semaphore(1, false);
		
	}
	
//	public void execute(){
//		// 在这里实现你的代码， 注意： 需要用多线程实现下载
//		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
//		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
//		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
//		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
//		// 具体的实现思路：
//		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
//		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
//		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
//		// 3. 把byte数组写入到文件中
//		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
//		
//		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
//		Connection conn = null;
//		try {
//			
//			conn = cm.open(this.url);
//			
//			int length = conn.getContentLength();	
//			DownloadThread dt = new DownloadThread(conn,0,length-1);
//			
//			dt.start();
//
//		} catch (ConnectionException e) {			
//			//e.printStackTrace();
//			System.err.println(e.getMessage());
//			
//		}finally{
//			if(conn != null){
//				conn.close();
//			}
//		}
//	}
	public void execute(){
		Connection c = null;
		try{
			c = cm.open(url);
			int length = c.getContentLength();
			if(length >=numberOfThreads){
				DownloadThread[] threads = constMultiDownloadThreads(length, this.numberOfThreads, url);
				for(DownloadThread thread : threads){
					thread.start();
				}
			}
		}
		catch(ConnectionException e){
			e.printStackTrace();
		}
	}
	//for example:
	/*
	 * if total length is 11; 11/3 = 3;
	 * thread 1: 0->3;
	 * thread 2; 4->7;
	 * thread 3; 8->(11-1) = 8->10;
	 * 
	 * if total length is 12: 12/3 =4;
	 * thread 1: 0->4;
	 * thread 2: 5->9;
	 * thread 3: 10->14 which is not fit in. need to adjust last token;
	 * thread 3: 10->(12-1) = 10->11;
	 */
	private DownloadThread[] constMultiDownloadThreads(int length, int numberOfThreads, String url){
		DownloadThread[] threads = new DownloadThread[numberOfThreads];
		int startPos = 0; 
		int endPos = 0;
		int patition = length /numberOfThreads;
		try{
			for(int i = 0; i<numberOfThreads-1; i++){
				Connection conn = cm.open(url);
				endPos = startPos + patition;
				threads[i] = new DownloadThread(conn, startPos, endPos);
				threads[i].setCallBack(this);
				startPos= endPos +1;
			}
			Connection conn = cm.open(url);
			endPos = length-1;
			threads[numberOfThreads-1] = new DownloadThread(conn, startPos, endPos);
			threads[numberOfThreads-1].setCallBack(this);
		}
		catch(ConnectionException e){
			e.printStackTrace();
		}
		
		return threads;
	}
	
	public void reportDownloadFinished(byte[] data, int offset){
		if(data == null) {
			System.err.println("data is null: should report listener notify failed.");
			//listener.notifyFailed();
		}
		
		System.out.println("data recieved start from byte:" + offset + " to byte:" + (offset + data.length -1));
		finishedCount ++;
		if(finishedCount>=numberOfThreads){
		listener.notifyFinished();
		}
	}
	
	public RandomAccessFile getTargetFile(){
		return targetFile;
	}
	
	public void acquireFilePermit(){
		try {
			fileLock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void releaseFilePermit(){
		fileLock.release();
	}
	
	public void acquireFinishCounterPermit(){
		try {
			finishCountLock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void releaseFinishCounterPermit(){
		finishCountLock.release();
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
