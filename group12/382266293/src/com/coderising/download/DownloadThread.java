package com.coderising.download;

import java.io.File;
import java.io.IOException;

import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	private String dest;
	private FileDownloader fileDownloader;

	public DownloadThread(Connection conn, int startPos, int endPos) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	@Override
	public void run() {
		System.out.println(this.getName() + " is running");
		RandomAccessFile raf = null;
		try {
			byte[] buffer = conn.read(startPos, endPos);
			raf = new RandomAccessFile(new File(dest), "rws");
			raf.seek(startPos);
			raf.write(buffer);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(this.getName() + " finished");

			try {
				if (raf != null)
					raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				notifyFinished();
			}
		}
	}

	public void setFileDownloader(FileDownloader fileDownloader) {
		this.fileDownloader = fileDownloader;
	}

	public void notifyFinished() {
		fileDownloader.setThreadFinished();
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public void close() {
		this.conn.close();

	}

}
