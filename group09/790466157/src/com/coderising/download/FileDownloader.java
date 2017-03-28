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
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	private static final int THREAD_NUM = 3;

	private static final String BASE_PATH = "C:/Users/Liner/Desktop/Document/Doc";

	boolean isFinished = false;
	
	public FileDownloader(String _url) {
		this.url = _url;
		File baseFile = new File(BASE_PATH);
		if(!baseFile.exists()){
			baseFile.mkdirs();
		}
	}
	
	public void execute(){
		
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