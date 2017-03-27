package com.github.FelixCJF.coding2017.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.github.FelixCJF.coding2017.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		System.out.println("下载开始");
		try {
			byte[] buff = conn.read(startPos, endPos);
			//创建一个可随机写入文件
			RandomAccessFile randomAccessFile = new RandomAccessFile(new File("G:/"), "rwd");
			randomAccessFile.seek(startPos);
			randomAccessFile.write(buff, 0, buff.length);
			randomAccessFile.close();
			
			System.out.println("下载结束");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
