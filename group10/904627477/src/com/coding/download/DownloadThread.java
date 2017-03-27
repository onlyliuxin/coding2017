package com.coding.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coding.download.api.Connection;
import com.coding.util.IOUtils;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	private File file;

	public DownloadThread(Connection conn, int startPos, int endPos,File file) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
	}

	public void run() {
		byte[] buff;
		try {
			buff = conn.read(startPos, endPos);
			if(buff!=null&&buff.length!=0){
				IOUtils.writeFile(file, startPos, buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
