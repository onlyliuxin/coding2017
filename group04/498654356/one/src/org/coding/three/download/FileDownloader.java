package org.coding.three.download;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import org.coding.three.download.api.Connection;
import org.coding.three.download.api.ConnectionManager;
import org.coding.three.download.api.DownloadListener;

public class FileDownloader {
	
	String url;
	String destPath;
	private int threadCount;
	
	DownloadListener listener;
	ConnectionManager cm;
	
	public FileDownloader(String _url, String destPath, int threadCount) {
		this.url = _url;
		this.destPath = destPath;
		this.threadCount = threadCount;
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
		Connection conn = null;
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();
			createDownloadFile(length);
			int[][] threadData = allocationData(length);
			CyclicBarrier barrier = new CyclicBarrier(this.threadCount, new Runnable() {
				@Override
				public void run() {
					listener.notifyFinished();
				}
			});
			// TODO
			for(int i = 0; i < this.threadCount; i++) {
				new DownloadThread(barrier, conn, threadData[i][0], threadData[i][1], this.destPath).start();
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
	}
	
	private int[][] allocationData(int length) {
		int[][] threadData = new int[this.threadCount][2];
		int size = length / this.threadCount;
		int offset = length % this.threadCount;
		for(int i = 0; i < this.threadCount; i++) {
			int start = i * size;
			int end = (i + 1) * size -1;
			if(i + 1 == this.threadCount) {
				end += offset;
			}
			threadData[i][0] = start;
			threadData[i][1] = end;
			
		}
		return threadData;
	}


	private void createDownloadFile(int length) throws Exception {
		File file = new File(destPath);
		if(file.exists()) {
			file.delete();
		}
		RandomAccessFile raf = new RandomAccessFile(destPath, "rw");
		for(int i = 0; i < length; i++) {
			raf.writeByte(0);
		}
		raf.close();
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
 