package com.coderising.download;

import com.coderising.download.api.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread{

    private final Logger logger = LoggerFactory.getLogger(DownloadThread.class);

	private Connection connection;

	private int startPos;

	private int endPos;

	private File file;

	private CountDownLatch countDownLatch;

	public DownloadThread(File file, CountDownLatch countDownLatch, Connection connection, int startPos, int endPos){
		// validate
	    if (file == null) throw new IllegalArgumentException("destination file can't be null");
	    if (!file.exists()) throw new IllegalArgumentException("the destination file doesn't exists");

	    this.countDownLatch = countDownLatch;
	    this.file = file;
		this.connection = connection;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	public void run(){
		try {
			byte[] read = connection.read(startPos, endPos);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(read);
            fileOutputStream.flush();

			countDownLatch.countDown();
		} catch (IOException e) {
		    logger.error("download error:", e);
		}
	}
}
