package com.coderising.download;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	FileDownloader fileDown;

	public DownloadThread( Connection conn, int startPos, int endPos){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		FileOutputStream fos = null;
		ByteArrayInputStream bis =null;
		try {
			byte[] bt = conn.read(startPos, endPos);
			File file = new File("C:\\Users\\Adminstater\\Desktop\\test"+Math.ceil(Math.random()*100)+".jpg");
			if(!file.exists()){
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			bis = new ByteArrayInputStream(bt);
			int i = 0;
			byte[] copy = new byte[1024];
			while((i=bis.read(copy))!=-1){
				fos.write(copy, 0, i);
				fos.flush();
			}
			
			DownloadListener listener = fileDown.getListener();
			listener.notifyFinished();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fos.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void setFileDown(FileDownloader fileDown) {
		this.fileDown = fileDown;
	}
	
}
