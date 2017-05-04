package dataStruct.com.coderising.download;

import dataStruct.com.coderising.download.api.Connection;
import dataStruct.com.coderising.download.api.ConnectionException;
import dataStruct.com.coderising.download.api.ConnectionManager;
import dataStruct.com.coderising.download.api.DownloadListener;

import java.io.File;

public class FileDownloader {
	
	String url;
	DownloadListener listener;
	ConnectionManager cm;
	public static final int threadCount = 5;
	private static int threadFinished;

	public FileDownloader(String _url) {
		this.url = _url;
	}
	
	public void execute(){
		try {
			Connection conn = cm.open(this.url);
			int length = conn.getContentLength();

			File targetFile = new File("D:" + File.separator + getFileName());
			System.out.println("总长度：" + length);

			int temp = length / threadCount;
			for (int i = 0; i < threadCount; i++) {
				int startPos = i * temp;
				int endPos = startPos + temp - 1;
				if (i == threadCount - 1){
					endPos = length;
				}
				String threadName = "DownloadThread" + String.valueOf(i);
				Connection connection = cm.open(url);
				DownloadThread downloadThread = new DownloadThread(connection, startPos, endPos, targetFile, listener);
				downloadThread.setName(threadName);
				downloadThread.start();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		} finally{

		}
	}

	public String getFileName(){
		return "meinv.jpg";
	}

	public synchronized static boolean isDownLoadFinished(){
		threadFinished++;
		return threadFinished == threadCount;
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
}
