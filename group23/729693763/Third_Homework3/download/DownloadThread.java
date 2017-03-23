package com.zhous.download;

import com.zhous.download.api.Connection;
import com.zhous.download.api.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	File targetFile;
	DownloadListener downloadListener;

	public DownloadThread( Connection conn, int startPos, int endPos,File targetFile,DownloadListener downloadListener){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.targetFile = targetFile;
		this.downloadListener = downloadListener;
	}
	public void run(){	
		try {
			//读取到指定长度的二进制数据
			System.out.println(this.getName()+"开始下载");
			byte[] data = conn.read(startPos,endPos);

			//插入的file中去
			RandomAccessFile raFile = new RandomAccessFile(this.targetFile,"rw");
			raFile.seek(startPos);
			raFile.write(data,0,endPos-startPos );

			raFile.close();
			//做完了
			if(FileDownloader.isDownloadFinish() ){
				downloadListener.notifyFinished();
				System.out.println(this.getName()+"完成最后的下载，并写入文件");
			}


		}catch (IOException e){

		}
	}
}
