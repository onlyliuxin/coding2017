package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.DownloadThreadListener;

public class FileDownloader {

	String url;

	DownloadListener listener;

	ConnectionManager cm;

	public String downloadLocation = "C:\\";

	private final static String EXT = "lyj";
	private static int buffer_size = 1024;

	private int finishedCount;
	public void setFinished() {
		finishedCount++;
	}

	private static final int MIN_CONNECTIONS = 2;
	private static final int MAX_CONNECTIONS = 4;

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
		Connection conn = null;
		DownloadThread[] threadPool = null;
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();
			if (length <= 0) {
				try {
					throw new ConnectionException("file does not exist");
				} catch (ConnectionException e) {
					e.printStackTrace();
					return;
				} finally {
					conn.close();
				}
			}
			System.out.println("length:"+length);
			String fileName = conn.getFileName();
			String tempName = createTempName(downloadLocation+fileName);
			createTempFile(tempName, length);
			int connNum = calculateConnects(length);
			System.out.println(connNum + " Threads will be created.");
			threadPool = new DownloadThread[connNum];
			System.out.println(connNum);
			int batch_size = length / connNum;
			int beginPos = 0;
			int endPos = batch_size;
			threadPool[0] = new DownloadThread(conn, beginPos, endPos);
			threadPool[0].setDest(tempName);
			threadPool[0].setFileDownloader(this);
			threadPool[0].start();
			for (int i = 1; i < connNum; i++) {
				beginPos = endPos + 1;
				endPos = beginPos + batch_size;
				if (i == connNum - 1) {
					endPos = length - 1;
				}
				Connection con = cm.open(this.url);
				DownloadThread dt = new DownloadThread(con, beginPos, endPos);
				dt.setDest(tempName);
				dt.setFileDownloader(this);
				threadPool[i] = dt;
				dt.start();
			}
			checkFinish(threadPool.length);
			listener.notifyFinished();
			changeName(tempName, fileName);
		} catch (ConnectionException e) {
			e.printStackTrace();
		} finally {
			if (threadPool != null) {
				for (int i = 0; i < threadPool.length; i++) {
					if (threadPool[i] != null)
						threadPool[i].close();
				}
			}
		}

	}
	private void checkFinish(int links) {

		while(finishedCount != links) {
			try {
				System.out.println( "Unfinshed threads number: " + (links - finishedCount));
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}	
	}

	private static String createTempName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf('.') + 1) + EXT;
	}

	private int calculateConnects(int length) {
		int conns = length / 1024 / 1024;
		if (conns < MIN_CONNECTIONS) {
			return MIN_CONNECTIONS;
		} else if (conns > MAX_CONNECTIONS) {
			return MAX_CONNECTIONS;
		} else {
			return conns;
		}
	}

	private void changeName(String from, String to) {
		rename(from, downloadLocation + to);
	}

	public boolean rename(String from, String to) {
		System.out.println(from);
		System.out.println(to);
		File file = new File(from);
		if (file.exists()) {
			file.renameTo(new File(to));
			System.out.println("rename success");
			return true;
		}
		System.out.println("rename failed");
		return false;
	}

	private static void createTempFile(String dest, int len) {
		File file = new File(dest);
		if (file.exists()) {
			System.out.println("tempfile already created");
			return;
		}
		FileOutputStream temp = null;
		try {
			temp = new FileOutputStream(dest);
			int length = len;
			byte[] buffer = new byte[1024];
			long times = length / 1024;
			int left = (int) (length % 1024);
			for (int i = 0; i < times; i++) {
				temp.write(buffer);
			}
			temp.write(buffer, 0, left);
			System.out.println("tempFile "+ dest + " created");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				temp.flush();
				temp.flush();
			} catch (IOException e) {
				e.printStackTrace();
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

	public String getDownloadLocation() {
		return downloadLocation;
	}

	public void setDownloadLocation(String downloadLocation) {
		this.downloadLocation = downloadLocation;
	}

}
