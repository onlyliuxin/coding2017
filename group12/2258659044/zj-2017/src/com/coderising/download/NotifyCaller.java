package com.coderising.download;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coderising.download.api.DownloadListener;

public class NotifyCaller extends Thread{

	/*监听器*/
	private DownloadListener listener;
	
	/*线程名*/
	private List<String> threadNames;
	
	public NotifyCaller(DownloadListener listener,List<String> threadNames){
		
		this.listener = listener;
		this.threadNames = threadNames;
	}
	
	@Override
	public void run() {		
		while(true){
			if(DownloadThreadsIsComplete(threadNames)){
				listener.notifyFinished();
				break;
			}						
		}
	}
	
	/**
	 * 根据名称判断这些线程是否执行完毕
	 * @param threadNames
	 * @return
	 */
    private boolean DownloadThreadsIsComplete(List<String> threadNames){
		
		Map<Thread, StackTraceElement[]> threadMaps=Thread.getAllStackTraces();
		Iterator<Thread> it = threadMaps.keySet().iterator();
		while(it.hasNext()){
			Thread thread = it.next();
			if(threadNames.contains(thread.getName())){
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

	public List<String> getThreadNames() {
		return threadNames;
	}

	public void setThreadNames(List<String> threadNames) {
		this.threadNames = threadNames;
	}			
}
