package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	private static int DOWNLOAD_SIZE_PER_THREAD = 1024*1000;
	
	private String destFilePath;
	

	public FileDownloader(String _url) {
		this.url = _url;
		
	}
	
	public FileDownloader(String _url,String destFilePath) {
		this.url = _url;
		this.destFilePath = destFilePath;
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
			
			int length = conn.getContentLength();	
			System.out.println("GetLength ="+length);
			
			File dest = new File(destFilePath);
			dest.createNewFile();
			//fill the file with byte array to ensure the size
//			byte[] ba = new byte[length];
//			FileOutputStream fos = new FileOutputStream(dest);
//			fos.write(ba);
//			fos.flush();
//			fos.close();
			
			ArrayList<DownloadThread> threadPool = new ArrayList<DownloadThread>();
			int numOfThread = length/DOWNLOAD_SIZE_PER_THREAD;
			if(length%DOWNLOAD_SIZE_PER_THREAD!=0)
			{
				numOfThread++;
			}
			
			for(int i=0;i<numOfThread;i++){
				int startPos = i*DOWNLOAD_SIZE_PER_THREAD;
				int endPos = (i+1)*DOWNLOAD_SIZE_PER_THREAD-1;
				if(endPos>length-1)
				{
					endPos = length-1;
				}
				DownloadThread t = new DownloadThread(cm.open(url),startPos,endPos,destFilePath);
				t.setName("downloadThread_"+i);
				System.out.println("Initializing Thread:"+t.getName());
				threadPool.add(t);
				t.start();
			}
			//pooling
			boolean flag;
			do{
				Iterator<DownloadThread> it = threadPool.iterator();
				flag=true;
				DownloadThread t;
				while(it.hasNext()){
					t = it.next();
					System.out.println("Thread ="+t.getName()+" has completed?"+t.isFinished());
					flag = flag && t.isFinished();
				}
				if(!flag){
					System.out.println("=====================AllCompleted="+flag);
					Thread.sleep(10*1000);
					continue;
				}
			}while(!flag);
			
		} catch (Exception e) {			
			throw new RuntimeException("Error occured during download,",e);
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		getListener().notifyFinished();
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
