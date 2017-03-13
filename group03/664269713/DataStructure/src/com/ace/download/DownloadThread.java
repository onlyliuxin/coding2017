package com.ace.download;

import com.ace.download.api.Connection;
import com.ace.download.api.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	private RandomAccessFile raf;
	private DownloadListener listener;

	public DownloadThread(Connection conn, int startPos, int endPos, RandomAccessFile raf, DownloadListener listener){
		this.raf = raf;
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.listener = listener;
	}
	public void run(){
		try {
			raf.seek(startPos);
			byte[] bytes = conn.read(startPos, endPos);
			int contentLength = endPos - startPos;
			raf.write(bytes, 0, contentLength);

			if(raf.length() >= (conn.getContentLength() - 2)){
				listener.notifyFinished();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			conn.close();
		}

	}
}
