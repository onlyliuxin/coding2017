package com.coding.week3.download;


import com.coding.week3.download.api.Connection;
import com.coding.week3.download.api.ConnectionException;
import com.coding.week3.download.api.ConnectionManager;
import com.coding.week3.download.api.DownloadListener;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;

public class FileDownloader {

	String url;

	DownloadListener listener;

	ConnectionManager cm;

	String savePath;

	int nThread;

	int fileLength;

	AtomicInteger downloadBytesCount;

	public FileDownloader(String _url) {
		this.url = _url;
	}

	public FileDownloader(int nThread, String savePath, String url) {
		this.nThread = nThread;
		this.savePath = savePath;
		this.url = url;
		downloadBytesCount = new AtomicInteger(0);
	}

	public void execute() throws IOException {
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
		Connection conn = null;
		try {

			//根据文件长度为每个线程分配下载字节数量
			conn = cm.open(this.url);
			fileLength = conn.getContentLength();
			//已读字节数量

            int perLenOfThread ;
            int lastLen ;
			Thread[] threads = new Thread[nThread];
            if ( fileLength % nThread == 0) {
                perLenOfThread = fileLength / nThread;
            } else {
                lastLen = fileLength % nThread;
                perLenOfThread = (fileLength + (nThread - lastLen)) / nThread;
            }

			//启动线程
			for (int i = 0; i < nThread; i++) {
				Connection conn1 = cm.open(this.url);
				RandomAccessFile ras = new RandomAccessFile(savePath, "rw");
                if ( i < nThread - 1) {
					threads[i] = new DownloadThread(conn1, perLenOfThread * i, perLenOfThread * (i + 1)-1, ras, downloadBytesCount);
					threads[i].start();
				} else {
					threads[i] =  new DownloadThread(conn1, perLenOfThread * (nThread - 1), fileLength - 1, ras, downloadBytesCount);
					threads[i].start();
				}
            }

			shutdown(threads);

		} catch (ConnectionException e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				conn.close();
			}
		}
	}

	private void shutdown(Thread[] threads){
		while (true) {
			boolean allTerminated  = true;
			for (int i = 0; i < nThread; i++) {
				allTerminated = allTerminated & threads[i].getState().equals(Thread.State.TERMINATED);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				System.out.println(threads[i].getName() + "状态："+threads[i].getState().toString());
			}

			System.out.println("已下载："+(downloadBytesCount.get()/1024)+"K,百分比："+ (downloadBytesCount.get()/ (fileLength/100))+"%" );
			if (allTerminated) {
				listener.notifyFinished();
				break;
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
