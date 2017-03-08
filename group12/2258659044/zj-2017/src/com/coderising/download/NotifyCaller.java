package com.coderising.download;

import java.util.Map;
import java.util.Set;

import com.coderising.download.api.DownloadListener;

public class NotifyCaller extends Thread{

	/*监听器*/
	private DownloadListener listener;
	
	/*下载线程*/
	private DownloadThread[] downloadThreads;
	
	
	public NotifyCaller(DownloadListener listener,DownloadThread[] downloadThreads){
		
		this.listener = listener;
		this.downloadThreads = downloadThreads;
	}
	
	@Override
	public void run() {		
		while(true){	
			if(DownloadThreadsIsComplete(downloadThreads)){
				listener.notifyFinished();
			}										
		}
	}
	
	/**
	 * 根据名称判断这些线程是否执行完毕
	 * @param threadNames
	 * @return
	 */
    private boolean DownloadThreadsIsComplete(DownloadThread[] downloadThreads){
		
		Map<Thread, StackTraceElement[]> threadMaps=Thread.getAllStackTraces();
		Set<Thread> keySet = threadMaps.keySet();
		for (int i = 0; i < downloadThreads.length; i++) {
			if(keySet.contains(downloadThreads[i])){
				return false;
			}
		}
		return true;
	}
        
	public DownloadListener getListener() {
		return listener;
	}
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public DownloadThread[] getDownloadThreads() {
		return downloadThreads;
	}

	public void setDownloadThreads(DownloadThread[] downloadThreads) {
		this.downloadThreads = downloadThreads;
	}
}
