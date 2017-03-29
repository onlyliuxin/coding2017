package rui.study.coding2017.jobs3.download;


import rui.study.coding2017.jobs3.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

import static rui.study.coding2017.jobs3.download.FileDownloader.getFilePath;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread(Connection conn, int startPos, int endPos){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}
	@Override
	public void run(){
		try {
			byte[] bytes=conn.read(startPos,endPos);
			RandomAccessFile randomAccessFile=new RandomAccessFile(getFilePath(),"rw");
            randomAccessFile.seek(startPos);
            randomAccessFile.write(bytes);
            randomAccessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}

	}
}
