package code03.v1;

import code03.v1.api.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/**
 * 定义线程类
 */


public class DownloadThread extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(DownloadThread.class);

	private Connection conn;
	private int startPos;
	private int endPos;
	private CountDownLatch finished;
	private String fileName;


	public DownloadThread(Connection conn, int startPos, int endPos, CountDownLatch finished,String fileName){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.finished = finished;
		this.fileName = fileName;
	}

	@Override
	public void run(){
		logger.debug("thread {} begin to download from start {} to end {} ",Thread.currentThread().getName(),startPos,endPos);

		try {
			byte[] data = conn.read(startPos,endPos);
			RandomAccessFile rfile = new RandomAccessFile(fileName,"rw");
			rfile.seek(startPos);
			rfile.write(data);
			rfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finished.countDown();
		logger.debug("thread {} end to download from start {} to end {} ",Thread.currentThread().getName(),startPos,endPos);
	}
}
