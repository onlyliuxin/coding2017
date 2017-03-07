package com.coderising.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

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
		
        try {
			//请求服务器下载部分文件 指定文件的位置 读取指定位子的字节
			byte[] buffer = conn.read(startPos, endPos);
			//随机访问文件流
			RandomAccessFile raf = new RandomAccessFile("setup.exe", "rwd");  
			//随机写文件的时候从哪个位置开始写  
			raf.seek(startPos);//定位文件 
			//写文件
			raf.write(buffer);
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}