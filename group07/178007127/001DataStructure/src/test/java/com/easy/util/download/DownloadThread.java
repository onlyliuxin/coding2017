package com.easy.util.download;

import java.awt.RadialGradientPaint;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.RandomAccess;

public class DownloadThread extends Thread{
	private int threadId;
	private int startIndex;
	private int endIndex;
	private String path;
	
	public  DownloadThread(String path,int threadId,int startIndex,int endIndex) {
		this.path=path;
		this.threadId=threadId;
		this.startIndex=startIndex;
		this.endIndex=endIndex;
	}
	
	@Override
	public void run() {
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setRequestMethod("GET");
			// 重要：请求服务器下载部分的文件 指定文件的位置
			conn.setRequestProperty("Range", "bytes=" + startIndex + "-"
					+ endIndex);
			conn.setConnectTimeout(5000);
			// 从服务器请求全部资源的状态码200 ok 如果从服务器请求部分资源的状态码206 ok
			int code = conn.getResponseCode();
			System.out.println("---code---" + code);
			InputStream is = conn.getInputStream();// 已经设置了请求的位置，返回的是当前位置对应的文件的输入流
			RandomAccessFile raf = new RandomAccessFile("d://github666.jpg", "rwd");
			// 随机写文件的时候从哪个位置开始写
			raf.seek(startIndex);// 定位文件
			int len = 0;
			byte[] buffer = new byte[1024*1024];
			while ((len = is.read(buffer)) != -1) {
				raf.write(buffer, 0, len);
			}
			is.close();
			raf.close();
			System.out.println("线程" + threadId + ":下载完毕了！");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
