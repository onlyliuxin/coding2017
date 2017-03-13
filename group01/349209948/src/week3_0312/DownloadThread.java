package week3_0312;

import java.io.IOException;
import java.io.RandomAccessFile;

import week3_0312.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		byte[] data = new byte[endPos - startPos];
		try {
			data = conn.read(startPos, endPos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeToFile(data);
	}
	public void writeToFile(byte[] data) {
		RandomAccessFile file;
		try {
			file = new RandomAccessFile("downloadTest.jpg","rw");
			file.seek(startPos);
			file.write(data, 0, data.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
