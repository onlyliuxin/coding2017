package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	

	public FileDownloader(String _url) {
		this.url = _url;
		
	}
	
	public void execute(){
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
		
		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		//写入文件
		//得到文件名
		String fileName = "E:\\zhuomian\\java课程\\testFile.jpg";
		//根据文件大小及文件名，创建一个同样大小，同样文件名的文件
		File file = new File(fileName);
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn1 = null;
		try {
			CountDownLatch countdownlatch = new CountDownLatch(3);

			conn1 = cm.open(this.url);
			Connection conn4 = cm.open(this.url);
			int length = conn4.getContentLength();
			try {
				raf.setLength(length); //设置文件长度 一系列的占位符
			} catch (IOException e) {
				e.printStackTrace();
			}
			new DownloadThread(conn1, 0, length / 3 - 1, countdownlatch, raf).start();
			Connection conn2 = cm.open(this.url);
			new DownloadThread(conn2, length / 3, (length / 3) *2 - 1, countdownlatch, raf).start();
			Connection conn3 = cm.open(this.url);
			new DownloadThread(conn3, (length / 3) *2 , length  - 1, countdownlatch, raf).start();


			try {
				countdownlatch.await();
				this.listener.notifyFinished();
				conn4.close();
				conn1.close();
				conn2.close();
				conn3.close();
				try {
					if (raf != null) {
						raf.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//this.listener.notifyFinished();
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn1 != null){
				conn1.close();
			}
			if (raf != null)
			{
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
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
