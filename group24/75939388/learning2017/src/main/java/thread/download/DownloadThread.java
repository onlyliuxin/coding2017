package thread.download;


import thread.download.api.Connection;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	String localFile = "";

	CyclicBarrier barrier;

	public DownloadThread(Connection conn, int startPos, int endPos, String localFileName, CyclicBarrier barrier){
		this.localFile = localFileName;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.barrier = barrier;
	}
	public void run(){
		try{
			RandomAccessFile file = new RandomAccessFile(localFile, "rw");
			byte[] buffer = this.conn.read(this.startPos, this.endPos);
			file.seek(startPos);
			file.write(buffer);
			file.close();
			this.conn.close();
			barrier.await();
		}catch(Exception e){
		    e.printStackTrace();
		}
	}
}
