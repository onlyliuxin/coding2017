package com.louis.download;

import java.io.File;
import java.util.concurrent.CyclicBarrier;

import com.louis.download.api.LYConnection;
import com.louis.download.api.LYConnectionException;
import com.louis.download.api.LYConnectionManager;
import com.louis.download.api.LYDownloadListener;

public class LYFileDownloader {

	String url;
	
	LYDownloadListener listener;
	
	LYConnectionManager cm;
	
	private static final int THREAD_NUM = 3;

	public LYFileDownloader(String _url) {
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
//		LYConnection conn = null;
//		try {
//			
//			conn = cm.open(this.url);
//			
//			int length = conn.getContentLength();	
//			
//			new LYDownloadThread(conn,0,length-1).start();
//			
//		} catch (LYConnectionException e) {			
//			e.printStackTrace();
//		}finally{
//			if(conn != null){
//				conn.close();
//			}
//		}
		
		CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM, new Runnable() {

			@Override
			public void run() {
				listener.notifyFinished();
			}
		});
		
		LYConnection conn = null;
		try {

			conn = cm.open(this.url);

			int length = conn.getContentLength();
			System.out.println("length:"+length);
			String filePath = getFileName(this.url);
			File file = new File(filePath);
			
			int blockSize = (length % THREAD_NUM == 0 ) ? length / THREAD_NUM : (length / THREAD_NUM + 1);
			for (int i = 0; i < THREAD_NUM; i++) {
				int startPos = i * blockSize;
				int endPos = (i+1) * blockSize - 1;
				if(endPos > length - 1){
					endPos = length - 1;
				}
//				new LYDownloadThread(barrier , conn, startPos, endPos , file).start();

			}

		} catch (LYConnectionException e) {			
			System.out.println(e.getMessage());
		} finally{
			if(conn != null){
				conn.close();
			}
		}
	}
	
	public void setListener(LYDownloadListener listener) {
		this.listener = listener;
	}

	public LYDownloadListener getListener(){
		return this.listener;
	}
	
	public void setConnectionManager(LYConnectionManager ucm){
		this.cm = ucm;
	}
	
	private String getFileName(String url) {
		int index = url.lastIndexOf("/");
		return url.substring(index + 1 , url.length());
	}
}
