package com.coding.week3.download1;


import com.coding.week3.download1.api.Connection;
import com.coding.week3.download1.api.ConnectionException;
import com.coding.week3.download1.api.ConnectionManager;
import com.coding.week3.download1.api.DownloadListener;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
		DownloadTask[] downloadTasks = new DownloadTask[nThread];
		try {

			//根据文件长度为每个线程分配下载字节数量
			conn = cm.open(this.url);
			fileLength = conn.getContentLength();
			//已读字节数量
			ExecutorService executorService = Executors.newFixedThreadPool(nThread);
//			int[][] allotSize = alloctSize(nThread, fileLength);
			int[][] allotSize = readPos(nThread, fileLength);
			for (int i = 0; i < nThread; i++) {
				RandomAccessFile ras = new RandomAccessFile(savePath, "rw");
				downloadTasks[i] = new DownloadTask(url, cm, allotSize[0][i],
						allotSize[1][i], ras , downloadBytesCount);
				executorService.execute(downloadTasks[i]);
			}
			//关闭线程池
			executorService.shutdown();
			boolean isTermination ;
			do {
				isTermination = executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
				if (fileLength > 0)
					System.out.println("已下载："+(downloadBytesCount.get()/1024)+"K,百分比："+ (downloadBytesCount.get()/ (fileLength/100))+"%" );
				//循环等待，直到线程全部关闭
			} while (!isTermination);
			System.out.println(111111);

		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			writePos(downloadTasks);
			if(conn != null){
				conn.close();
			}
		}
	}

	private int[][] alloctSize(int nThread, int fileLength){
		int[][] allotSize = new int[2][nThread];
		int perLenOfTask = fileLength / nThread;
		int lastLen = fileLength % nThread;
		for (int i = 0; i < nThread; i++) {
			int start = perLenOfTask * i;
			int end = perLenOfTask * (i + 1) - 1;
			if (i == nThread - 1) {
				end += lastLen;
			}
			allotSize[0][i] = start;
			allotSize[1][i] = end;
		}
		return allotSize;
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

	private void writePos(DownloadTask[] tasks){
		File file = new File("");
		File posFile = new File(file.getAbsolutePath()+"/tempPos");
		RandomAccessFile ras = null;
		try {
			ras = new RandomAccessFile(posFile, "rw");
			if (!posFile.exists())  {
				posFile.createNewFile();
			}
			for (int i = 0; i < tasks.length; i++) {
				ras.writeInt(tasks[i].getEachThreadDownloadBytesCount());
				ras.writeInt(tasks[i].endPos);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int[][] readPos(int nThread, int fileLength){
		File file = new File("");
		File posFile = new File(file.getAbsolutePath()+"/tempPos");
		if (!posFile.exists()) {
			return alloctSize(nThread, fileLength);
		}
		RandomAccessFile ras = null;
		int[][] pos = new int[2][nThread];
		try {
			ras = new RandomAccessFile(posFile, "r");
			ras.seek(0);
			for (int i = 0; i < nThread; i++) {
				pos[0][i]  = ras.readInt();
				pos[1][i]  = ras.readInt();
			}
			return pos;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pos;
	}
}
