package task3.download;

import task3.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{
	Connection conn;
	int startPos;
	int endPos;
	String targetPath;

	public DownloadThread(Connection conn, int startPos, int endPos){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	public DownloadThread( Connection conn, int startPos, int endPos, String targetPath) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.targetPath = targetPath;
	}

	public void run(){
		try {
			byte[] rs = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile(targetPath, "rw");
			raf.seek(startPos);
			raf.write(rs, 0, rs.length);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
