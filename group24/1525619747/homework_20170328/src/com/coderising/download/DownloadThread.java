package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread
{

	Connection conn;
	int startPos;
	int endPos;
	int length = 0;
	String savePathDir;
	String fileName;

	public DownloadThread (Connection conn, int startPos, int endPos) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.length = endPos - startPos + 1;
	}

	public DownloadThread (Connection conn, int startPos, int endPos,
			String savePathDir, String fileName) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.length = endPos - startPos + 1;
		this.savePathDir = savePathDir;
		this.fileName = fileName;
	}

	public void run() {
		System.out.println("thread " + this.getId() + " running...");

		try {
			byte[] data = null;
			data = conn.read(startPos, endPos);
			// 检验下载长度是否一致（即是否传输出错）
			for (int i = 0; i < 5; i++) {
				if (length != data.length) {
					System.out.print("thread " + this.getId()
							+ " not equal !  Loop " + (i + 1));
					System.out.print(", length: " + length);
					System.out.println(", downloaded: " + data.length);
					data = conn.read(startPos, endPos);
				} else {
					break;
				}
			}

			saveFile(savePathDir, fileName, data, startPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized boolean saveFile(String savePathDir, String fileName,
			byte[] data, int offset) {
		System.out.println("thread " + this.getId() + " saveFile...");
		File saveDir = new File(savePathDir);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}

		synchronized (this) {
			File file = new File(saveDir + File.separator + fileName);
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile(file, "rw");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				raf.seek(offset);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				raf.write(data);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (raf != null) {
						raf.close();
					}
				} catch (IOException e) {
					//
				}
			}
		}

		return false;
	}
}
