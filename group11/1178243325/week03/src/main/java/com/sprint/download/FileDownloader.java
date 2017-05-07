package com.sprint.download;

import com.sprint.download.api.Connection;
import com.sprint.download.api.ConnectionManager;
import com.sprint.download.api.DownloadListener;
import java.util.concurrent.CyclicBarrier;
import java.io.RandomAccessFile;
import java.io.IOException;
public class FileDownloader {
	private	String url;
	private String localFile;

	DownloadListener listener;
	ConnectionManager cm;
	
	private static final int DOWNLOAD_THREAD_NUM = 3;

	public FileDownloader(String _url, String localFile) {
		this.url = _url;
		this.localFile = localFile;
	}

	public void execute() {
		CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_THREAD_NUM, new Runnable() {
			public void run() {
				listener.notifyFinished();
			}
		});	

		Connection conn = null;

		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();
			createPlaceHolderFile(this.localFile, length);
			int[][] ranges = allocateDownloadRange(DOWNLOAD_THREAD_NUM, length);
			for (int i = 0; i < DOWNLOAD_THREAD_NUM; i++) {
				DownloadThread thread = new DownloadThread(
						cm.open(url),
						ranges[i][0],
						ranges[i][1],
						localFile,
						barrier);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private void createPlaceHolderFile(String fileName, int contentLen) throws IOException {
		RandomAccessFile file = new RandomAccessFile(fileName, "rw");
		for (int i = 0; i < contentLen; i++) {
			file.write(0);
		}
		file.close();
	}

	private int[][] allocateDownloadRange(int threadNum, int contentLen) {
		int[][] ranges = new int[threadNum][2];
		int eachThreadSize = contentLen / threadNum;
		int left = contentLen % threadNum;
		for (int i = 0; i < threadNum; i++) {
			int startPos = i*eachThreadSize;
			int endPos = (i+1) * eachThreadSize - 1;
			if (i == (threadNum -1)) {
				endPos += left;
			}
			ranges[i][0] = startPos;
			ranges[i][1] = endPos;
		}
		return ranges;
	}
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm) {
		this.cm = ucm;
	}

	public DownloadListener getListener() {
		return listener;
	}
}
