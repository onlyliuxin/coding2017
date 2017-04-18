package org.wsc.coderising.download;

import java.util.concurrent.atomic.AtomicInteger;

import org.wsc.coderising.download.api.Connection;
import org.wsc.coderising.download.api.ConnectionException;
import org.wsc.coderising.download.api.ConnectionManager;
import org.wsc.coderising.download.api.DownloadListener;

/**
 * 文件下载器
 *
 * @author Administrator
 * @date 2017年3月6日下午7:04:44
 * @version v1.0
 *
 */
public class FileDownloader {
	private final static int THREAD_NUM = 10;
	
	private String url;
	
	private DownloadListener listener;
	
	private ConnectionManager cm;
	
	private AtomicInteger atomicInteger = new AtomicInteger();

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
			int length = cm.getContentLength(url);
			int perThred_length = length/THREAD_NUM;
			int redundant  = length%THREAD_NUM;
			for (int i = 0; i < THREAD_NUM; i++) {
				int startPos = i*perThred_length;
				int endPos = (i+1)*perThred_length-1;
				if(i == THREAD_NUM -1)//最后一个线程
					endPos+=redundant;
				Connection conn = cm.open(this.url);
				atomicInteger.getAndIncrement();
				new DownloadThread(conn,startPos,endPos,new DownloadListener() {
					@Override
					public void notifyFinished() {
						if(atomicInteger.decrementAndGet()==0)
							listener.notifyFinished();
					}
				}).start();
			}
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
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
