package com.xiaol.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.xiaol.download.api.Connection;
import com.xiaol.download.api.DownloadListener;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	RandomAccessFile randomAccessFile;
	DownloadListener listener;

	public DownloadThread(Connection conn, int startPos, int endPos, RandomAccessFile randomAccessFile) {
		this.randomAccessFile = randomAccessFile;
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	@Override
	public void run() {
		try {
			byte[] read = conn.read(startPos, endPos);
			randomAccessFile.seek(startPos);
			randomAccessFile.write(read, 0, endPos - startPos);
			System.out.println(this.getName() + "完成下载，从" + startPos + "到" + endPos + "共" + (endPos - startPos));
			conn.close();
			FileDownloader.finishCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}