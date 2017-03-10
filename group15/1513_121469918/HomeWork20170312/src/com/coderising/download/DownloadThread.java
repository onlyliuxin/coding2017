package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class DownloadThread extends Thread {
	private static int finishCount =0;
	String url;
	String localFileName;
	int startPos;
	int endPos;
	DownloadListener listener;
	private Object lock = new Object();
	
	public DownloadThread(String url,String localFileName,int startPos, int endPos,DownloadListener listener) {		
		this.url =url;
		this.startPos = startPos;
		this.endPos = endPos;	
		this.localFileName = localFileName;
		this.listener = listener;
	}
	public void run() {	
		RandomAccessFile ras = null;
		Connection conn= null;
		try {
			ConnectionManagerImpl cm = new ConnectionManagerImpl();
			conn = cm.open(url);			
			byte[] download = conn.read(startPos, endPos);					
			ras = new RandomAccessFile(localFileName, "rwd");
			ras.seek(startPos);			
			ras.write(download);						
			synchronized(lock){
				finishCount++;
				if(finishCount == 6){
					listener.notifyFinished();
				}
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ras!=null){
				try {
					ras.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
