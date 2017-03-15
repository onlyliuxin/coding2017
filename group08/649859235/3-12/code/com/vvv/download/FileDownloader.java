package com.vvv.download;

import com.vvv.download.api.Connection;
import com.vvv.download.api.ConnectionException;
import com.vvv.download.api.ConnectionManager;
import com.vvv.download.api.DownloadListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDownloader {
	public static final int THREAD_NUM = 5;
	private String fileName = "d:\\temp";
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String url;
	private DownloadListener listener;
	private ConnectionManager cm;
	
    private int downNum;
    
	public FileDownloader(String _url) {
		
		this.url = _url;		
	}
	
	public int getDownNum() {		
		return downNum;
	}

	public synchronized void addDownNum() {
		downNum++;
		System.out.println("..."+downNum);
		if (getDownNum() >= FileDownloader.THREAD_NUM) {
			if (listener != null) {
				listener.notifyFinished();
			}
		}
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
			setFileName("d:\\"+conn.getFileName());
			conn.close();
			startDownload(length, THREAD_NUM);	
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
			}
		}		
	}
	
	private void startDownload(int len, int threadNum){
		if(len<=0){
			listener.notifyFinished();
			return;
		}		
		 //在客户端本地创建出来一个大小跟服务器端一样大小的临时文件	
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(getFileName(), "rw");
			raf.setLength(len);		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int block = len/threadNum==0?len/threadNum:len/threadNum+1;		
		System.out.println("...startDownload len:"+len+",block "+block);
		for(int i=0; i<threadNum; i++){
			int start = i*block;
	        int end = start+(block-1);
	        try {
				new DownloadThread(cm.open(url), start, end, "downthread#"+i, this).start();
			} catch (ConnectionException e) {
				e.printStackTrace();
			}	  
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
