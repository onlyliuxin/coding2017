package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	RandomAccessFile raf;

	public DownloadThread(Connection conn, int startPos, int endPos,
			RandomAccessFile raf) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.raf = raf;
	}

	public void run() {
		try {
			raf.write(conn.read(startPos, endPos));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
