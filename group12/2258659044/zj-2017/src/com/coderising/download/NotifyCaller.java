package com.coderising.download;

import java.io.File;
import java.text.DecimalFormat;

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
		
		int i =1;
		while(true){			
			try {	
				Thread.sleep(5000);
				if(HUNDRED == getPercentOfDownload()){
					rename();
				}
				listener.notifyFinished(getPercentOfDownload(),getDownloadSpeed(5*i));
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	   
    /**
     * 获取下载百分比
     * @return
     */
    private int getPercentOfDownload(){
    	
    	int sum = calculateDownloadSize();
    	return (sum)/(fileLength/HUNDRED);
    }
    
    /**
     * 获取下载速度
     * @return
     */
    private String getDownloadSpeed(int timeDiff){
    	
    	float sum = calculateDownloadSize();
    	DecimalFormat df = new DecimalFormat("0.000");//格式化小数    
        String num = df.format((sum/((float)(1024*1024)*timeDiff)));//返回的是String类型  
        if(num==null||num.isEmpty()){
        	num = "0";
        }
    	return num+"Mb/s";
    }
    
    /**
     * 计算已下载文件大小
     */
    private int calculateDownloadSize(){
    	int sum = 0;
    	for (int i = 0; i < downloadThreads.length; i++) {
    		sum += downloadThreads[i].downloadSize;
		}
    	return sum;
    }
    /**
     * 重命名
     */
    private void rename(){
    	
    	File tempFile = downloadThreads[0].tempFile;
		String path = tempFile.getPath();
		String name = path.substring(0,path.lastIndexOf("."))+downloadThreads[0].sufferName;
    	File file = new File(name);
    	tempFile.renameTo(file);
    	
    }
    
	public DownloadListener getListener() {
		return listener;
	}
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}
}
