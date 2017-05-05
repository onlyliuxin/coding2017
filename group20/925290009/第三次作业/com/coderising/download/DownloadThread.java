package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

public class DownloadThread extends Thread {

	
	ConnectionManager cm;
	int startPos;
	int endPos;
	String url;
	DownloadListener downloadListener;

	public DownloadThread(ConnectionManager cm, String url, int startPos, int endPos, DownloadListener listener) {

		this.cm = cm;
		this.startPos = startPos;
		this.endPos = endPos;
		this.url = url;
		this.downloadListener = listener;
	}
	
	@Override
	public void run() {

		Connection conn = null;
		RandomAccessFile raf = null;
		// 随机写文件的时候从哪个位置开始写
		try {
			conn = cm.open(url,startPos,endPos);
		} catch (ConnectionException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		try {
			
			byte[] byeArr = conn.read(startPos, endPos);
			System.out.println("----::" + byeArr.length);
			File file = new File("D:\\111.jpg");
			synchronized (file) {
				if (!file.exists()) {
					file.createNewFile();
				}
				raf = new RandomAccessFile(file, "rw");
			}
			//raf.setLength(34134);
			raf.seek(startPos);// 定位文件
			raf.write(byeArr);

			System.out.println("-------");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (raf != null) {
					raf.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			if (conn != null) {
				conn.close();
			}
			if (downloadListener != null) {
				downloadListener.notifyFinished();
			}

		}
	}
}
