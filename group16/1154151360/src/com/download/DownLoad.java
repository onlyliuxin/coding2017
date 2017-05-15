package com.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownLoad {

	private String path;//资源路径
	
	private String targetPath;//本地路径
	
	private long length;//文件大小
	
	private int threadNum;//线程数量
	
	private DownThread [] threads;// 线程数组
	
	
	
	public DownLoad(String path, String targetPath, int threadNum) {
		this.path = path;
		this.targetPath = targetPath;
		this.threadNum = threadNum;
		this.threads = new DownThread[threadNum];
	}


	public void downLoad(){
		
		URL url;
		
		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setReadTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Accpet", "*/*");
			
			length = conn.getContentLengthLong();
			conn.disconnect();
			RandomAccessFile r = new RandomAccessFile(targetPath, "rw");
			
			r.setLength(length);
			
			long avgPart = length / threadNum + 1; //设置每个线程下载文件的大小
			
			for(int i = 0; i < threadNum; i++){
				long startPos = avgPart * i;
				RandomAccessFile raf = new RandomAccessFile(targetPath, "rw");
				threads [i] = new DownThread(startPos,avgPart, raf);
				threads[i].start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class DownThread extends Thread{
		
		private long startPos;
		
		private long size;
		
		private long length;
		
		private RandomAccessFile raf;
		
		
		DownThread(long startPos, long length,
				RandomAccessFile raf) {
			super();
			this.startPos = startPos;
			this.length = length;
			this.raf = raf;
		}


		public void run(){
			URL url;
			
			try {
				url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("connection", "keep-alive");
				conn.setRequestProperty("Accpet", "*/*");
				
				InputStream in = conn.getInputStream();
				in.skip(this.startPos);
				byte[] buf = new byte[1024];
				int headread = 0;
				while (size < length && (headread = in.read(buf)) != -1 ){
					raf.write(buf, 0, headread);
					size += headread;
				}
				raf.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String path = "http://dongman.tuku.com/121187/1.html";
		String targetPath = "C:/Users/Administrator/Desktop/文本文档.txt";
		final DownLoad downLoad = new DownLoad(path, targetPath,4);
		downLoad.downLoad();
		
		
	}
}

