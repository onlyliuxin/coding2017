package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	String targetPath;
	DownloadListener listener;

	public DownloadThread(){}
	
	public DownloadThread(Connection conn, int startPos, int endPos, String targetPath, DownloadListener listener) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.targetPath = targetPath;
		this.listener = listener;
	}

	public void run() {
		System.out.println("下载：" + startPos + "--" + endPos);
		try {
			byte[] content = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile(new File(this.targetPath), "rw");
			//偏移量超出文件末尾时会自动更改其长度
			raf.seek(startPos);
			raf.write(content, 0, content.length);
			raf.close();
			listener.notifyFinished();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("线程" + startPos + "-" + endPos + "的下载完成.");
	}
}
