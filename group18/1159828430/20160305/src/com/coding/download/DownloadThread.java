package com.coding.download;

import com.coding.download.api.Connection;
import com.coding.download.impl.FileUtil;

public class DownloadThread extends Thread{

	Connection conn;
	FileUtil file;
	int startPos;
	int endPos;

	public DownloadThread(Connection conn, FileUtil file, int startPos, int endPos) {
		
		this.conn = conn;
		this.file = file;
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			
			byte[] data = conn.read(startPos, endPos);
			int length = endPos - startPos;
			file.writeFile(data, startPos, length);
			
		} catch (Exception e) {
			//System.out.println("线程执行出错"+e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			conn.close();
			file.close();
		}
	}
	
}
