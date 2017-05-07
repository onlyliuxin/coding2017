package com.coding.download;

import com.coding.download.api.Connection;
import com.coding.download.api.ConnectionException;
import com.coding.download.impl.ConnectionManagerImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String filelocal;
	CountDownLatch cdl;
	public DownloadThread(Connection conn, int startPos, int endPos, String filelocal, CountDownLatch cdl){

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.filelocal = filelocal;
		this.cdl = cdl;
	}
	public void run(){
		/*try {
			RandomAccessFile fos = new RandomAccessFile("C:\\Users\\gaokun\\Desktop\\test3.jpg", "rwd");
			byte[] read = conn.read(startPos, endPos);
			fos.seek(startPos);
			fos.setLength(endPos-startPos);
			fos.write(read);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		RandomAccessFile raf = null;
		try {
			String url = "http://www.dabaoku.com/sucaidatu/dongwu/chongwujingling/804838.JPG";
			byte[] buf = conn.read(startPos, endPos);
			raf = new RandomAccessFile(filelocal, "rwd");
			raf.seek(startPos);
			raf.write(buf);
			raf.close();
			cdl.countDown();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
