package com.github.chaoswang.learning.java.downloader;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.github.chaoswang.learning.java.downloader.api.Connection;

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
			writeByteArrayToFile(conn.read(startPos, endPos), "F:\\6977.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeByteArrayToFile(byte[] buf, String destFilePath){
		BufferedOutputStream bos = null;
		try{
			bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
			bos.write(buf);
			bos.flush();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (bos != null){
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}