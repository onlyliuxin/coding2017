package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.UUID;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {
	
	private String url;
	private String localPath = "";
	private DownloadListener listener;
	private ConnectionManager cm;
	

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
		try {
			
			int length = cm.open(url).getContentLength();	
			RandomAccessFile tempFile = CreateTempFile(localPath,cm.open(url));
			
			int step = length / 4;
			DownloadThread downloadThread0 = new DownloadThread(cm.open(this.url,0,step-1),tempFile,"downLoad_Thread0");
			DownloadThread downloadThread1 = new DownloadThread(cm.open(this.url,step,2*step-1),tempFile,"downLoad_Thread1");
			DownloadThread downloadThread2 = new DownloadThread(cm.open(this.url,2*step,3*step-1),tempFile,"downLoad_Thread2");
			DownloadThread downloadThread3 = new DownloadThread(cm.open(this.url,3*step,length),tempFile,"downLoad_Thread3");
			downloadThread0.start();
			downloadThread1.start();
			downloadThread2.start();
			downloadThread3.start();
			
			while(true) {
				if (!(downloadThread0.isAlive()||downloadThread1.isAlive()||downloadThread2.isAlive()||downloadThread3.isAlive())) {
					tempFile.close();
					this.listener.notifyFinished();
					break;
				}
			}
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private RandomAccessFile CreateTempFile(String path,Connection _conn) {
		String tempFileName = UUID.randomUUID().toString() + ".jpg";
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(path + File.separator +tempFileName,"rw");
			randomAccessFile.setLength(_conn.getContentLength());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return randomAccessFile;
	}
	
	private boolean ChangeFileName(File _f,Connection _conn) {
		String fileName = _conn.getURL().getFile().substring(url.lastIndexOf("/"));
		return _f.renameTo(new File(_f.getAbsolutePath()+File.separator+fileName));
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
	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
}
