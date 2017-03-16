package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {
	Connection conn;
	int startPos;
	int endPos;
	String filePath;

	public DownloadThread(Connection conn, int startPos, int endPos, String filePath) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filePath = filePath;
	}

	public void run() {
		try {
			synchronized (filePath.getClass()) {
				byte[] bytes = conn.read(startPos, endPos);
				RandomAccessFile RAFile = new RandomAccessFile(filePath, "rw");
				RAFile.seek(startPos);
				RAFile.write(bytes, 0, bytes.length);
				RAFile.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
