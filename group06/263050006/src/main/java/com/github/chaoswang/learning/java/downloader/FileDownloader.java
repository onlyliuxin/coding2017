package com.github.chaoswang.learning.java.downloader;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.github.chaoswang.learning.java.downloader.api.Connection;
import com.github.chaoswang.learning.java.downloader.api.ConnectionException;
import com.github.chaoswang.learning.java.downloader.api.ConnectionManager;
import com.github.chaoswang.learning.java.downloader.api.DownloadListener;

public class FileDownloader {
	String url;
	DownloadListener listener;
	ConnectionManager cm;
	int threadNum = 10;
	
	public FileDownloader(String _url, int threadNum) {
		this.url = _url;
		this.threadNum = threadNum;
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
		// 参考：http://blog.csdn.net/yan8024/article/details/46474239
		
		long startTime = System.currentTimeMillis();
		//判断所有线程是否运行完毕
		ArrayList<Thread> list = new ArrayList<Thread>();
		
		try{
			for(int i=1; i<=threadNum; i++){
				Connection conn = cm.open(url);
				Thread dt = new DownloadThread(conn, threadNum, i);
				dt.start();
				list.add(dt);
			}
		}catch(ConnectionException e){
			e.printStackTrace();
			return;
		}
		
		while(true){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			if(!isAllFinished(list)){
				continue;
			}
			System.out.println("finished, cost time:" + (System.currentTimeMillis() - startTime));
			listener.notifyFinished();
			break;
		}
	}
	
	private boolean isAllFinished(ArrayList<Thread> list){
		for(Thread t : list){
			if(t.getState() != Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
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
