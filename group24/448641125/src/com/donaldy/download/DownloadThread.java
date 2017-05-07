package com.donaldy.download;

import com.donaldy.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

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
		System.out.println("running --");
		try (

			RandomAccessFile raf = new RandomAccessFile("test.jpg", "rw")

			)
		{
			raf.seek(startPos);

			byte [] buffer = conn.read(startPos, endPos);

			int hasRead = buffer.length;

			System.out.println("hasRead : " + hasRead);

			raf.write(buffer, 0, hasRead);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("write part file default!");
		}
	}
}
