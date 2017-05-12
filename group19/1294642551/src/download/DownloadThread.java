package download;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;
	String localFile;

	public DownloadThread( Connection conn, int startPos, int endPos,String localFile, CyclicBarrier barrier){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.localFile = localFile;
		this.barrier = barrier;
	}

	
	public void run(){	
		try {
			System.out.println("Begin to read [" + startPos +"-"+endPos+"]");
			// 每个线程都有一个RandomAccessFile,这样多线程下载的时候不会出现冲突
			RandomAccessFile raf = new RandomAccessFile(localFile, "rw");
			byte[] data = conn.read(startPos, endPos);
			raf.seek(startPos);
			raf.write(data);
			raf.close();
			conn.close();
			barrier.await();//等待别的线程完成
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
