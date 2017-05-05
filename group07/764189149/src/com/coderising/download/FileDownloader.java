package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {

	boolean isFinished = false;

	String url;

	DownloadListener listener;

	ConnectionManager cm;

	private static final int THREAD_NUM = 3;

	private static final String BASE_PATH = "F:/download/";


	public FileDownloader(String _url) {
		this.url = _url;
		File baseFile = new File(BASE_PATH);
		if(!baseFile.exists()){
			baseFile.mkdirs();
		}

	}

	public void execute(){
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法

		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM, new Runnable() {

			@Override
			public void run() {
				listener.notifyFinished();
			}
		});
		Connection conn = null;
		try {

			conn = cm.open(this.url);

			int length = conn.getContentLength();
			String filePath = BASE_PATH + "download."+getFileType(this.url);
			//System.out.println(filePath);
			
			File file = new File(filePath);
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try{
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(new byte[length], 0, length);//占位
				fos.close();
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}

			int blockSize = (length % THREAD_NUM == 0 ) ? length / THREAD_NUM : (length / THREAD_NUM + 1);
			for (int i = 0; i < THREAD_NUM; i++) {
				int startPos = i * blockSize;
				int endPos = startPos + blockSize - 1;
				if(endPos >= length - 1){
					endPos = length - 1;
				}
				new DownloadThread(barrier , conn, startPos, endPos , filePath).start();	
			}

		} catch (ConnectionException e) {			
			System.out.println(e.getMessage());
		} finally{
			if(conn != null){
				conn.close();
			}
		}




	}

	private String getFileType(String url) {
		int index = url.lastIndexOf(".");
		return url.substring(index + 1 , url.length());
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}



	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}

	public DownloadListener getListener(){
		return this.listener;
	}

}
