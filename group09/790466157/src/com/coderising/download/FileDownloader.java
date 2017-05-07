package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FileDownloader {
	
	private String url;
	private String fileName;
	private DownloadListener listener;
	private ConnectionManager cm;
	private int threadNum = 5;
	private int length = 0;
	private Connection conn;
	

	public FileDownloader(String _url, String _fileName) {
		this.url = _url;
		this.fileName = _fileName;
		
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

        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rwd")) {
            conn = cm.open(this.url);
            length = conn.getContentLength();
            raf.setLength(length);
            threadPoolDownload();
//            oneThreadDownload();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }

    }

	public void oneThreadDownload() {
        final CyclicBarrier barrier = new CyclicBarrier(1 ,new Runnable() {
            @Override
            public void run() {
                getListener().notifyFinished();
            }
        });
        try {
            Thread thread = new DownloadThread("oneThread", conn,0,length, fileName, barrier);
            thread.start();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void threadPoolDownload() throws ConnectionException {
        final CyclicBarrier barrier = new CyclicBarrier(threadNum ,new Runnable() {
            @Override
            public void run() {
                getListener().notifyFinished();  // 栅栏
            }
        });
        ExecutorService threadPool = Executors.newCachedThreadPool();
        int len = conn.getContentLength();
        for(int i = 0; i< threadNum; i++)
        {
            int start=i*len/ threadNum;
            int end = (i+1)*len/ threadNum -1;
            conn = cm.open(this.url);
            if(i== threadNum -1)
            {
                end =len;
            }
            Thread thread = new DownloadThread("thread"+i, conn, start, end, fileName, barrier);
            threadPool.execute(thread);
        }
        if (conn != null) {
            conn.close();
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
