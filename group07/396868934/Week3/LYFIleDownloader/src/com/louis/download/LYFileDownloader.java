package com.louis.download;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.louis.download.api.LYConnectionCallBack;
import com.louis.download.api.LYConnection;
import com.louis.download.api.LYConnectionException;
import com.louis.download.api.LYConnectionManager;
import com.louis.download.api.LYDownloadListener;

public class LYFileDownloader {

	String url;
	LYDownloadListener listener;
	LYConnectionManager cm;
	int threadNum;

	public LYFileDownloader(String _url, LYConnectionManager ucm, LYDownloadListener listener) {
		this.url = _url;
		this.cm = ucm;
		this.listener = listener;
		this.threadNum = 3;
	}
	
	public void execute(){
		CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
			@Override
			public void run() {
				listener.notifyFinished();
			}
		});
		
		LYConnection connection = null;
		try {

			connection = cm.open(this.url);

			int length = connection.getContentLength();
			System.out.println("length:" + length);

			int blockSize = (length % threadNum == 0 ) ? length / threadNum : (length / threadNum + 1);
			for (int i = 0; i < threadNum; i++) {
				int startPos = i * blockSize;
				int endPos = (i+1) * blockSize - 1;
				if (endPos > length - 1) {
					endPos = length - 1;
				}
				
				new LYDownloadThread(connection, startPos, endPos, new LYConnectionCallBack() {
					
					@Override
					public void excute(byte[] buffer) {
						try {
							int index = url.lastIndexOf("/");
							String filePath = url.substring(index + 1 , url.length());
							File file = new File(filePath);
							RandomAccessFile rafile = new RandomAccessFile(file, "rw");
							rafile.seek(startPos);
							rafile.write(buffer, 0, buffer.length);
							rafile.close();
							barrier.await();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("写文件失败");
						}
					}
				}).start();
			}

		} catch (LYConnectionException e) {			
			System.out.println(e.getMessage());
		} finally{
			if (connection != null) {
				connection.close();
			}
		}
	}
}
