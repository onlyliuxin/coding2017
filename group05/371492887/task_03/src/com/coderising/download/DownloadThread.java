package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;//这个类不熟悉
	String filePath;

	public DownloadThread(CyclicBarrier barrier, Connection conn, int startPos,
			int endPos, String filePath) {

		this.barrier = barrier;
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
	}

	public void run() {
		try {

			System.out.println("begin download startPos=" + startPos
					+ ",endPos=" + endPos);
			byte[] buffer = conn.read(startPos, endPos);
//			File fileO=new File(filePath);
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			file.seek(startPos);//找到输出的位置
			file.write(buffer, 0, buffer.length);//都在filePath下 
			file.close();
			barrier.await();//干啥的
			System.out.println("finish download startPos=" + startPos + ",endPos=" + endPos);
		} catch (Exception e) {
			System.out.println("download error:startPos=" + startPos
					+ ",endPos=" + endPos);
		}

	}
}
