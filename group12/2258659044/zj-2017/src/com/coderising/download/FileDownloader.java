package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

public class FileDownloader {
	
	String url;
	
	String downloadPath;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	/*线程数目*/
	private final int threadNum = 5;
	
	public FileDownloader(String _url) {
		this.url = _url;
		
	}
	
	public void execute(){

		Connection conn = null;
		try {
			
			conn = cm.open(this.url);			
			int length = conn.getContentLength();			
			//分配下载块
			DownloadThread[] threads = assignDownloadPart(length);
			//判断所有线程是否下载完成
			new NotifyCaller(listener,threads,length).start();
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
			}
		}								
	}

	/**
	 * 分配下载块并启动下载
	 * @param length
	 * @return
	 * @throws ConnectionException
	 */
	private DownloadThread[] assignDownloadPart(int length)
			throws ConnectionException {
		int blockSize = length / threadNum;
		DownloadThread[] threads = new DownloadThread[threadNum];
		for (int thread = 1; thread <= threadNum; thread++) {				
		    int startIndex = (thread - 1) * blockSize;  
		    int endIndex = thread * blockSize-1;  
		    if (thread == threadNum) {//最后一个线程下载的长度
		        endIndex = length;  
		    }                   
		    DownloadThread thr = new DownloadThread(downloadPath,cm.open(this.url),startIndex,endIndex);
		    threads[thread-1] = thr;
		    thr.start();
		}
		return threads;
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

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
		
}
