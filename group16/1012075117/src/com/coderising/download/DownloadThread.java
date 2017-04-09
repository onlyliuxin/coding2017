package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class DownloadThread extends Thread {

	ConnectionManager cm;
	Connection conn;
	DownloadListener downloadListener;
	int startPos;
	int endPos;
	String fileName;
	String url;

	public DownloadThread(String url, int startPos, int endPos, String fileName, DownloadListener downloadListener) {
		this.url = url;
		this.startPos = startPos;
		this.endPos = endPos;
		this.fileName = fileName;
		this.downloadListener = downloadListener;
	}

	public void run() {
		cm = new ConnectionManagerImpl();
		byte[] b = null;
		RandomAccessFile randomAF = null;
		try {
			conn = cm.open(url);
			b = conn.read(startPos, endPos);
			randomAF = new RandomAccessFile(fileName, "rw");
			randomAF.seek(startPos);
			randomAF.write(b);
			randomAF.close();
			downloadListener.notifyFinished();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}

}