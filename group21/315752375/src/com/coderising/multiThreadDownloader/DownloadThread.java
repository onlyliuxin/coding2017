package com.coderising.multiThreadDownloader;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadThread extends Thread {

	private int id;
	private String downloadPath;
	private String storePath;
	private int startIndex;
	private int endIndex;

	public DownloadThread(int id, String downloadPath,String storePath, int startIndex, int endIndex) {
		this.downloadPath = downloadPath;
		this.id = id;
		this.storePath=storePath;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Thread " + id + "开始下载：");
			URL url = new URL(downloadPath);
			File tmpFile = new File(storePath, getFileName(url) + id);
			RandomAccessFile rFile = null;
			if (tmpFile.exists()) {
				rFile = new RandomAccessFile(tmpFile, "rwd");
				String start = rFile.readLine();
				this.startIndex = Integer.parseInt(start);
			} else {
				rFile = new RandomAccessFile(tmpFile, "rwd");
			}
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setRequestProperty("Range", "bytes="+ startIndex + "-" + endIndex);
			int code=httpURLConnection.getResponseCode();
			System.out.println("Thread "+id+" code: "+code);
			if ( code== 206) {
				InputStream inputStream = httpURLConnection.getInputStream();
				RandomAccessFile randomAccessFile = new RandomAccessFile(new File(storePath, getFileName(url)), "rw");
				randomAccessFile.seek(startIndex);
				byte[] tmp = new byte[1024];
				int length = -1;
				int total = 0;
				while ((length = inputStream.read(tmp)) > 0) {
					randomAccessFile.write(tmp, 0, length);
					total += length;
					rFile.seek(0);
					rFile.write((startIndex + total + "").getBytes("UTF-8"));
				}
				rFile.close();
				inputStream.close();
				randomAccessFile.close();
				cleanTemp(tmpFile);
				System.out.println("thread "+id+"下载完成");
			}else{
				System.out.println("响应吗："+code+"--不支持多线程下载");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getFileName(URL url) {
		String name = url.getFile();
		name = name.substring(name.lastIndexOf("/") + 1);
		return name;
	}
	private synchronized void cleanTemp(File file){
        file.delete();
    }
}
