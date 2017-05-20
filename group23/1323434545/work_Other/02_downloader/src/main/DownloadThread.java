package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	File saveLocation;
	CyclicBarrier barrier;

	public DownloadThread( Connection conn, int startPos, int endPos,File saveLocation,CyclicBarrier barrier){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.saveLocation = saveLocation;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(saveLocation,"rw");
			rf.seek(startPos);
			rf.write(conn.read(startPos, endPos));
			barrier.await();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null!=rf){
				try {
					rf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=conn){
				conn.close();
			}
			
		}
	}
}