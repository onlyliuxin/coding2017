package cn.net.pikachu.download;


import cn.net.pikachu.download.api.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		try {
			byte[] bytes = conn.read(startPos,endPos);
			LogUtil.log(" startPos = "+startPos+", "+"endPos = "+endPos);
			writeToFile(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile(byte[] bytes){
		File file = FileUtil.getFile();
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			LogUtil.log("写入文件");
			raf.seek(startPos);
			raf.write(bytes);
			raf.close();
			LogUtil.log("\n"+Arrays.toString(bytes));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
