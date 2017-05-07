 package com.github.ipk2015.coding2017.coderising.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.ipk2015.coding2017.coderising.download.api.Connection;
import com.github.ipk2015.coding2017.coderising.download.api.ConnectionException;
import com.github.ipk2015.coding2017.coderising.download.api.ConnectionManager;
import com.github.ipk2015.coding2017.coderising.download.api.DownloadListener;
import com.github.ipk2015.coding2017.coderising.download.impl.ConnectionImpl;
import com.github.ipk2015.coding2017.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloader {
	
	String url;
	int threadCount=1;
	DownloadListener listener;
	
	ConnectionManager cm;
	

	public FileDownloader(String _url) {
		this.url = _url;
	}
	public FileDownloader(String _url,int _threadCount){
		this.url = _url;
		this.threadCount=_threadCount;
		ConnectionImpl.threadCount=_threadCount;
	}
	public void execute() throws Exception{
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
		Connection conn = this.cm.open(url);
		int length=conn.getContentLength();
		if(length<=0){
			System.out.println("获取下载文件长度失败");
			return;
		}
		int lastIndexOf = url.lastIndexOf("/");
		RandomAccessFile raf = new RandomAccessFile(url.substring(lastIndexOf+1), "rwd");
		raf.setLength(length);
		for(int threadId=0; threadId<threadCount; threadId++){
			int blockSize = length/threadCount;
			int startIndex = threadId*blockSize;
			
			// 下次下载时,读取临时文件,得到上次下载到的位置,接着这个位置继续下载
			File file = new File(threadId+".txt");
			if(file.exists() && file.length() > 0){
				FileInputStream fileInputStream =new FileInputStream(file);
				InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String content = bufferedReader.readLine();
				int currentPosition = Integer.parseInt(content);
				startIndex = currentPosition;
			}
			
			int endIndex = (threadId+1)*blockSize-1;
			if(threadId == threadCount-1){
				endIndex = length-1;
			}
			Connection connection = cm.open(url);
			new DownloadThread(connection,startIndex,endIndex,threadId,listener).start();
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
