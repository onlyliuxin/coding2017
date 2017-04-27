package com.github.HarryHook.coding2017.download;

import java.io.RandomAccessFile;

import com.github.HarryHook.coding2017.download.api.Connection;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;
    boolean downloadFinsh = false;

    public DownloadThread(Connection conn, int startPos, int endPos) {

	this.conn = conn;
	this.startPos = startPos;
	this.endPos = endPos;
    }

    public void run() {

	RandomAccessFile outFile = null;
	try {

	    byte[] buffer = conn.read(startPos, endPos);
	    // 在本地创建一个与服务器大小一致的可随机写入文件
	    outFile = new RandomAccessFile("bd_logo.png", "rwd");
	    outFile.seek(startPos);
	    outFile.write(buffer);
	    FileDownloader fileloader = new FileDownloader("");
	    fileloader.getListener();

	} catch (Exception e) {

	    e.printStackTrace();

	} finally {

	    try {
		outFile.close();

	    } catch (Exception e) {

		e.printStackTrace();
	    }

	}
    }

}
