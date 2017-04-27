package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

public class FileDownloader {

	private final static String EXT = "lyj";
	private static DownloadThread[] threadPool;
	private int finishedCount;
	public String downloadLocation;

	String url;
	ConnectionManager cm;
	DownloadListener listener;
	private static String fileName;
	private static String tempName;

	public FileDownloader(String _url) {
		this.url = _url;
		this.finishedCount = 0;
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

		long start = getCurrentTime();
		try {
			Connection conn = cm.open(this.url);
			int length = conn.getContentLength();

			System.out.println("file length:" + length);
			setLocation("C:\\");

			String name = conn.getFileName();
			setFileName(name);
			setTempName(name);
			checkLength(length, conn);

			DownloadUtil.createTempFile(tempName, length);

			int connNumbers = DownloadUtil.calculateConnects(length);
			System.out.println(connNumbers + " Threads will be created.");

			threadPool = new DownloadThread[connNumbers];
			setAndStartThreadPool(conn, threadPool, length);

			checkFinish(threadPool.length);

			listener.notifyFinished();
			DownloadUtil.rename(tempName, fileName);
			long end = getCurrentTime();
			DownloadUtil.printDownloadReport(length, start, end);

		} catch (ConnectionException e) {
			e.printStackTrace();
		} finally {
			freeDownloadThread();
		}
	}

	private void checkLength(int length, Connection conn) {
		if (length <= 0) {
			try {
				throw new ConnectionException("file does not exist");
			} catch (ConnectionException e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
		}
	}

	private void setTempName(String name) {
		String temp = name.substring(0, name.lastIndexOf(".") + 1) + EXT;
		FileDownloader.tempName = downloadLocation + temp;
	}

	private void setFileName(String name) {
		FileDownloader.fileName = downloadLocation + name;
	}

	private void setLocation(String downloadLocation) {
		this.downloadLocation = downloadLocation;
	}

	private boolean checkFinish(int links) {

		while (finishedCount != links) {
			try {
				Thread.sleep(5000);
				System.out.println("Unfinshed threads number: " + (links - finishedCount));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private void freeDownloadThread() {
		if (threadPool != null) {
			for (int i = 0; i < threadPool.length; i++) {
				if (threadPool[i] != null)
					threadPool[i].close();
			}
		}
	}

	private long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public String getDownloadLocation() {
		return downloadLocation;
	}

	public DownloadListener getListener() {
		return this.listener;
	}

	private void setAndStartThreadPool(Connection conn, DownloadThread[] threadPool, int length)
			throws ConnectionException {
		int connectionNumbers = threadPool.length;
		int batch_size = length / connectionNumbers;
		int beginPos = 0;
		int endPos = batch_size;
		threadPool[0] = new DownloadThread(conn, beginPos, endPos);
		setAndStartThread(threadPool[0], tempName);
		for (int i = 1; i < connectionNumbers; i++) {
			beginPos = endPos + 1;
			endPos = beginPos + batch_size;
			Connection con = cm.open(this.url);

			if (i == connectionNumbers - 1) {
				endPos = length - 1;
			}
			threadPool[i] = new DownloadThread(con, beginPos, endPos);
			setAndStartThread(threadPool[i], tempName);

		}
	}

	private void setAndStartThread(DownloadThread downloadThread, String dest) {
		downloadThread.setDest(dest);
		downloadThread.setFileDownloader(this);
		downloadThread.start();
	}

	public void setConnectionManager(ConnectionManager ucm) {
		this.cm = ucm;
	}

	public void setThreadFinished() {
		finishedCount++;
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

}
