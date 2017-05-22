package com.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtil {

	private String path; //远程资源路径
	
	private String targetPath;//本地存储路径
	
	private DownFileThread [] threads;// 线程数组
	
	private int threadNum; //线程数量
	
	private long length;//文件大小

	private DownloadUtil(String path, String targetPath,int threadNmu) {
		super();
		this.path = path;
		this.targetPath = targetPath;
		this.threadNum = threadNmu;
		this.threads = new DownFileThread[threadNmu];
	}
	
	
	//多线程下载文件资源
		public void download(){
			URL url;
			
			try {
				url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(5 * 1000);//设置超时时间为5秒
				conn.setRequestMethod("GET");//请求方法
				conn.setRequestProperty("connection", "keep-alive");
				conn.setRequestProperty("accept", "*/*");
				length = conn.getContentLengthLong(); //获取远程文件的大小
				conn.disconnect();
				
				RandomAccessFile targetFile = new RandomAccessFile(targetPath, "rw");
				targetFile.setLength(length); //设置本地文件大小
				
				long avgPart = length / threadNum + 1; //每个线程下载的大小
				
				for (int i = 0; i < threadNum; i++){
					long startPos = avgPart * i;
					RandomAccessFile targetTmp = new RandomAccessFile(targetPath, "rw");
					targetTmp.seek(startPos); //分段下载
					threads[i]  = new DownFileThread(startPos, avgPart, targetTmp);
					threads[i].start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		
		public double getDownRate(){
			
			int currentSize = 0;
			for (int i = 0; i < threadNum; i++){
				currentSize += threads[i].length;
			}
			return currentSize * 1.0 / length;
		}
	
		class DownFileThread extends Thread{
			private long startPos;
			
			private long size;
			
			private long length;
			
			private RandomAccessFile raf;

			private DownFileThread(long startPos, long size,RandomAccessFile raf) {
				super();
				this.startPos = startPos;
				this.size = size;
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
					conn.setRequestProperty("accpet", "*/*");
					
					InputStream in = conn.getInputStream();
					in.skip(this.startPos);
					byte[] buf = new byte[1024];
					int hasRead = 0;
					while(length < size && (hasRead = in.read(buf)) != -1){
						raf.write(buf,0,hasRead);
						length += hasRead;
					}
					raf.close();
					in.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public static void main(String[] args) {
			String path = "http://dongman.tuku.com/121187/1.html";
			String targetPath = "C:/Users/Administrator/Desktop/文本文档.txt";
			final DownloadUtil download = new DownloadUtil(path, targetPath, 4);
			download.download();
			//主线程负责下载文件，再启动一个线程负责监控下载进度
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (download.getDownRate() < 1){
						System.out.println(download.getDownRate());
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();;
		}
}


