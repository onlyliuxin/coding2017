package com.coderising.download;

import java.io.File;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.impl.ConnectionManagerImpl;
import com.coding.basic.List;
import com.coding.basic.array.ArrayList;

public class Resource {

	private int index;
	private int size;
	private String url;
	private List<Thread> threadList;
	private File file;
	private boolean flag;
	
	public Resource(String url, File file){
		index = 0;
		size = 1024 * 2;
		this.url = url;		
		threadList = new ArrayList<Thread>();
		this.file = file;
		flag = false;
	}

	public synchronized int increase(){
		while(threadList.size() >= 5){
			try {
				this.wait(); //wait()每个Object都有，wait()方法要在同步代码块中运行，否则报异常
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int startPos = index * size;		
		try {
			Connection conn = new ConnectionManagerImpl().open(url);
			DownloadThread dt = new DownloadThread(conn, startPos, startPos+size-1, file);
			dt.start();
			index++;
			threadList.add(dt);
			this.notify();
			
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return index * size;
	}
	
	public synchronized void decrease(){
		while(threadList.size() == 0){			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i=0; i<threadList.size(); i++){
				if(threadList.get(i).isAlive()){
					threadList.remove(i);
					this.notify();
					break;
				}
			}
		}
	}
	
	public List<Thread> getThreadList() {
		return threadList;
	}

	public void setThreadList(List<Thread> threadList) {
		this.threadList = threadList;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
