package com.pan.download;



import com.pan.download.api.Connection;
import com.pan.download.api.ConnectionManager;
import com.pan.download.api.DownloadListener;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

public class FileDownloader {
	
	private String url;
	private DownloadListener listener;
	private ConnectionManager cm;
	private static String LOCAL_FILE = "F:/xxx.jpg";

	private static final int DOWNLOAD_THREAD_NUM = 3;

	public FileDownloader(String url) {
		this.url = url;
	}
	// 当所有的Thread都调用 await方法时，会执行后面的 barrierAction,调用后面这个线程
	CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_THREAD_NUM,new Runnable() {
		@Override
		public void run() {
			listener.notifyFinished();
		}
	});


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
		Connection conn = null;
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();// 拿到将要下载文件的长度
			RandomAccessFile file = new RandomAccessFile(LOCAL_FILE,"rw");
			for(int i = 0; i < length; i++){
				file.write(0);
			}
			file.close();
			int [][] ranges = new int[DOWNLOAD_THREAD_NUM][2];
			int eachThreadSize = length / DOWNLOAD_THREAD_NUM;
			int left = length % DOWNLOAD_THREAD_NUM;
			for(int i = 0; i<DOWNLOAD_THREAD_NUM; i++){
				int startPos = i * eachThreadSize;
				int endPos = (i+1) * eachThreadSize - 1;
				if((i == (DOWNLOAD_THREAD_NUM -1))){
					endPos += left;
				}
				ranges[i][0] = startPos;
				ranges[i][1] = endPos;
			}
			System.out.println("开始下载文件");
			for(int i = 0; i < DOWNLOAD_THREAD_NUM; i++){
				DownloadThread thread = new DownloadThread(cm.open(url), ranges[i][0], ranges[i][1], LOCAL_FILE, barrier);
				thread.start();
				System.out.println("第" + (i+1) + "个线程已经启动");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
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
