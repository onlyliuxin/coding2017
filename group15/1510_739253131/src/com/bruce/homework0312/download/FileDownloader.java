package com.bruce.homework0312.download;

import com.bruce.homework0312.download.api.Connection;
import com.bruce.homework0312.download.api.ConnectionException;
import com.bruce.homework0312.download.api.ConnectionManager;
import com.bruce.homework0312.download.api.DownloadListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;


public class FileDownloader {
	
	private String url;

	private String localFile;
	
	private DownloadListener listener;
	
	private ConnectionManager cm;
	
	private static final int DOWNLOAD_THREAD_COUNT = 3;

	public FileDownloader(String _url, String localFile) {
		this.url = _url;
		this.localFile = localFile;
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

		CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_THREAD_COUNT, new Runnable() {
			@Override
			public void run() {
				listener.notifyFinished();
			}
		});

		Connection conn = null;
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();
			createPlaceHolderFile(this.localFile, length);
			int[][] ranges = allocateDownloadRange(DOWNLOAD_THREAD_COUNT, length);
			for (int i = 0; i < DOWNLOAD_THREAD_COUNT; i++) {
				DownloadThread thread = new DownloadThread(conn, ranges[i][0], ranges[i][1], localFile, barrier);
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

	//创建一个大小为length的空文件，准备存放下载内容
	private void createPlaceHolderFile(String localFile, int length) throws IOException {
		RandomAccessFile file = new RandomAccessFile(localFile, "rw");
		for (int i = 0; i < length; i++) {
			file.write(0);
		}
		file.close();
	}

	//根据文件总长度和下载的线程数量，“切分”文件
	private int[][] allocateDownloadRange (int threadCount, int totalLength) {
		//二维数组第二维长度为2，用于存放startPos和endPo
		int[][] ranges = new int[threadCount][2];
		int perLen = totalLength / threadCount;
		int left = totalLength % threadCount;
		for (int i = 0; i < threadCount; i++) {
			ranges[i][0] = i * perLen;
			ranges[i][1] = (i+1) * perLen - 1;
			if (i == (threadCount-1)) {
				ranges[i][1] += left;
			}
		}
		return ranges;
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
