package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionImpl;
import com.coderising.download.impl.ConnectionManagerImpl;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;

public class DownloadThread extends Thread{
	int startPos;
	int endPos;
	String url;
	String savePath;
	DownloadListener listener;
	private static int count = 0;
	private Object lock = new Object();  //对象锁

	public DownloadThread(String url, String savePath, DownloadListener listener, int startPos, int endPos){
		this.startPos = startPos;
		this.endPos = endPos;
		this.url = url;
		this.savePath = savePath;
		this.listener = listener;
	}
	public void run(){
		RandomAccessFile raf = null;
		//实现
		try {
			Connection conn = new ConnectionManagerImpl().open(url);
			raf = new RandomAccessFile(savePath,"rwd");

			byte[] data= conn.read(startPos,endPos);

			raf.seek(startPos);
			raf.write(data);
			synchronized (lock){  //加对象锁
				count ++;
				if(count == 3){
					listener.notifyFinished();
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("读取错误");
		}finally {
			try {
				if(raf != null){
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
