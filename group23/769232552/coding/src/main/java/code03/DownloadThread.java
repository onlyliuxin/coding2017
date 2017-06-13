package code03;

import code03.api.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 定义线程类
 */


public class DownloadThread extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(DownloadThread.class);

	private Connection conn;
	private int startPos;
	private int endPos;
	private static final String fileName = "D://test.png";


	public DownloadThread(Connection conn, int startPos, int endPos){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	@Override
	public void run(){
		logger.debug("thread {} begin to download from start {} to end {} ",Thread.currentThread().getName(),startPos,endPos);

		try {
			byte[] data = conn.read(startPos,endPos);
			RandomAccessFile rfile = new RandomAccessFile(fileName,"rw");
			rfile.seek(startPos);
			rfile.write(data,0,data.length);
			rfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("thread {} end to download from start {} to end {} ",Thread.currentThread().getName(),startPos,endPos);


	}
}
