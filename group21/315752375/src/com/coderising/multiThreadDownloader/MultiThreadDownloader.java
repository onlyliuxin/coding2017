package com.coderising.multiThreadDownloader;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiThreadDownloader {
	private String downloadAdress = "http://img.xmpig.com/forum/201610/20/201816z4zvp3zov8vtpp5g.jpg";
	private String storePathString = "f:/";
	private int threadNum = 4;

	public MultiThreadDownloader() {
	}

	public MultiThreadDownloader(String downloadAdress, String storePathString,
			int threadNum) {
		this.downloadAdress = downloadAdress;
		this.storePathString = storePathString;
		this.threadNum = threadNum;
	}

	public void download() {
		try {
			URL url = new URL(downloadAdress);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setReadTimeout(10000);
			int code=httpURLConnection.getResponseCode();
			if (code== 200) {
				int len = httpURLConnection.getContentLength();
				RandomAccessFile rFile = new RandomAccessFile(new File(storePathString, DownloadThread.getFileName(url)), "rw");
				rFile.setLength(len);
				int threadSize = len / threadNum;
				for (int id = 0; id < threadNum; id++) {
					int startIndex = id * threadSize;
					int endIndex = (id + 1) * threadSize - 1;
					if (id == threadNum - 1) {
						endIndex = len - 1;
					}
					Thread thread = new DownloadThread(id, downloadAdress,
							storePathString, startIndex, endIndex);
					thread.start();
				}
				Thread.sleep(3000);
				System.out.println("download success");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		MultiThreadDownloader mt=new MultiThreadDownloader();
		mt.download();
	}
}
