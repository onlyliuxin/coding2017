package com.coderising.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	/*默认下载位置*/
	String downloadPath = "C:/Users/ZJ/Desktop";
	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( String downloadPath,Connection conn, int startPos, int endPos){
				
		if(downloadPath!=null&&!downloadPath.isEmpty()){
			this.downloadPath = downloadPath;
		}
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		
        try {
        	
			//请求服务器下载部分文件 指定文件的位置 读取指定位置的字节
			byte[] buffer = conn.read(startPos, endPos);
			//随机访问文件流
			RandomAccessFile raf = new RandomAccessFile(downloadPath+"/"+conn.getDownloadName(), "rw");  
			//随机写文件的时候从哪个位置开始写  
			raf.seek(startPos);//定位文件 
			//写文件			
			raf.write(buffer);
			raf.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}
	
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}		
}