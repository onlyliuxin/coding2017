package com.github.chaoswang.learning.java.downloader;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.github.chaoswang.learning.java.downloader.api.Connection;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;

	public DownloadThread(Connection conn, int totalSection, int sectionIndex){
		this.conn = conn;
		int contentLength = conn.getContentLength();
		initStartPosAndEndPos(contentLength, totalSection, sectionIndex);
	}
	
	private void initStartPosAndEndPos(int contentLength, int totalSection, int sectionIndex){
		int sectionLength = contentLength / totalSection;  
		startPos = (sectionIndex - 1) * sectionLength;
        if(sectionIndex == totalSection){
        	endPos = contentLength - 1;
        }else{
        	endPos = sectionIndex * sectionLength - 1;
        }
	}
	
	public void run(){	
		try {
			writeByteArrayToFile(conn.read(startPos, endPos), "F:\\tmp\\6977.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeByteArrayToFile(byte[] buf, String destFilePath){
		try {
			// 创建一个可读可写的随机访问文件
			RandomAccessFile out = new RandomAccessFile(destFilePath, "rw");
			out.seek(startPos);// 从指定位置开始写
			out.write(buf);
	        out.close();// 从里到外关闭文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}