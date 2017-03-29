package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coding.basic.Stack;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	// Use stack to check if all threads are finished
	// When a thread is started, an empty Object will be pushed in this stack;
	// when a thread is finished, an Object will be popped out.
	private Stack mStack;
	
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
		
		Connection conn = null;
		try {
			int length = 0;	
			
			int threadNum = 3;
			mStack = new Stack();
			
			DownloadThread.DownloadThreadListener dtListener = new DownloadThread.DownloadThreadListener() {
				
				@Override
				public void onFinished(Connection conn, int tag, boolean succeeded) {
					System.out.println("Thread with tag " + tag + (succeeded?" succeeded":" failed") + " to download");
					mStack.pop();
					if (conn != null) {
						conn.close();
					}
					if (mStack.isEmpty()) {
						listener.notifyFinished();
					}
				}
			};
			
			for (int i = 0; i < threadNum; i++) {
				conn = cm.open(this.url);
				if (length == 0) {
					length = conn.getContentLength();
				}
				mStack.push(new Object());
				DownloadThread dt = new DownloadThread(conn,length*i/threadNum,length*(i+1)/threadNum - 1);
				dt.setTagAndListener(i, dtListener);
				dt.start();
				System.out.println("DownloadThread with tag " + i + " created and started");
			}
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		} finally{
//			if(conn != null){
//				conn.close();
//			}
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
