package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;
import com.coding.basic.ArrayList;

public class DownloadThread extends Thread {

	Connection conn;
	Integer startPos;
	Integer endPos;
	DownloadListener listener;
	File file;
	int threadNum;
	ArrayList threadDone;

	public DownloadThread(Connection conn, int startPos, int endPos, DownloadListener listener, File file,
			Integer threadNum, ArrayList threadDone) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.listener = listener;
		this.file = file;
		this.threadNum = threadNum;
		this.threadDone = threadDone;
		// run();
	}

	@Override
	public synchronized void run() {
		try {			
			byte[] bt = conn.read(startPos, endPos, file);

			threadDone.add(1);
			
			if (conn != null) {
				conn.close();
			}
			if (threadDone.size() == threadNum) {

				listener.notifyFinished();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
