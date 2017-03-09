package com.coderising.download;

import java.io.File;

import com.coderising.download.api.DownloadListener;

public class NotifyCaller extends Thread{

	/*监听器*/
	private DownloadListener listener;
	
	/*内存文件*/
	DownloadThread[] downloadThreads;
	
	/*文件总长度*/
	private int fileLength;
	
	private static final int HUNDRED = 100;
	
	public NotifyCaller(DownloadListener listener,DownloadThread[] downloadThreads,int fileLength){
		
		this.listener = listener;
		this.downloadThreads = downloadThreads;
		this.fileLength = fileLength;
	}
	
	@Override
	public void run() {		
		while(true){
			if(HUNDRED == getPercentOfDownload()){
				rename();
			}
			listener.notifyFinished(getPercentOfDownload());
		}
	}
	   
    /**
     * 获取下载百分比
     * @return
     */
    private int getPercentOfDownload(){
    	
    	int sum = 0;
    	for (int i = 0; i < downloadThreads.length; i++) {
    		sum += downloadThreads[i].downloadSize;
		}
    	return (sum)/(fileLength/HUNDRED);
    }
    
    /**
     * 重命名
     */
    private void rename(){
    	
    	File tempFile = downloadThreads[0].tempFile;
    	String name = tempFile.getName();
    	name = name.substring(0,name.lastIndexOf("."))+downloadThreads[0].sufferName;
    	File file = new File(tempFile.getPath()+"/"+name);
    	tempFile.renameTo(file);
    }
    
	public DownloadListener getListener() {
		return listener;
	}
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}
}
