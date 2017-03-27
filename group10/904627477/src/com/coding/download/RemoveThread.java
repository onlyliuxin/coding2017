package com.coding.download;

import com.coding.download.api.DownloadListener;
import com.coding.download.api.Resource;

public class RemoveThread extends Thread {
	
	private Resource resource;
	private DownloadListener listener;
	
	public RemoveThread(Resource resource,DownloadListener listener){
		this.resource = resource;
		this.listener = listener;
	}

	@Override
	public void run() {
		while(true){
			if(resource.isFlag()&&resource.getThreads().size()==0){
				listener.notifyFinished();
				break;
			}else{
				resource.decreace();
			}
		}
	}
	
	

}
