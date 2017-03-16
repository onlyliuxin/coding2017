package cn.net.pikachu.download;


import cn.net.pikachu.download.api.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	private RandomAccessFile raf = null;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			byte[] bytes = conn.read(startPos,endPos);
			raf.write(bytes);
			System.out.println("startPos = "+startPos+", "+"endPos = "+endPos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFile(File file){
		try {
			raf = new RandomAccessFile(file, "rws");
			raf.seek(startPos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
