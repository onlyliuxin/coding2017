package com.multiThreadDownload;

import com.multiThreadDownload.api.Connection;
import com.multiThreadDownload.api.ConnectionException;
import com.multiThreadDownload.api.ConnectionManager;
import com.multiThreadDownload.api.DownloadListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.concurrent.CyclicBarrier;
public class FileDownloader {
	
	boolean isFinished = false;
	
	String url;

	DownloadListener listener;

	ConnectionManager cm;
	
	private static final int THREAD_NUM = 3;

	public FileDownloader(String _url) {
		this.url = _url;

	}

	public void execute() {
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos,
		// endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		// 线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接，
		// 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载， 注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法

		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		
		CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM,new Runnable(){

			@Override
			public void run() {
				listener.notifyFinished();
			}
			
		});
		
		Connection conn = null;
		try {
			conn = cm.open(url);
			
			int length = conn.getContentLength();
			System.out.println("----文件总长度---- :" + length);
			RandomAccessFile raf = new RandomAccessFile("yunpan.exe","rwd");
			
			raf.setLength(length);
			
			int block = length / THREAD_NUM;
			
			for(int threadId = 0; threadId < THREAD_NUM; threadId++){
				int startPos = (threadId) * block;
				int endPos = (threadId + 1 ) * block -1;
				if(threadId-1 == THREAD_NUM){
					endPos = length;
				}
				System.out.println("---threadId--- :" + threadId +
						   "---startIndex---" + startPos +
						   "---endIndex---" + endPos);
				//开启 线程
				URL u = new URL(url);
				new DownloadThread(barrier,cm.open(url),threadId,startPos,endPos).start();
			}
			
			
		} catch (ConnectionException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}

	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm) {
		this.cm = ucm;
	}

	public DownloadListener getListener() {
		return this.listener;
	}

	
}
