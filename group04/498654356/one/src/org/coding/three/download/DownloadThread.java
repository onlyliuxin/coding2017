package org.coding.three.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import org.coding.three.download.api.Connection;

public class DownloadThread extends Thread{

	String filePath = "D:\\a.jpg";
	
	FileDownloader fileDownloader;
	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread(FileDownloader fileDownloader, Connection conn, int startPos, int endPos){
		this.fileDownloader = fileDownloader;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		RandomAccessFile accessFile = null; 
		try {
			synchronized (DownloadThread.class) {
				File file = new File(filePath);
				if(!file.exists()) {
					file.createNewFile();
					accessFile = new RandomAccessFile(file, "rw");
					accessFile.setLength(conn.getContentLength());
				}
			byte[] b = conn.read(startPos, endPos);
			accessFile = new RandomAccessFile(new File(filePath), "rw");
			System.out.println(Thread.currentThread().getName() +  " --> 读取数据 " + b.length );
			accessFile.seek(startPos);
			accessFile.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileDownloader.addFinshCount();
			if(fileDownloader.isFinsh()) {
				fileDownloader.listener.notifyFinished();
			}
			if(accessFile != null) {
				try {
					accessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
}
