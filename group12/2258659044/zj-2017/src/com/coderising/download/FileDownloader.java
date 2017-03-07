package com.coderising.download;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	/*线程名称前缀*/
	private final String threadName = "thread";

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
		
		
		Connection conn = null;
		try {
			
			conn = cm.open(this.url);			
			int threadCount = 3;
			int length = conn.getContentLength();
			//存放下载线程名称
			List<String> names = new ArrayList<String>();
			int blockSize = length / threadCount;			
			for (int thread = 1; thread <= threadCount; thread++) {
				
                int startIndex = (thread - 1) * blockSize;  
                int endIndex = thread * blockSize - 1;  
                if (thread == threadCount) {//最后一个线程下载的长度要稍微长一点  
                    endIndex = length;  
                }  
                System.out.println("线程："+thread+"下载:---"+startIndex+"--->"+endIndex);  
                Thread thr= new DownloadThread(conn,startIndex,endIndex);
                //线程名称组成：thread+编号
                thr.setName(threadName+thread);
                thr.start();
                names.add(threadName+thread);
            }
			//判断所有线程是否下载完成
			if(DownloadThreadsIsComplete(names)){
				listener.notifyFinished();
			}
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
		
		
		
	}
	
	/**
	 * 判断所有下载线程是否执行完
	 * @return
	 */
	private boolean DownloadThreadsIsComplete(List<String> threadNames){
		
		Map<Thread, StackTraceElement[]> threadMaps=Thread.getAllStackTraces();
		Iterator<Thread> it = threadMaps.keySet().iterator();
		while(it.hasNext()){
			Thread thread = it.next();
		    return !threadNames.contains(thread.getName());
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
