package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	File file;

	public DownloadThread(Connection conn, int startPos, int endPos, File file) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
	}

	public void run() {
		RandomAccessFile raf=null;
		try {
			raf=new RandomAccessFile(file, "rw");
			raf.seek(startPos);
			raf.write(conn.read(startPos, endPos),0,endPos-startPos+1);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
