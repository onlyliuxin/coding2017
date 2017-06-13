package com.coding.download.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.coding.download.DownloadThread;
import com.coding.download.impl.ConnectionManagerImpl;

public class Resource {
	
	private int index;
	private int size;
	private String url;
	private List<Thread> threads;
	private File file;
	private boolean flag;
	
	public Resource(String url,File file){
		index = 0;
		size = 1024*2;
		threads = new ArrayList<Thread>();
		flag = false;
		this.url = url;
		this.file = file;
	}
	
	public synchronized int increace(){
		//System.out.println(threads.size());
		while(threads.size()>=5){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int startPos = index*size;
		try {
			Connection conn = new ConnectionManagerImpl().open(url);
			DownloadThread down = new DownloadThread(conn, startPos, startPos+size-1,file);
			down.start();
			index ++;
			threads.add(down);
			this.notify();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} 
		return index*size;
	}
	
	public synchronized void decreace(){
		//System.out.println("decreace:"+threads.size());
		while(threads.size()==0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		for (int i = 0; i < threads.size();i++) {
			if(!threads.get(i).isAlive()){
				threads.remove(i);
				this.notify();
				break;
			}
		}
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
