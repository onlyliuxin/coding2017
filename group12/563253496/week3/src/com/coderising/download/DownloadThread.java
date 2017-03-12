package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	private String position;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {

			RandomAccessFile raf = new RandomAccessFile(new File(position),"rws")
			byte[]




		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
