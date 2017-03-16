package com.github.eloiseSJTU.coding2017.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.github.eloiseSJTU.coding2017.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	String fileName;

	public DownloadThread(Connection conn, int startPos, int endPos, String fileName) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.fileName = fileName;
	}

	public void run() {
		System.out.println("下载线程开始：" + startPos + ", " + endPos);
		try {
			if (conn != null) {
				byte[] result = conn.read(startPos, endPos);
				if (result != null) {
					RandomAccessFile file = new RandomAccessFile(new File(fileName), "rwd");
					file.seek(startPos);
					file.write(result, 0, result.length);
					file.close();
				}
			}
			System.out.println("下载线程结束：" + startPos + ", " + endPos);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
